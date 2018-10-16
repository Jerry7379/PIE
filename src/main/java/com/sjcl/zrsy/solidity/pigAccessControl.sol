pragma solidity ^0.4.0;

contract pigAccessControl {

    address public govenmentAdddress;
    address public farmAdddress;
    address public slaughterhouseAdddress;
    address public logisticsAdddress;
    address public marketAdddress;

    modifier onlyGovenmnet(){
        require(msg.sender == govenmentAdddress);
        _;
    }

    modifier onlyFarm(){
        require(msg.sender == farmAdddress);
        _;
    }

    modifier onlySlaughterhouse(){
        require(msg.sender == slaughterhouseAdddress);
        _;
    }

    modifier onlyLogistics(){
        require(msg.sender == logisticsAdddress);
        _;
    }

    modifier onlyMarket(){
        require(msg.sender == marketAdddress);
        _;
    }

    function setGovenment(address _newGovenment) external onlyGovenmnet{
        require(_newGovenment != address(0));
        govenmentAdddress = _newGovenment;
    }

    function setFarm(address _newFarm) external onlyGovenmnet{
        require(_newFarm != address(0));
        farmAdddress = _newFarm;
    }

    function setSlaughterhouse(address _newSlaughterhouse)external onlyGovenmnet{
        require(_newSlaughterhouse != address(0));
        slaughterhouseAdddress = _newSlaughterhouse;
    }

    function setLogistics(address _newLogistics)external onlyGovenmnet{
        require(_newLogistics != address(0));
        logisticsAdddress = _newLogistics;
    }

    function setMarket(address _newMarket)external onlyGovenmnet{
        require(_newMarket != address(0));
        marketAdddress = _newMarket;
    }
}
