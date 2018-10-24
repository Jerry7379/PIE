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
        uint256 porkID;//小猪id
        Operation[] operation;
    }

    mapping(uint256 => Pork) porkMap;
    address LogisticsID;

    modifier onlyLogistics(){
        require(msg.sender == LogisticsID);
        _;
    }

    function insertLogisticsOperation(uint256 porkID, bytes32[] carID, bytes32[] humidity,
        bytes32[] temperature, bytes32[] CO2, bytes32[] location)external view onlyLogistics returns (bool,bytes32){
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

    function getLOperation(uint256 _pigID)external view
    returns(
        bytes32[] carID,
        bytes32[] humidity,
        bytes32[] temperature,
        bytes32[] CO2,
        bytes32[] location
    ){
        Pork storage pork = porkMap[_pigID];
        porkMap[pork.porkID].operation.push(Operation({
            carID : carID,
            humidity : humidity,
            temperature : temperature,
            CO2 : CO2,
            location : location
            }));
    }
}
