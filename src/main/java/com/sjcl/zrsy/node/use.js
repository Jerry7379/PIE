var contracts = require('@monax/legacy-contracts');
var burrowURL = "http://localhost:1337/rpc";
var accountData = require('./data.json');
var contractManager = contracts.newContractManagerDev(burrowURL, accountData);
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


// Create a factory for the contract with the JSON interface 'myAbi'.
var myContractFactory = contractManager.newContractFactory(myAbi);

var contractAddress = "799C16C5100CD976057A91247F01282F53A3C744";
var myExistingContract;


function addCallback(error, sum){
    console.log(sum.toString()); // Would print: 56
}

myContractFactory.at(contractAddress, function(error, contract){
    if (error) {
        throw error;
    }
    contract.add(3, 2, addCallback);
});
