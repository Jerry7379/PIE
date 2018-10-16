pragma solidity ^0.4.24;

/**
 * 节点注册
 */
contract Registration {
    enum Kind {Farm, Slaughterhouse, LogisticsCompany, Supermarket}

    struct Regulator {
        address identifier;
    }

    struct Registrant {
        address identifier;
        string name;
        string location;
        string mail;
        Kind kind;
    }


    Regulator public regulator;
    Registrant[] public candidates;
    mapping(address => Registrant) public registrants;

    constructor() public {
        regulator = Regulator(msg.sender);
    }

    modifier onlyRegistrants() {
        require(msg.sender != regulator.identifier, "只允许注册人执行该操作");
        _;
    }

    modifier onlyRegulator() {
        require(msg.sender == regulator.identifier, "只允许监管人执行该操作");
        _;
    }

    function applyFor(Kind _kind, string _name, string _location, string _mail) public onlyRegistrants {
        require(registrants[msg.sender].identifier != msg.sender, "您已注册成功，请不要重复申请注册");
        bool inQueue = false;
        for(uint i = 0; i < candidates.length; i++) {
            if(candidates[i].identifier == msg.sender) {
                inQueue = true;
                break;
            }

        }
        require(!inQueue, "您已处于队列中，请等待结果");
        require(candidates.length < 1, "当前排队人数过多，请稍后再试");

        candidates.push(Registrant({
            identifier: msg.sender,
            name: _name,
            location: _location,
            mail: _mail,
            kind: _kind
            }));
    }



    function obtainACandidate() public view onlyRegulator returns (address, Kind, string, string, string){
        Registrant storage top = candidates[0];
        return (top.identifier, top.kind, top.name, top.location, top.mail);
    }

    function approve(address eligible) public onlyRegulator {

        require(registrants[eligible].identifier != eligible, "已经注册，不允许重复注册");

        for(uint i = 0; i < candidates.length; i++) {
            Registrant storage candidate = candidates[i];
            address identifier = candidate.identifier;
            if(eligible == identifier) {
                removeCandidate(i);
                registrants[eligible] = candidate;
                return;
            }
        }
        require(false, "候选人中不存在该注册者");
    }

    function reject(address notEligible) public onlyRegulator {
        for(uint i = 0; i < candidates.length; i++) {
            Registrant storage candidate = candidates[i];
            address identifier = candidate.identifier;
            if(notEligible == identifier) {
                removeCandidate(i);
                return;
            }
        }
    }

    function removeCandidate(uint index) private {
        if (index >= candidates.length) return;

        for (uint i = index; i<candidates.length-1; i++){
            candidates[i] = candidates[i+1];
        }
        candidates.length--;
    }
}
