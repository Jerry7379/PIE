pragma solidity ^0.4.24;



//Ownable 合同具有所有者地址，并提供基本授权控制功能，这简化了“用户权限”的实现
contract Ownable{
    address public owner;

    //该构造函数将合约“所有者”设置为部署合约的地址。
    function Ownable(){
        owner = msg.sender;
    }

    //不允许所有者以外的任何帐户调用。
    modifier onlyOwner(){
        require(msg.sender == owner);
        _;
    }

    //允许当前所有者将合同的控制权转移给newOwner。
    function transferOwnership(address newOwner)onlyOwner{
        if(newOwner != address(0)){
            owner =newOwner;
        }
    }
}



contract pigAccessControl is Ownable{

    /**
     *government可以重新分配其他角色。
     *它最初设置为在pigcore构造函数中创建智能合约的地址。
     */

    ///执行每个角色的协议地址
    //第三方地址
    address public governmentAdddress;
    User public user;

    //管理协议是否被暂定，暂停时大多数行动都会被阻塞
    bool public paused = false;

    enum Role{Farm, Slaugthethouse, Logistics, Market}//角色

    struct User{
        address roleAddress;
        uint256 roleId;
        string location;
        Role role;
    }



    mapping (address => User) public FarmMap;
    mapping (address => User) public SlaughterhouseMap;
    mapping (address => User) public LogisticsMap;
    mapping (address => User) public MarkeMap;





    //仅限government功能的访问修饰符
    modifier onlyGovernment(){
        require(msg.sender == governmentAdddress);
        _;
    }

    //仅限farm功能的访问修饰符
    modifier onlyFarm(){
        require(msg.sender == user.roleAddress);
        _;
    }

    //仅限slaughterhouse功能的访问修饰符
    modifier onlySlaughterhouse(){
        require(msg.sender == user.roleAddress);
        _;
    }

    //仅限logistics功能的访问修饰符
    modifier onlyLogistics(){
        require(msg.sender == user.roleAddress);
        _;
    }

    //仅限market功能的访问修饰符
    modifier onlyMarket(){
        require(msg.sender == user.roleAddress);
        _;
    }

    //指定一个新地址担任government,仅可以由现任government调用。
    //_newGovernment为新government的地址
    function setGovernment(address _newGovernment)external onlyOwner {
        require(_newGovernment != address(0));
        governmentAdddress = _newGovernment;
    }


    function newUser(uint256 ID, string location,Role role) returns(bool, address,Role,string){

        if(role == Role.Farm){
            //user = FarmMap[msg.sender];
            user.roleAddress = msg.sender;
            user.roleId = ID;
            user.location = location;
            user.role = role;
            FarmMap[msg.sender] = user;
        }else if(role == Role.Slaugthethouse){
            //user = SlaughterhouseMap[msg.sender];
            user.roleAddress = msg.sender;
            user.roleId = ID;
            user.location = location;
            user.role = role;
            SlaughterhouseMap[msg.sender] = user;
        }else if(role == Role.Logistics){
            // user = LogisticsMap[msg.sender];
            user.roleAddress = msg.sender;
            user.roleId = ID;
            user.location = location;
            user.role = role;
            LogisticsMap[msg.sender] = user;
        }else if(role == Role.Market){
            //user = MarkeMap[msg.sender];
            user.roleAddress = msg.sender;
            user.roleId = ID;
            user.location = location;
            user.role = role;
            MarkeMap[msg.sender] = user;
        }else{
            return (false,user.roleAddress, user.role,"the actor is not belong");
        }
        if(user.roleId != 0x0){
            return (false, user.roleAddress, user.role,"this ID has been occupied!");
        }
        user.roleAddress = msg.sender;
        user.roleId = ID;
        user.location = location;
        user.role = role;
        return (true, user.roleAddress, user.role,"Success");
    }




    //该修饰符仅在合约未暂停时才允许操作
    modifier whennotPaused{
        require(!paused);
        _;
    }

    //该修饰符仅在合约暂停时才允许操作
    modifier whenPaused{
        require(paused);
        _;
    }

    //只有government角色调用，以暂停合同,仅在检测到错误或漏洞时使。
    function pause() external onlyGovernment whennotPaused{
        paused == true;
    }

    //只有government角色调用，以取消暂停合同,仅在检测到错误或漏洞时使。
    function unpause() public onlyGovernment whenPaused {
        paused == false;
    }
}




///符合ERC721协议的接口
contract ERC721 {
    // Required methods
    //返回当前token的总数
    function totalSupply() public view returns (uint256 total);
    //返回获得某个给定地址拥有的代币数量
    function balanceOf(address _owner) public view returns (uint256 balance);
    //返回某代币的所有者的地址
    function ownerOf(uint256 _tokenId) external view returns (address owner);
    //代币所有者将其代币发送给另一个用户的地址
    function transfer(address _to, uint256 _tokenId) external;

    // Events
    event Transfer(address from, address to, uint256 tokenId);

    function tokensOfOwner(address _owner) external view returns (uint256[] tokenIds);

    // ERC-165 Compatibility (https://github.com/ethereum/EIPs/issues/165)
    function supportsInterface(bytes4 _interfaceID) external view returns (bool);
}



/// 定义pig是什么，定义了pig的基本属性
contract pigBase is pigAccessControl{

    //只要新的猪出现，就会触发Birth事件。
    event Birth(uint256 pigID, address owner, uint64 birthTime, uint256 breed, uint256 weight, int8 gender);
    //每次转移猪所有权时都会触发。
    event Transfer(address from, address to, uint256 tokenId);

    /**
     * pig结构。
     */
    struct pig{
        //所有者地址
        address currentAddress;
        //出生时间
        uint64 birthTime;
        //品种
        uint256 breed;
        //体重
        uint256 weight;
        //性别
        int8 gender;
    }

    //包含现有所有pig结构的数组,每只pig的ID是此数组的索引。
    pig[] pigs;

    //从pigID到主人的地址的映射。
    mapping(uint256 => address) public pigIndexToOwner;
    //从主人地址到他拥有的猪的个数的映射
    mapping(address => uint256) public ownershipTokenCount;


    //设置一只猪的主人地址
    function _transfer(address _from, address _to, uint256 _tokenId)internal{
        ownershipTokenCount[_to]++;
        // 设置主人
        pigIndexToOwner[_tokenId] = _to;
        // 需要规避原来主人是0x0的情况
        if(_from != address(0)){
            ownershipTokenCount[_from]--;
        }

        //发出主人转换事件
        Transfer(_from, _to, _tokenId);
    }

    /**
     * 一种创建newpig并存储它的内部方法。
     * 此方法不进行任何检查，只应在已知输入数据有效时调用,因此输入数据要保证正确。
     * 将生成Birth事件和Transfer事件。
     */
    function createPig (
        uint256 _breed,
        uint256 _weight,
        int8 _gender
    ) external returns (uint256) {

        pig memory _pig = pig({
            currentAddress : msg.sender,
            birthTime : uint64(now),
            breed : _breed,
            weight : _weight,
            gender : _gender
            });

        uint256 newPigID = pigs.push(_pig) - 1;

        // 发出Birth事件
        emit Birth(newPigID, msg.sender,uint64(now), _breed, _weight, _gender);

        // 设置主人，并且发出Transfer事件
        // 遵循ERC721草案
        _transfer(0, msg.sender, newPigID);
        return newPigID;
    }
}

/// 合约继承自KittyBase和ERC721实现了ERC721接口中定义的方法。定义了整个合约的名称和单位
contract pigOwnership is pigBase,ERC721{

    //基于ERC721，Name和symbol都是不可分割的Token
    string public constant name = "Pig’s Life";
    string public constant symbol = "PIE";

    bytes4 constant InterfaceSignature_ERC165 =
    bytes4(keccak256('supportsInterface(bytes4)'));

    bytes4 constant InterfaceSignature_ERC721 =
    bytes4(keccak256('name()')) ^
    bytes4(keccak256('symbol()')) ^
    bytes4(keccak256('totalSupply()')) ^
    bytes4(keccak256('balanceOf(address)')) ^
    bytes4(keccak256('ownerOf(uint256)')) ^
    bytes4(keccak256('approve(address,uint256)')) ^
    bytes4(keccak256('transfer(address,uint256)')) ^
    bytes4(keccak256('transferFrom(address,address,uint256)')) ^
    bytes4(keccak256('tokensOfOwner(address)')) ^
    bytes4(keccak256('tokenMetadata(uint256,string)'));

    //判断是否是自己支持的ERC721或ERC165接口
    function supportsInterface(bytes4 _interfaceID)external view returns(bool){
        return((_interfaceID == InterfaceSignature_ERC721) || (_interfaceID == InterfaceSignature_ERC165));
    }

    // 判断一个地址是否是猪的主人。
    // _currentAddress 判断的用户的地址
    function _owns(address _currentAddress, uint256 _tokenId) internal view returns (bool){
        return pigIndexToOwner[_tokenId] == _currentAddress;
    }


    //返回特定地址拥有的猪的数量。
    function balanceOf(address _owner)public view returns(uint256 count){
        return ownershipTokenCount[_owner];
    }

    //把猪转到另一个地址，要确保ERC-721兼容，否则可能丢失。
    function transfer(address _to, uint256 _tokenId) external {
        // 防止转移到0x0
        require(_to != address(0));
        require(_to !=address(this));
        // 只能转让自己的猪
        require(_owns(msg.sender,_tokenId));
        // 修改主人，发出Transfer事件
        _transfer(msg.sender, _to, _tokenId);
        pig storage Pig = pigs[_tokenId];
        Pig.currentAddress = pigIndexToOwner[_tokenId];
        // pigs.push(Pig);
        emit Transfer(msg.sender, _to, _tokenId);
    }


    //返回当前猪的总数
    function totalSupply()public view returns(uint){
        return pigs.length;
    }

    //返回一个猪的主人
    function ownerOf(uint256 _tokenId)external view returns(address owner){
        owner = pigIndexToOwner[_tokenId];
        require(owner != address(0));
    }

    // 返回一个主人的猪的列表
    function tokensOfOwner(address _owner)external view returns(uint256[] ownerTokens){
        // 获得_owner拥有的猪的数量
        uint256 tokenCount = balanceOf(_owner);

        if(tokenCount == 0){
            // 如果没有猪，则为空
            return new uint256[](0);
        }else{
            //声明并初始化一个返回值result，长度为tokenCount
            uint256[] memory result = new uint256[](tokenCount);
            // 当前所有猪的数量
            uint256 tolalPigs = totalSupply();
            // 循环的初始值
            uint256 resultIndex = 0;
        }

        // 猪的ID从1开始自增
        uint256 pigID;
        // 从1开始循环遍历所有的tolalPigs
        for(pigID =0; pigID < tolalPigs; pigID++){
            // 判断当前pigID的拥有者是否为_owner
            if(pigIndexToOwner[pigID] == _owner){
                // 如果是，将pigID放入result数组resultIndex位置
                result[resultIndex] = pigID;
                resultIndex++;
            }
        }
        return result;
    }

}


contract pigSeparation is pigOwnership{
    //？？？？？？
    //？？？？？？
}


///主协议
contract pigCore is pigOwnership{


    function getPig(uint256 _id) external view returns(
        address currentAddress,
        uint64 birthTime,
        uint256 breed,
        uint256 weight,
        int8 gender
    ){

        pig storage Pig = pigs[_id];
        currentAddress = address(Pig.currentAddress);
        birthTime = uint64(Pig.birthTime);
        breed  = uint256(Pig.breed);
        weight = uint256(Pig.weight);
        gender  = int8(Pig.gender);

    }

}