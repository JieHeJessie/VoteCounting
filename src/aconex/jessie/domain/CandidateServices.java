package aconex.jessie.domain;

import aconex.jessie.consts.Constants;
import aconex.jessie.core.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateServices implements ICandidateServices {
    /*
    * Generate all candidates with input strings
    * */
    public List<Candidate> Generate(ArrayList<String> candidateStrings){
        ArrayList<Candidate> candidates = new ArrayList<Candidate>(){};
        for (int id = 0; id < candidateStrings.size(); id ++) {
            if (id >= Constants.MAX_CANDIDATES)
                break;
            String candidateStr = candidateStrings.get(id);
            // calculate index
            char index = (char)((int)Constants.START_INDEX + candidates.size());
            Candidate newCandidate = new Candidate(String.valueOf(index), candidateStr);
            candidates.add(newCandidate);
        }
        return candidates;
    }
}
