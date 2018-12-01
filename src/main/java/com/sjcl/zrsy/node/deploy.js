var contracts = require('@monax/legacy-contracts');


//本机启动的Burrow结点GateWay
var burrowURL = "http://localhost:1337/rpc";

//部署账户文件，data.json中包含了部署合约的账户的公私钥(可以从Burrow启动目录的.keys/data/中找到，即：Burrow结点启动时自动配置生成的结点)
/*
data.json格式：
	{
		"address": "A58224C8AE102CA4978F0B23F0722BDEAC15D7A4",
		"pubKey": "C56ABE7944C4334410B81B5796F32799954BDD84EB24D52778B50C50564A6610",
		"privKey": "B7DEEF14B6CDB8F5BA66547AFD61D08117EA854B5A2EEF21BB91ACE3C82BD141C56ABE7944C4334410B81B5796F32799954BDD84EB24D52778B50C50564A6610"
	}
*/
var accountData = require('./data.json');
var contractManager = contracts.newContractManagerDev(burrowURL, accountData);
//使用solc编译生成的ABI
var myAbi = [{
    "constant":true,
    "inputs":[
        {
            "name":"a",
            "type":"int256"
        },{
            "name":"b",
            "type":"int256"
        }],
    "name":"add",
    "outputs":[{
        "name":"sum",
        "type":"int256"
    }],
    "type":"function"
}];

//使用solc编译好的Solidity字节码
var myCompiledCode = "608060405234801561001057600080fd5b5060c58061001f6000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063a5f3c23b146044575b600080fd5b348015604f57600080fd5b5060766004803603810190808035906020019092919080359060200190929190505050608c565b6040518082815260200191505060405180910390f35b60008183019050929150505600a165627a7a723058201b683801c2a238c5df911cc7b1a12d5be2ce133d0de8723ff9de21752a1965300029";
// Create a factory for the contract with the JSON interface 'myAbi'.
var myContractFactory = contractManager.newContractFactory(myAbi);

// To create a new instance and simultaneously deploy a contract use `new`:
var myNewContract;
myContractFactory.new({data: myCompiledCode}, function(error, contract){
    if (error) {
        // Something.
        throw error;
    }
    myNewContract = contract;
    console.log(contract);
});
