package aconex.jessie.domain;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;

import java.util.List;

public interface IBallotServices {
    Ballot GenerateBallot(List<Candidate> candidates, String inputString);
    Ballot ValidateExhausted(Ballot ballot);
}
