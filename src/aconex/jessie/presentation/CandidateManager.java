package aconex.jessie.presentation;

import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.domain.CandidatesServices;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandidateManager {
    private List<Candidate> _candidate;
    private CandidatesServices _candidateListServices;

    public CandidateManager(){
        _candidateListServices = new CandidatesServices();
    }

    public List<Candidate> GetCandidate(){
        return _candidate;
    }

    public List<Candidate> GenerateCandidates(String filepath){
        //Get candidate content from file
        ArrayList<String> candidateLines = GetFileContent(filepath);
        _candidate = _candidateListServices.Generate(candidateLines);
        return _candidate;
    }

    private ArrayList<String> GetFileContent(String fileName){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        int candidateCount = 0;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null &&
                    candidateCount < 24) {
                lines.add(line);
                candidateCount ++;
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
        return lines;
    }
}
