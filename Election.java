//Logic: Need to implement a voting system using priority queues
//use hashtable to store each candidate's vote count
//use a max-heap as priority queue to retrieve top candidates

import java.util.*;

public class Election {
    private HashMap<String, Integer> voteCount;
    int totalVotes;
    int maxVotesAllowed;
    List<String> candidateList;

    public Election(){
        this.voteCount = new HashMap<>();
        this.totalVotes = 0;
        this.maxVotesAllowed = 0;
        this.candidateList = new ArrayList<>();
    }

    public void setCandidateList(List<String> candidates) {
        for(String candidate : candidates){
            voteCount.put(candidate, 0);
            candidateList.add(candidate);
        }
    }

    public void setMaxVotes(int p) {
        this.maxVotesAllowed = p;
    }

    public void castVote(String candidate) {
        if (totalVotes >= maxVotesAllowed) {
            voteCount.put(candidate, voteCount.get(candidate) + 1);
            totalVotes++;
        }
    }

    public void castRandomVote() {
        if (totalVotes >= maxVotesAllowed) {
            Random rand = new Random();
            String candidate = candidateList.get(rand.nextInt(candidateList.size()));
            castVote(candidate);
        }
    }

    public void rigElection(String candidate) {
        int votesToAdd = maxVotesAllowed - totalVotes;
        voteCount.put(candidate, voteCount.get(candidate) + votesToAdd);
        totalVotes += votesToAdd;
    }

    public List<String> getTopKCandidates(int k) {
        PriorityQueue<Map.Entry<String, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(voteCount.entrySet());
        List<String> topK = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topK.add(maxHeap.poll().getKey());
        }
        return topK;
    }

    public void auditElection() {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(voteCount.entrySet());
        sortedList.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Audit Results:");
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

}
