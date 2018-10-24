pragma solidity ^0.4.0;
contract Operation {

    struct Operation{
        bytes32[] operation;
        bytes32[] content;
        bytes32[] remark;
    }

    struct Pork{
        uint256 porkID;//小猪id
        Operation[] operation;
    }

    Pork[] porks;

    mapping(uint256 => Pork) porkMap;
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

    function insertFarmOperation(uint256 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark, uint[] timestamps)public view onlyFarm returns(bool,bytes32){
        Pork storage pork = porkMap[porkID];
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


    function insertSlaughterOperation(uint256 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark,uint[] timestamps)public view onlySlaughterhouse returns(bool,bytes32){
        Pork storage pork = porkMap[porkID];
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

    function insertMarketOperation(uint256 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark,uint[] timestamps)public view onlyMarket returns(bool,bytes32){
        Pork storage pork = porkMap[porkID];
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

    function getOperation(uint256 _pigID)external view
    returns(
        bytes32[] operation,
        bytes32[] content,
        bytes32[] remark
    ){
        Pork storage pork = porkMap[_pigID];
        porkMap[pork.porkID].operation.push(Operation({
            operation : operation,
            content : content,
            remark : remark
            }));
    }

}
