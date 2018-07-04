package aconex.jessie.presentation;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.domain.IBallotServices;
import aconex.jessie.domain.BallotServices;

import java.util.ArrayList;
import java.util.List;

public class BallotManager {
    private List<Ballot> _ballots;
    private List<Candidate> _candidates;
    private IBallotServices _BallotServices;

    public BallotManager(){
        _ballots = new ArrayList<>();
        _BallotServices = new BallotServices();
    }

    public List<Ballot> GetBallots(){
        return this._ballots;
    }

    public void SetCandidates(List<Candidate> candidates){
        this._candidates = candidates;
    }

    public Ballot AddBallot(String voteString){
        Ballot newBallot = _BallotServices.GenerateBallot(_candidates, voteString);
        if(newBallot.Valid == true)
            _ballots.add(newBallot);

        return newBallot;
    }

    public List<Ballot> UpdateBallotWithRound(Round round){
        if (!round.Finished && round.EliminateCandidate != null)
            return _ballots;
        for (Ballot ballot : _ballots) {
            _BallotServices.ValidateExhausted(ballot);
        }
        return _ballots;
    }

}
