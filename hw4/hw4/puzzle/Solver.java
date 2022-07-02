package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import org.junit.validator.PublicClassValidator;

import java.util.Vector;

import java.util.PriorityQueue;

public class Solver {
    private MinPQ<SearchNode> pq=new MinPQ<>();
    private int moves;
    SearchNode solution;
    public Solver(WorldState initial) {
        SearchNode start=new SearchNode(initial,0,null);
        pq.insert(start);
        while(!pq.isEmpty()) {
            SearchNode cur=pq.delMin();
            if(cur.ws.isGoal()) {
                solution=cur;
                moves=cur.steps;
                return;
            }
            for(WorldState i:cur.ws.neighbors()) {
                if(!i.equals(cur.ws)&&(cur.parent==null||(!i.equals(cur.parent.ws)))) {  // no enqueued WorldState is its parent and grandparent.
                    pq.insert(new SearchNode(i,cur.steps+1,cur));
                }
            }
        }
    }
    public int moves() {
        return moves;
    }
    public Iterable<WorldState> solution() {
        Vector<WorldState> res=new Vector<>();
        while(solution.parent!=null) {
            res.add(solution.ws);
            solution=solution.parent;
        }
        res.add(solution.ws);
        return res;
    }

}
