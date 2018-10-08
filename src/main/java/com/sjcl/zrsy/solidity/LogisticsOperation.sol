pragma solidity ^0.4.0;

contract LogisticsOperation {

    struct Operation{
        bytes32[] carID;
        bytes32[] humidity;
        bytes32[] temperature;
        bytes32[] CO2;
        bytes32[] location;
        uint[] timestamps;//时间戳
    }

    struct Pork{
        bytes32[] porkID;//小猪id
        Operation[] operation;
    }

    mapping(bytes32 => Pork) porkMap;
    address LogisticsID;

    modifier onlyLogistics(){
        require(msg.sender == LogisticsID);
        _;
    }

    function insertLogisticsOperation(bytes32[] porkID, bytes32[] carID, bytes32[] humidity,
            bytes32[] temperature, bytes32 CO2, bytes32 location)external onlyLogistics returns (bool,bytes32){
        bytes32[] pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }

        porkMap[pork.porkID].insertLogisticsOperation.push(Operation({
            operation : operation,
            content : content,
            remark : remark,
            timestamps : timestamps
            }));
        return(true,"成功");
    }
}
