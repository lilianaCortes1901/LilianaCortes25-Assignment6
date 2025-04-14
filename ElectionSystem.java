//Logic: Need to implement a voting system using priority queues
//use hashtable to store each candidate's vote count
//use a max-heap as priority queue to retrieve top candidates

import java.util.LinkedList;

public class ElectionSystem {
    public static void main(String[] args) {
        Election election = new Election();

        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Yukio");
        candidates.add("Rin");
        candidates.add("Fujimoto");

        election.setCandidateList(candidates);
        election.setMaxVotes(100);

        //Random Vote
        for(int i = 0; i < 80; i++) {
            election.castRandomVote();
        }

        //Rig the Election
        election.rigElection("Rin");

        //Get Top candidates
        System.out.println("Top 2 Candidates: " + election.getTopKCandidates(2));

        //Audit election
        election.auditElection();


    }
}