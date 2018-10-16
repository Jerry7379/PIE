pragma solidity ^0.4.24;

///Ownable 合同具有所有者地址，并提供基本授权控制功能，这简化了“用户权限”的实现
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

///符合ERC721协议的接口
contract ERC721 {
    // Required methods
    //返回当前token的总数
    function totalSupply() public view returns (uint256 total);
    //返回获得某个给定地址拥有的代币数量
    function balanceOf(address _owner) public view returns (uint256 balance);
    //返回某代币的所有者的地址
    function ownerOf(uint256 _tokenId) external view returns (address owner);
    //该函数批准或授予另一个实体代表所有者转移token的权利
    function approve(address _to, uint256 _tokenId) external;
    //代币所有者将其代币发送给另一个用户的地址
    function transfer(address _to, uint256 _tokenId) external;
    function transferFrom(address _from, address _to, uint256 _tokenId) external;

    // Events
    event Transfer(address from, address to, uint256 tokenId);
    event Approval(address owner, address approved, uint256 tokenId);

    // Optional
    // function name() public view returns (string name);
    // function symbol() public view returns (string symbol);
    // function tokensOfOwner(address _owner) external view returns (uint256[] tokenIds);
    // function tokenMetadata(uint256 _tokenId, string _preferredTransport) public view returns (string infoUrl);

    // ERC-165 Compatibility (https://github.com/ethereum/EIPs/issues/165)
    function supportsInterface(bytes4 _interfaceID) external view returns (bool);
}


///谁控制合约，简单的权限管理
contract pigAccessControl {


    /**
     *government可以重新分配其他角色。
     *它也是唯一可以取消智能合约的角色。
     *它最初设置为在pigcore构造函数中创建智能合约的地址。
     */

    ///执行每个角色的协议地址
    //第三方地址
    address public governmentAdddress;
    //养殖场地址
    address public farmAddress;
    //屠宰场地址
    address public slaughterhouseAdddress;
    //物流公司地址
    address public logisticsAdddress;
    //超市地址
    address public marketAdddress;

    //管理协议是否被暂定，暂停时大多数行动都会被阻塞
    bool public paused = false;

    //仅限government功能的访问修饰符
    modifier onlyGovernment(){
        require(msg.sender == governmentAdddress);
        _;
    }

    //仅限farm功能的访问修饰符
    modifier onlyFarm(){
        require(msg.sender == farmAddress);
        _;
    }

    //仅限slaughterhouse功能的访问修饰符
    modifier onlySlaughterhouse(){
        require(msg.sender == slaughterhouseAdddress);
        _;
    }

    //仅限logistics功能的访问修饰符
    modifier onlyLogistics(){
        require(msg.sender == logisticsAdddress);
        _;
    }

    //仅限market功能的访问修饰符
    modifier onlyMarket(){
        require(msg.sender == marketAdddress);
        _;
    }

    //指定一个新地址担任government,仅可以由现任government调用。
    //_newGovernment为新government的地址
    function setGovernment(address _newGovernment)external onlyGovernment{
        require(_newGovernment != address(0));
        governmentAdddress = _newGovernment;
    }

    // 让Government指派一个新的farm
    function setFarm(address _newFarm) external onlyGovernment{
        require(_newFarm != address(0));
        farmAddress = _newFarm;
    }

    // 让Government指派一个新的slaughterhouse
    function setSlaughterhouse(address _newSlaughterhouse)external onlyGovernment{
        require(_newSlaughterhouse != address(0));
        slaughterhouseAdddress = _newSlaughterhouse;
    }

    // 让Government指派一个新的logistics
    function setLogistics(address _newLogistics)external onlyGovernment{
        require(_newLogistics != address(0));
        logisticsAdddress = _newLogistics;
    }

    // 让Government指派一个新的market
    function setMarket(address _newMarket)external onlyGovernment{
        require(_newMarket != address(0));
        marketAdddress = _newMarket;
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

/// 定义pig是什么，定义了pig的基本属性
contract pigBase is pigAccessControl{

    //只要新的猪出现，就会触发Birth事件。
    event Birth(uint256 pigID, address owner, uint64 birthTime, uint256 breed, uint256 weight);
    //每次转移猪所有权时都会触发。
    event Transfer(address from, address to, uint256 tokenId);

    /**
     * pig结构。
     */
    struct pig{
        //所有者地址
        address currentAddress;
        //饲养人ID
        //uint256 breederID;
        //出生时间
        uint64 birthTime;
        //品种
        uint256 breed;
        //性别
        //int8 gender;
        //体重
        uint256 weight;
    }

    //包含现有所有pig结构的数组,每只pig的ID是此数组的索引。
    pig[] pigs;

    //从pigID到主人的地址的映射。
    mapping(uint256 => address) public pigIndexToOwner;
    //从主人地址到他拥有的猪的个数的映射
    mapping(address => uint256) public ownershipTokenCount;
    // 从猪的ID到被允许交易的主人地址的映射，在transferFrom()中使用
    // 每只猪在任何时候只能有一个被允许的交易的地址
    // 0表示没有人被批准
    mapping(uint256 => address) public pigIndexToApproved;

    //Sell协议的地址，用来买卖猪
    //此协议处理了用户之间的买卖
    pigSale public transaction;

    //设置一只猪的主人地址
    function _transfer(address _from, address _to, uint256 _tokenId)internal{
        ownershipTokenCount[_to]++;
        // 设置主人
        pigIndexToOwner[_tokenId] = _to;
        // 需要规避原来主人是0x0的情况
        if(_from != address(0)){
            ownershipTokenCount[_from]--;
            // 同时清空允许交易的主人地址
            delete pigIndexToApproved[_tokenId];
        }

        //发出主人转换事件
        Transfer(_from, _to, _tokenId);
    }

    /**
     * 一种创建newpig并存储它的内部方法。
     * 此方法不进行任何检查，只应在已知输入数据有效时调用,因此输入数据要保证正确。
     * 将生成Birth事件和Transfer事件。
     */
    function _createPig(
        address _owner,
        uint64 _birthTime,
        uint256 _breed,
        uint256 _weight
    ) internal returns (uint){

        pig memory _pig = pig({
            currentAddress : _owner,
            birthTime : uint64(now),
            breed : _breed,
            weight : _weight
            });

        ///????
        uint256 newPigID = pigs.push(_pig) + 1;

        // 发出Birth事件
        Birth(newPigID, _owner,_birthTime, _breed, _weight);

        // 设置主人，并且发出Transfer事件
        // 遵循ERC721草案
        _transfer(0, _owner, newPigID);
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

    //判断一个地址能否交易一个猪。
    function _approvedFor(address _currentAddress, uint256 _tokenId) internal view returns (bool){
        return pigIndexToApproved[_tokenId] == _currentAddress;

    }

    //设置transferFrom()函数用到的可以交易的地址
    function _approve(uint256 _tokenId, address _approved) internal{
        pigIndexToApproved[_tokenId] = _approved;

    }

    //返回特定地址拥有的猪的数量。
    function balanceOf(address _owner)public view returns(uint256 count){
        return ownershipTokenCount[_owner];
    }

    //把猪转到另一个地址，要确保ERC-721兼容，否则可能丢失。
    function transfer(address _to, uint256 _tokenId)external whennotPaused{
        // 防止转移到0x0
        require(_to != address(0));
        require(_to !=address(this));
        // 只能转让自己的猪
        require(_owns(msg.sender,_tokenId));
        // 修改主人，发出Transfer事件
        _transfer(msg.sender, _to, _tokenId);
    }

    //赋予一个地址通过transferFrom()获得猪的权利。
    //_to 被授权的地址，ID为0是取消授权
    function approve(address _to, uint256 _tokenId)external whennotPaused{
        // 只有主人能够给予授权
        require(_owns(msg.sender, _tokenId));
        // 注册授权
        _approve(_tokenId, _to);
        // 发出Approval事件
        Approval(msg.sender, _to, _tokenId);
    }

    function transferFrom(address _from, address _to, uint256 _tokenId)external whennotPaused{
        // 安全检查，防止转给地址0
        require(_to != address(0));
        require(_to !=address(this));
        require(_approvedFor(msg.sender,_tokenId));
        require(_owns(_from, _tokenId));

        // 修改主人，发出Transfer事件
        _transfer(_from, _to, _tokenId);
    }

    //返回当前猪的总数
    function totalSupply()public view returns(uint){
        return pigs.length - 1;
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
        for(pigID =1; pigID <= tolalPigs; pigID++){
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
    //event Separation(address owner, uint256 pigID, uint256[] weight);

    //判断猪是否存在和
    function _isReadyToSeparation(pig _pig) internal view returns(bool){
        return(_pig.birthTime != 0) && (_pig.currentAddress == slaughterhouseAdddress);
    }

    function toSeparation(uint256 _pigID, uint256 _weight) external whennotPaused onlySlaughterhouse returns(uint256){

        // 通过PigID获取对象
        pig storage _pig = pigs[_pigID];
        // 通过birthTime验证是否是合法的猪
        require(_pig.birthTime != 0);
        require(_isReadyToSeparation(_pig));

        //????
        address owner = pigIndexToOwner[_pigID];
        uint256 pigID = _createPig(owner, uint64(now), 0, _weight);
        // 返回新猪的ID
        return pigID;
    }
}


//卖猪的基本功能
contract pigSaleBase is Ownable{

    //ERC-165用于ERC-721的接口签名
    bytes4 constant InterfaceSignature_ERC721 = bytes4(0x9a20483d);


    struct Sale{
        address seller;//卖家
        address buyer;//买家
        uint256 tokenId;
        uint256 price;//价格
        uint64 time;//售出时间
    }

    //参考合同跟踪NFT所有权,对NFT协议的引用
    ERC721 public NFT;

    //从ID映射到相应的交易
    mapping (uint256 => Sale) tokenIdToSale;

    event SaleCreated(uint256 tokenId, uint256 price, address buyer);
    event SaleSuccessful(uint256 tokenId, uint256 price, address buyer);
    event SaleCancelled(uint256 tokenId);

    //判断当前地址是否拥有token。
    function _owns(address _currentAddress, uint256 _tokenId)internal view returns(bool){
        return(NFT.ownerOf(_tokenId) == _currentAddress);
    }

    //把NFT的token转给协议
    function _escrow(address _owner, uint256 _tokenId) internal {
        NFT.transferFrom(_owner, this, _tokenId);
    }

    //把协议的NFT转到买家。
    function _transfer(address _buyer, uint256 _tokenId) internal {
        NFT.transfer(_buyer, _tokenId);
    }

    //将此次交易添加到列表中，同时触发SaleCreated事件
    function _addSale(uint256 _tokenId, Sale _sale) internal {
        //交易价格要大于0
        require(_sale.price >= 0);

        tokenIdToSale[_tokenId] = _sale;

        SaleCreated(
            uint256(_tokenId),
            uint256(_sale.price),
            address(_sale.buyer)
        );
    }

    //无条件取消一个交易
    function _cancelSale(uint256 _tokenId, address _seller) internal {
        _removeSale(_tokenId);
        _transfer(_seller,_tokenId);
        SaleCancelled(_tokenId);
    }

    //移除一个token的交易。
    function _removeSale(uint256 _tokenId) internal {
        delete tokenIdToSale[_tokenId];
    }

    //计算价格并转移奖金。
    //不转移令牌的所有权。
    function _pigSale (uint256 _tokenId, uint256 _currentPrice) internal returns(uint256){

        //获得拍卖的数据
        Sale storage sale = tokenIdToSale[_tokenId];

        // 在删除交易前预存卖家的地址。
        address  seller = sale.seller;
        // 在删除交易前预存买家的地址。
        address  buyer = sale.buyer;
        // 在删除交易前预存价格
        uint256  price = sale.price;

        require(_currentPrice == price);

        //在将费用发送给卖方之前删除交易。
        _removeSale(_tokenId);

        /**
         * 通过在调用transfer（）之前删除交易来防止重入攻击，
         * 并且卖家可以做的唯一事情就是出售他们自己的资产！
         * 在复杂的操作中包含transfer()是有风险的
         * 我们通过提前删除Auction来规避
        */

        //转移到卖方
        if(price > 0)
        {
            seller.transfer(price);
        }

        SaleSuccessful(_tokenId, price, buyer);
        return price;

    }


    //转移所有的以太币到NFT的协议地址，可以被NFT的地址或者本地址调用
    function withdrawBalance() external {
        address NFTAddress = address(NFT);

        require(msg.sender == owner || msg.sender == NFTAddress);

        bool request = NFTAddress.send(this.balance);
    }

    // 获得一个token的交易信息
    function getSale(uint256 _tokenId) external view returns
    (
        address seller,
        address buyer,
        uint256 price,
        uint64 time
    ){
        Sale storage sale = tokenIdToSale[_tokenId];
        return(
        sale.seller,
        sale.buyer,
        sale.price,
        sale.time
        );
    }
}

//卖猪
contract pigSale is pigSaleBase{

    //创建一个交易
    function createSale(
        address _seller,
        address _buyer,
        uint256 _tokenId,
        uint256 _price

    )
    external
    {
        require(_owns(msg.sender, _tokenId));
        _escrow(msg.sender, _tokenId);
        Sale memory sale = Sale
        (
            _seller,
            _buyer,
            _tokenId,
            _price,
            uint64(now)
        );
        _addSale(_tokenId,sale);
    }

    //交易，进行token的转移
    function isSale(uint256 _tokenId) external payable{
        //address seller = tokenIdToSale[_tokenId].seller;
        address buyer = tokenIdToSale[_tokenId].buyer;
        uint256 price = _pigSale(_tokenId, msg.value);
        _transfer(buyer, _tokenId);
    }




}

//处理交易
contract Sell is pigOwnership,pigSale{

    function createSell(address _seller, address _buyer, uint256 _pigID,  uint256 _price) external whennotPaused
    {
        //交易协议检查输入参数
        // 如果已经在交易会失败
        require(_owns(msg.sender, _pigID));
        _approve(_pigID, transaction);
        transaction.createSale(
            _seller,
            _buyer,
            _pigID,
            _price
        );
    }

    //转移交易的收入
    function withdrawSaleBalances() external {
        transaction.withdrawBalance();
    }
}

///创建猪
contract pigMinting is Sell{

    uint256 public constant PIG_BASIC_COUNT = 10;

    //记录创建的猪的个数
    uint256 public pigCreatedCount;

    //创建新猪，并且发布交易
    function createPigSale(uint256 _weight, uint256 _tokenId, uint256 _breed) external {

        address _buyer = tokenIdToSale[_tokenId].buyer;
        uint256 _price = _pigSale(_tokenId,msg.value);

        // require(pigCount > 0);
        uint256 pigID = _createPig(address(this),uint64(now),_breed,_weight);
        _approve(pigID, transaction);

        transaction.createSale(
            address(this),
            _buyer,
            pigID,
            _price
        );
        pigCreatedCount++;
    }


}


///主协议
contract pigCore is pigMinting{

    //创建PIE智能合约实例
    function pigCore() public {
        //创建各类角色
        governmentAdddress = msg.sender;
        farmAddress = msg.sender;
        slaughterhouseAdddress = msg.sender;
        logisticsAdddress = msg.sender;
        marketAdddress = msg.sender;

        //出现新猪
        _createPig(address(0), uint64(now), 0, 0);
    }

    function withdrawBalance() external {
        uint256 balance = this.balance;

        require(msg.sender == farmAddress || msg.sender == slaughterhouseAdddress
        || msg.sender == logisticsAdddress || msg.sender == marketAdddress);

        msg.sender.send(balance);
    }


    function getPig(uint256 _id) external view returns(
        address currentAddress,
        uint64 birthTime,
        uint256 breed,
        uint256 weight
    ){

        pig storage Pig = pigs[_id];
        currentAddress = address(Pig.currentAddress);
        birthTime = uint64(Pig.birthTime);
        breed  = uint256(breed);
        weight = uint256(weight);

    }
}