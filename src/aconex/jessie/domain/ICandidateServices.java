package aconex.jessie.domain;

import aconex.jessie.core.Candidate;

import java.util.ArrayList;
import java.util.List;

public interface ICandidateServices {
    List<Candidate> Generate(ArrayList<String> CandidateStrings);
}
