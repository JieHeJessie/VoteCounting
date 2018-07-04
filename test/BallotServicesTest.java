import aconex.jessie.consts.ErrorType;
import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.domain.BallotServices;
import aconex.jessie.domain.IBallotServices;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class BallotServicesTest {

    private IBallotServices _ballotServices = new BallotServices();
    private List<Candidate> mockCandidates = new ArrayList<Candidate>(){{
        add(new Candidate("A", "AAAA"));
        add(new Candidate("B", "BBBB"));
        add(new Candidate("C", "CCCC"));
    }};

    @Test
    public void GenerateBallotDuplicateInputTest(){
        String mockDuplicateInput = "aa";
        Ballot ballot = _ballotServices.GenerateBallot(
                mockCandidates, mockDuplicateInput);

        boolean expectBallotValid = false;
        boolean expectVote2Valid = false;
        ErrorType expectVote2Error = ErrorType.DuplicateInput;

        assertEquals("Ballot Invalid", expectBallotValid, ballot.Valid);
        assertEquals("Has one vote invalid", expectVote2Valid, ballot.Votes.get(1).Valid);
        assertEquals("One vote with Duplicate Error", expectVote2Error, ballot.Votes.get(1).Error);
    }

    @Test
    public void GenerateBallotInvalidInoutTest(){
        String mockInvalidInput = "az";
        Ballot ballot = _ballotServices.GenerateBallot(mockCandidates, mockInvalidInput);

        boolean expectBallotValid = false;
        boolean expectVote2Valid = false;
        ErrorType expectVote2Error = ErrorType.InvalidInput;

        assertEquals("Ballot Invalid", expectBallotValid, ballot.Valid);
        assertEquals("Has one vote invalid", expectVote2Valid, ballot.Votes.get(1).Valid);
        assertEquals("One vote with Duplicate Error", expectVote2Error, ballot.Votes.get(1).Error);

    }

    @Test
    public void ValidateExhaustedTest(){
        String mockDuplicateInput = "ab";
        Ballot ballot = _ballotServices.GenerateBallot(
                mockCandidates, mockDuplicateInput);
        mockCandidates.get(0).Eliminate = true;
        mockCandidates.get(1).Eliminate = true;
        _ballotServices.ValidateExhausted(ballot);
        assertEquals("Ballot should be exhausted", true, ballot.Exhausted);
    }
}
