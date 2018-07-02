package aconex.jessie.domain;

import aconex.jessie.consts.Constants;
import aconex.jessie.core.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidatesServices {
    public List<Candidate> Generate(ArrayList<String> CandidateStrings){
        ArrayList<Candidate> candidates = new ArrayList<Candidate>(){};
        for (String str: CandidateStrings) {
            char index = (char)((int)Constants.START_INDEX + candidates.size());
            Candidate newCandidate = new Candidate(String.valueOf(index), str);
            candidates.add(newCandidate);
        }
        return candidates;
    }
}
