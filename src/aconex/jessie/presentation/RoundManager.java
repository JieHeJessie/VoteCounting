package aconex.jessie.presentation;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.domain.RoundServices;

import java.util.ArrayList;
import java.util.List;

public class RoundManager {
    private List<Round> _rounds;
    private RoundServices _roundServices;

    public RoundManager(){
        _rounds = new ArrayList<>();
        _roundServices = new RoundServices();
    }

    public Round CountUpRound(List<Ballot> ballots, List<Candidate> candidates){
        // Check if calculate finished

        // generate this rounds
        Round newRound = _roundServices.GenerateRound(ballots, candidates);
        _rounds.add(newRound);
        return newRound;
    }

    public int GetRoundNumber(){
        return _rounds.size();
    }

}
