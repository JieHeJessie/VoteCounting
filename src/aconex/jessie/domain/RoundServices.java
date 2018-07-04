package aconex.jessie.domain;

import aconex.jessie.core.Ballot;
import aconex.jessie.core.Candidate;
import aconex.jessie.core.Round;
import aconex.jessie.core.Vote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoundServices implements IRoundServices {
    /*
    * Calculate current round with ballot
    * */
    public Round GenerateRound(
            List<Ballot> ballots,
            List<Candidate> allCandidates){
        Round round = new Round();
        // Get max & min value of candidate
        Map<Candidate, Integer> rank = getCandidatesRank(ballots, allCandidates, 0);
        Map.Entry<Candidate, Integer> maxCandidate = getMaxCandidate(rank);
        boolean succeed = checkSuccessWithQuota(ballots, maxCandidate.getValue());
        if (succeed){
            round.Success = true;
            round.Finished = true;
            round.Result = maxCandidate.getKey();
            return round;
        }

        Candidate eliminate = findEliminateWithPreference(ballots, allCandidates, 0);
        if (eliminate == null){
            round.Success = false;
            round.Finished = true;
        }
        else {
            round.Success = false;
            round.Finished = false;
            round.EliminateCandidate = eliminate;
            eliminate.Eliminate = true;
        }

        return round;
    }

    /*
    * Find the exhausted candidate with the preference
    * */
    private Candidate findEliminateWithPreference(
            List<Ballot> ballots,
            List<Candidate> allCandidates,
            int preference){
        Map<Candidate, Integer> rank = getCandidatesRank(ballots, allCandidates, preference);
        Map.Entry<Candidate, Integer> maxCandidate = getMaxCandidate(rank);
        Map.Entry<Candidate, Integer> minCandidate = getMinCandidate(rank);

        if(allCandidates.size() == 1 || maxCandidate.getValue() == 0) return null;

        // If max and min is the same that means all candidates have the same vote
        if(maxCandidate.getValue().equals(minCandidate.getValue())){
            // Try to find the min in the next preference
            return findEliminateWithPreference(ballots, allCandidates, preference + 1);
        }else {
            return minCandidate.getKey();
        }
    }

    /*
    * Check if a number of candidate can win basing on the non-exhausted ballots
    * */
    private boolean checkSuccessWithQuota(List<Ballot> ballots, int candidateCount){
        int nonExhaustedCount = 0;
        for (Ballot b: ballots) {
            if (!b.Exhausted && b.Valid)
                nonExhaustedCount ++;
        }
        // Quota
        int requiredQuota = nonExhaustedCount / 2 + 1;
        return candidateCount >= requiredQuota;
    }

    /*
    * Calculate the amount for each candidate
    * */
    private Map<Candidate, Integer> getCandidatesRank(
            List<Ballot> ballots,
            List<Candidate> allCandidates,
            int preference){

        Map<Candidate, Integer> rank = new HashMap<>();
        allCandidates.stream()
                .filter(c -> !c.Eliminate)
                .forEach(c -> rank.put(c, 0));

        List<Ballot> validBallots = ballots.stream()
                .filter(b -> (!b.Exhausted && b.Valid))
                .collect(Collectors.toList());

        for (Ballot ballot: validBallots) {
            Candidate firstCandidate = getPrefCandidate(ballot.Votes, preference);
            if (firstCandidate != null)
                rank.put(firstCandidate, rank.get(firstCandidate) + 1);
        }
        return rank;
    }

    /*
    * Find the candidate with the preference
    * */
    private Candidate getPrefCandidate(List<Vote> votes, int preference){
        int available = 0;
        for (int index = 0; index < votes.size(); index ++){
            Vote v = votes.get(index);
            if (v.Valid && !v.Candidate.Eliminate){
                if (available == preference)
                    return v.Candidate;
                else
                    available ++;
            }
        }
        return null;
    }

    /*
    * Get the max amount of candidate from the map
    * */
    private Map.Entry<Candidate, Integer> getMaxCandidate(Map<Candidate, Integer> rank){
        Map.Entry<Candidate, Integer> maxCandidate = null;
        for (Map.Entry<Candidate, Integer> enter : rank.entrySet()) {
            // get max value candidate
            if (maxCandidate == null ||
                    enter.getValue().compareTo(maxCandidate.getValue()) > 0){
                maxCandidate = enter;
            }
        }
        return maxCandidate;
    }

    /*
    * Get the least amount of candidate from the map
    * */
    private Map.Entry<Candidate, Integer> getMinCandidate(Map<Candidate, Integer> rank){
        Map.Entry<Candidate, Integer> minCandidate = null;
        for (Map.Entry<Candidate, Integer> enter : rank.entrySet()) {
            // get max value candidate
            if (minCandidate == null ||
                    enter.getValue().compareTo(minCandidate.getValue()) < 0){
                minCandidate = enter;
            }
        }
        return minCandidate;
    }

}
