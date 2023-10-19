// SPDX-License-Identifier: MIT
pragma solidity ^0.8.8;

contract Voting {
    struct Proposal {
        string name;
        uint256 voteCount;
    }

    address public chairperson;
    mapping(address => bool) public voters;
    Proposal[] public proposals;


    constructor(string[] memory proposalNames) {
        chairperson = msg.sender;
        voters[chairperson] = true;

        for (uint256 i = 0; i < proposalNames.length; i++) {
            proposals.push(Proposal({
                name : proposalNames[i],
                voteCount : 0
            }));
        }
    }

    function giveRightVote(address voter) public {
        require(msg.sender == chairperson, "Only chairperson can give the right to vote.");
        require(!voters[voter], "The voter already has the right to vote.");
        voters[voter] = true;
    }

    function vote(uint256 proposalIndex) public {
        require(voters[msg.sender], "Only voters can vote.");
        require(proposalIndex < proposals.length, "Invalid proposal index.");
        proposals[proposalIndex].voteCount++;
    }

    function winningProposal() public view returns (string memory) {
        uint256 winningVoteCount = 0;
        uint256 winningProposalIndex = 0;

        for (uint256 i = 0; i < proposals.length; i++) {
            if (proposals[i].voteCount > winningVoteCount) {
                winningVoteCount = proposals[i].voteCount;
                winningProposalIndex = i;
            }
        }

        return proposals[winningProposalIndex].name;
    }
}