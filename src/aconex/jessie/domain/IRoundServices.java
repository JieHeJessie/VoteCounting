package aconex.jessie.domain;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;

import java.util.List;

public interface IRoundServices {
    Round GenerateRound(List<Ballot> ballots, List<Candidate> allCandidates);
}
