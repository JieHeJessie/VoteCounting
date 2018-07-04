package aconex.jessie;

import aconex.jessie.consts.Constants;
import aconex.jessie.consts.ErrorType;
import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.core.Vote;
import aconex.jessie.presentation.BallotManager;
import aconex.jessie.presentation.CandidateManager;
import aconex.jessie.presentation.RoundManager;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CandidateManager candidateManager = new CandidateManager();
        BallotManager ballotManager = new BallotManager();
        RoundManager roundManager = new RoundManager();

        String filepath = args.length == 0 ? Constants.Default_FilePath : args[0];
        // Check file exist
        File fileDir = new File(filepath);
        if (!fileDir.exists()){
            System.out.println("File " + filepath + " not exist!");
            System.exit(0);
        }
        List<Candidate> candidates = candidateManager.GenerateCandidates(filepath);
        ballotManager.SetCandidates(candidates);
        if (candidates.size() == 0){
            System.out.println("There is 0 valid candidate in the file.");
            System.exit(0);
        }
        // Print all candidates
        for (Candidate candidate: candidates) {
            System.out.println(candidate.Index + ". " + candidate.Name);
        }

        // Read user input
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.print("> ");
            // wait user input
            String inputLine = scan.nextLine();

            if (inputLine.toLowerCase().equals(Constants.TALLY))
                break;

            Ballot ballot = ballotManager.AddBallot(inputLine);
            for(Vote v : ballot.Votes){
                if (!v.Valid && v.Error == ErrorType.DuplicateInput)
                    System.out.println(String.format(Constants.Error_Duplicate_Input, v.Input));
                if (!v.Valid && v.Error == ErrorType.InvalidInput)
                    System.out.println(String.format(Constants.Error_Invalid_Input, v.Input));
            }
        }

        // start calculate after tally
        List<Ballot> ballots = ballotManager.GetBallots();
        while (true){
            Round round = roundManager.CountUpRound(ballots, candidates);
            if(round.Finished) {
                if (round.Success){
                    System.out.println(Constants.SUCCESS_MSG + round.Result.Name);
                }else {
                    System.out.println(Constants.FAIL_MSG);
                    for(Candidate c: candidates){
                        if (!c.Exhausted)
                            System.out.println(c.Index + ". " + c.Name);
                    }
                }
                break;
            }
            ballots = ballotManager.UpdateBallotWithRound(round);
            //Print round message and continue
            System.out.println("Round " + roundManager.GetRoundNumber() +
                    ": eliminate " + round.EliminateCandidate.Name);
        }

    }
}
