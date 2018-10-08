pragma solidity ^0.4.0;

contract LogisticsOperation {

    struct Operation{
        bytes32[] carID;
        bytes32[] humidity;
        bytes32[] temperature;
        bytes32[] CO2;
        bytes32[] location;
    }

    struct Pork{
        bytes32 porkID;//小猪id
        Operation[] operation;
    }

    mapping(bytes32 => Pork) porkMap;
    address LogisticsID;

    modifier onlyLogistics(){
        require(msg.sender == LogisticsID);
        _;
    }

    function insertLogisticsOperation(bytes32 porkID, bytes32[] carID, bytes32[] humidity,
        bytes32[] temperature, bytes32[] CO2, bytes32[] location)public view onlyLogistics returns (bool,bytes32){
        Pork pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }

        porkMap[pork.porkID].operation.push(Operation({
            carID : carID,
            humidity : humidity,
            temperature : temperature,
            CO2 : CO2,
            location : location
            }));
        return(true,"成功");
    }
}
