package aconex.jessie.presentation;

import aconex.jessie.core.Candidate;
import aconex.jessie.domain.CandidatesServices;

import java.util.ArrayList;
import java.util.List;

public class CandidateManager {
    private List<Candidate> _candidate;
    private CandidatesServices _candidateListServices;

    public CandidateManager(){
        _candidateListServices = new CandidatesServices();
    }


    public List<Candidate> GenerateCandidates(String filepath){
        //Get candidate content from file

        ArrayList<String> candidates = new ArrayList<>();
        _candidate = _candidateListServices.Generate(candidates);
        return _candidate;
    }

    public List<Candidate> GetCandidate(){
        return _candidate;
    }
}
