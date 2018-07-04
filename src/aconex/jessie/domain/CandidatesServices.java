package aconex.jessie.domain;

import aconex.jessie.consts.Constants;
import aconex.jessie.core.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidatesServices {
    /*
    * Generate all candidates with input strings
    * */
    public List<Candidate> Generate(ArrayList<String> CandidateStrings){
        ArrayList<Candidate> candidates = new ArrayList<Candidate>(){};

        if(CandidateStrings.size()>26){
            System.out.println(Constants.INVALID_CANDIDATES);
        }else{
            for (String str: CandidateStrings) {
                // calculate index
                char index = (char)((int)Constants.START_INDEX + candidates.size());
                Candidate newCandidate = new Candidate(String.valueOf(index), str);
                candidates.add(newCandidate);
            }
        }

        return candidates;
    }
}
