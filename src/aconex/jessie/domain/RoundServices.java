package aconex.jessie.domain;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Round;

import java.util.List;

public class RoundServices {
    public Round GenerateRound(int roundID, List<Ballot> ballots){
        return new Round();
    }
}
