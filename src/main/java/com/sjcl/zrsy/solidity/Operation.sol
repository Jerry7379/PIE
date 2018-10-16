pragma solidity ^0.4.0;
contract Operation {

    struct Operation{
        bytes32[] operation;
        bytes32[] content;
        bytes32[] remark;
    }

    struct Pork{
        bytes32 porkID;//小猪id
        Operation[] operation;
    }

    mapping(bytes32 => Pork) porkMap;
    address FarmID; //养殖场id
    address SlaughterhouseID; //屠宰场id
    address MarketID; //超市id

    modifier onlyFarm(){
        require(msg.sender == FarmID);
        _;
    }

    modifier onlySlaughterhouse(){
        require(msg.sender == SlaughterhouseID);
        _;
    }

    modifier onlyMarket(){
        require(msg.sender == MarketID);
        _;
    }

    function insertFarmOperation(bytes32 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark, uint[] timestamps)external onlyFarm returns(bool,bytes32){
        Pork pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }

        porkMap[pork.porkID].operation.push(Operation({
            operation : operation,
            content : content,
            remark : remark
            }));
        return(true,"成功");

    }


    function insertSlaughterOperation(bytes32 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark, uint[] timestamps) external onlySlaughterhouse returns(bool,bytes32){
        Pork pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }

        porkMap[pork.porkID].operation.push(Operation({

            operation : operation,
            content : content,
            remark : remark
            }));

        return(true,"成功");

    }

    function insertMarketOperation(bytes32 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark,uint[] timestamps)external onlyMarket returns(bool,bytes32){
        Pork pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }
        porkMap[pork.porkID].operation.push(Operation({
            operation : operation,
            content : content,
            remark : remark
            }));
        return(true,"success");

    }

}
