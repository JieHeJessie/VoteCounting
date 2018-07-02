package aconex.jessie;

import aconex.jessie.consts.Constants;
import aconex.jessie.consts.ErrorType;
import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.presentation.BallotManager;
import aconex.jessie.presentation.CandidateManager;
import aconex.jessie.presentation.RoundManager;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CandidateManager candidateManager = new CandidateManager();
        BallotManager ballotManager = new BallotManager();
        RoundManager roundManager = new RoundManager();

        String filepath = args.length == 0 ? Constants.Default_FilePath : args[0];
        // Check file exist

        List<Candidate> candidate = candidateManager.GenerateCandidates(filepath);
        // Print all candidates

        Scanner scan = new Scanner(System.in);
        while (true){
            // wait user input
            String inputLine = scan.nextLine();

            if (inputLine.toLowerCase().equals(Constants.TALLY))
                break;

            Ballot ballot = ballotManager.AddBallot(inputLine);
            if (!ballot.Valid){
                System.out.print(ballot.error);
            }
        }

        // start calculate after tally
        List<Ballot> ballots = ballotManager.GetBallots();
        while (true){
            Round round = roundManager.CountUpRound(ballots);
            if(round.Finished) {
                // pint result message
                String resultMsg = round.Success ? Constants.SUCCESS_MSG : Constants.FAIL_MSG;

                break;
            }
            ballots = ballotManager.UpdateBallotWithRound(round);
            //Print round message and continue
        }

    }
}
