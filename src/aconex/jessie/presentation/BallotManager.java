package aconex.jessie.presentation;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Round;
import aconex.jessie.domain.BallotServices;

import java.util.ArrayList;
import java.util.List;

public class BallotManager {
    private List<Ballot> _ballots;
    private BallotServices _BallotServices;

    public BallotManager(){
        _ballots = new ArrayList<>();
        _BallotServices = new BallotServices();
    }

    public List<Ballot> GetBallots(){
        return this._ballots;
    }

    public Ballot AddBallot(String voteString){
        Ballot newBallot = _BallotServices.GenerateBallot(voteString);
        if(newBallot.Valid == true)
            _ballots.add(newBallot);

        return newBallot;
    }

    public List<Ballot> UpdateBallotWithRound(Round round){
        if (!round.Finished && round.EliminateCandidate != null)
            return _ballots;

        List<Ballot> newBallots = new ArrayList<>();
        for (Ballot ballot : _ballots) {
            Ballot newBallot = _BallotServices.RemoveCandidate(ballot, round.EliminateCandidate);
            if (newBallot.Valid && !newBallot.Exhausted){
                newBallots.add(newBallot);
            }
        }
        _ballots = newBallots;
        return _ballots;
    }

}
