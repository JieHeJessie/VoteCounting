import java.util.ArrayList;
import java.util.List;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.domain.BallotServices;
import aconex.jessie.domain.IBallotServices;
import aconex.jessie.domain.IRoundServices;
import aconex.jessie.domain.RoundServices;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RoundServicesTest {
    private IRoundServices _roundService;
    private IBallotServices _ballotService;

    public RoundServicesTest(){
        _roundService = new RoundServices();
        _ballotService = new BallotServices();
    }

    @Test
    public void testGenerateRoundSuccess() {
        List<Candidate> candidates = new ArrayList<Candidate>(){{
            add(new Candidate("A", "AAAA"));
            add(new Candidate("B", "BBBB"));
            add(new Candidate("C", "CCCC"));
            add(new Candidate("D", "DDDD"));
        }};

        List<Ballot> ballots = new ArrayList<>();
        ballots.add(_ballotService.GenerateBallot(candidates, "abdc"));
        ballots.add(_ballotService.GenerateBallot(candidates, "bad"));
        ballots.add(_ballotService.GenerateBallot(candidates, "cabd"));
        ballots.add(_ballotService.GenerateBallot(candidates, "cdab"));
        ballots.add(_ballotService.GenerateBallot(candidates, "da"));
        ballots.add(_ballotService.GenerateBallot(candidates, "db"));
        ballots.add(_ballotService.GenerateBallot(candidates, "bac"));
        ballots.add(_ballotService.GenerateBallot(candidates, "cbad"));

        Round round0 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round0: eliminate A", "A", round0.EliminateCandidate.Index);
        assertEquals("Round0: Success", false, round0.Success);

        Round round1 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round1: eliminate D", "D", round1.EliminateCandidate.Index);
        assertEquals("Round1: Success", false, round1.Success);

        Round round2 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round2: eliminate C", "C", round2.EliminateCandidate.Index);
        assertEquals("Round2: Success", false, round2.Success);

        Round round3 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round3: Success", true, round3.Success);
        assertEquals("Round3: Result B", "B", round3.Result.Index);
    }

    @Test
    public void testGenerateResultSuccess() {
        List<Candidate> candidates = new ArrayList<Candidate>(){{
            add(new Candidate("A", "AAAA"));
            add(new Candidate("B", "BBBB"));
        }};

        List<Ballot> ballots = new ArrayList<>();
        ballots.add(_ballotService.GenerateBallot(candidates, "a"));
        ballots.add(_ballotService.GenerateBallot(candidates, "ba"));

        Round round0 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round0: eliminate B", "B", round0.EliminateCandidate.Index);
        assertEquals("Round0: Success", false, round0.Success);

        Round round1 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round1: Success", true, round1.Success);
        assertEquals("Round1: Result A", "A", round1.Result.Index);
    }

    @Test
    public void testGenerateResultFinishWithFalse() {
        List<Candidate> candidates = new ArrayList<Candidate>(){{
            add(new Candidate("A", "AAAA"));
            add(new Candidate("B", "BBBB"));
        }};

        List<Ballot> ballots = new ArrayList<>();
        ballots.add(_ballotService.GenerateBallot(candidates, "ab"));
        ballots.add(_ballotService.GenerateBallot(candidates, "ba"));

        Round round0 = _roundService.GenerateRound(ballots, candidates);
        assertEquals("Round0: Finished", true, round0.Finished);
        assertEquals("Round0: Success", false, round0.Success);
    }

    @Test
    public void testFinishWithEliminate() {
        List<Candidate> candidates = new ArrayList<Candidate>(){{
            add(new Candidate("A", "AAAA"));
            add(new Candidate("B", "BBBB"));
            add(new Candidate("C", "CCCC"));
        }};

        List<Ballot> ballots = new ArrayList<>();
        ballots.add(_ballotService.GenerateBallot(candidates, "ab"));
        ballots.add(_ballotService.GenerateBallot(candidates, "ba"));
        ballots.add(_ballotService.GenerateBallot(candidates, "ab"));
        ballots.add(_ballotService.GenerateBallot(candidates, "ba"));

        _roundService.GenerateRound(ballots, candidates);
        _roundService.GenerateRound(ballots, candidates);
        assertEquals("Candidate A Exist", false, candidates.get(0).Eliminate);
        assertEquals("Candidate B Exist", false, candidates.get(1).Eliminate);
        assertEquals("Candidate C Exhausted", true, candidates.get(2).Eliminate);

    }




}