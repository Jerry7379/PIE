pragma solidity ^0.4.0;
contract Operation {

    struct Operation{
        bytes32[] operation;
        bytes32[] content;
        bytes32[] remark;
        uint[] timestamps;//时间戳
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
        bytes32 pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }

        porkMap[pork.porkID].insertFarmOperation.push(Operation({
            operation : operation,
            content : content,
            remark : remark,
            timestamps : timestamps
            }));
        return(true,"成功");

    }


    function insertSlaughterOperation(bytes32 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark, bytes32 isacid, bytes32 acider_id, uint[] timestamps) external onlySlaughterhouse returns(bool,bytes32){
        Pork pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }

        porkMap[pork.porkID].insertSlaughterOperation.push(Operation({

            operation : operation,
            content : content,
            remark : remark,
            timestamps : timestamps
            }));

        return(true,"成功");

    }

    function insertMarketOperation(bytes32 porkID,bytes32[] operation, bytes32[] content,
        bytes32[] remark,uint[] timestamps)external onlyMarket returns(bool,bytes32){
        Pork pork = porkMap[porkID];
        if (pork.porkID == 0x0 ){
            return (false,"porkID存在错误");
        }
        porkMap[pork.porkID].insertMarketOperation.push(Operation({
            operation : operation,
            content : content,
            remark : remark,
            timestamps : timestamps
            }));
        return(true,"success");

    }

}
