// Leetcode 332. Reconstruct Itinerary
/*
* Problem Description: Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

* Note:
* 1. If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
* 2. All airports are represented by three capital letters (IATA code).
* 3. You may assume all tickets form at least one valid itinerary.
* 4. One must use all the tickets once and only once.

* Examples:
* Example 1: 
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

* Example 2:
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.

*/

// DFS Solution: 
class Solution {
    private HashMap<String, PriorityQueue<String>> track = new HashMap<>();
    private LinkedList<String> res = new LinkedList<>();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        for (int i = 0; i <  tickets.size(); i++) {
            List<String> ticket = tickets.get(i);
            String dep = ticket.get(0);
            String des = ticket.get(1);
            if (track.containsKey(dep)) {
                track.get(dep).add(des);
            } else {
                PriorityQueue<String> desQueue = new PriorityQueue<>();
                desQueue.add(des);
                track.put(dep, desQueue);
            }
        }
        visit("JFK");
        return res;
    }
    
    public void visit(String dep) {
        PriorityQueue<String> arrs = track.get(dep);
        while (arrs != null && !arrs.isEmpty()) {
            visit(arrs.poll());
        }
        res.addFirst(dep);
    }
}