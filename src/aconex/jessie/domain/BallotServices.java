package aconex.jessie.domain;

import aconex.jessie.consts.ErrorType;
import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Vote;

import java.util.List;

public class BallotServices implements IBallotServices {
    /*
    * Generate ballot with user input
    * Validate input error
    * */
    public Ballot GenerateBallot(List<Candidate> candidates, String inputString){
        Ballot ballot = new Ballot();
        ballot.InputString = inputString;

        String[] inputVotes = inputString.replaceAll("\\s+","").split("");
        for(int index = 0; index < inputVotes.length; index++){
            // generate each vote
            String inputStr = inputVotes[index];
            Vote vote = new Vote(index, inputStr);
            // fine candidate by input
            vote.Candidate = candidates.stream()
                    .filter(c -> c.Index.equals(inputStr.toUpperCase()))
                    .findFirst().orElse(null);
            // check weather the vote is valid
            vote = validateVote(vote, ballot.Votes);
            ballot.Votes.add(vote);
            if (!vote.Valid)
                ballot.Valid = false;
        }

        return ballot;
    }

    /*
    * Check if the ballot is exhausted and update that ballot
    * */
    public Ballot ValidateExhausted(Ballot ballot){
        boolean remaining = ballot.Votes.stream()
                .anyMatch(vote -> !vote.Candidate.Eliminate);
        ballot.Exhausted = !remaining;
        return ballot;
    }

    /*
    * Validate vote:
    * Check if it is duplicated or invalid input
    * */
    private Vote validateVote(Vote vote, List<Vote> existVotes){
        vote.Valid = true;
        if (vote.Candidate == null){
            vote.Error = ErrorType.InvalidInput;
            vote.Valid = false;
            return vote;
        }

        boolean isExist = false;
        for (Vote exist: existVotes) {
            if(exist.Candidate != null && exist.Candidate.equals(vote.Candidate))
                isExist = true;
        }
        if (isExist == true){
            vote.Valid = false;
            vote.Error = ErrorType.DuplicateInput;
        }
        return vote;
    }


}
