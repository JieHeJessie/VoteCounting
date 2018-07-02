package aconex.jessie.domain;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;

public class BallotServices {
    public Ballot GenerateBallot(String inputBallot){
        return new Ballot();
    }

    // Remove candidate from ballot, update other vote preference
    public Ballot RemoveCandidate(Ballot ballot, Candidate candidate){
        return ballot;
    }


    private boolean validateInputString(String inputVote){
        return false;
    }


}
