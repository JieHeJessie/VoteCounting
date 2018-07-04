import aconex.jessie.core.Candidate;
import aconex.jessie.domain.CandidateServices;
import aconex.jessie.domain.ICandidateServices;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CandidateServicesTest {

    private ICandidateServices _candidateServices = new CandidateServices();

    //generate candidate
    @Test
    public void GenerateCandidateTest(){
        ArrayList<String> mockInputString = new ArrayList<String>(){{
            add("AAAAAA");
        }};

        String actualIndex = _candidateServices.Generate(mockInputString).get(0).Index;
        String expectedIndext = "A";

        String actualName = _candidateServices.Generate(mockInputString).get(0).Name;
        String expectedName = "AAAAAA";

        assertEquals("failure mockInputString Index", expectedIndext, actualIndex);
        assertEquals("failure mockInputString Name", expectedName, actualName);

    }

    //Invalid candidate
    @Test
    public void GenerateInvalidCandidateTest(){
        ArrayList<String> mockInputString = new ArrayList<String>(){{
            add("1");add("2");add("3");add("4");add("5");
            add("6");add("7");add("8");add("9");add("10");
            add("11");add("12");add("13");add("14");add("15");
            add("16");add("17");add("18");add("19");add("20");
            add("21");add("22");add("23");add("24");add("25");add("26");add("27");
        }};

        List<Candidate> allCandidates = _candidateServices.Generate(mockInputString);
        assertEquals("Max candidate length 26", 26, allCandidates.size());
    }
}
