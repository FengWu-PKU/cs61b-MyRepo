package hw4.puzzle;


public class SearchNode implements Comparable<SearchNode> {
    public WorldState ws;
    public int steps;
    public SearchNode parent;
    public SearchNode(WorldState w,int s, SearchNode p) {
        ws=w;
        steps=s;
        parent=p;
    }
    @Override
    public int compareTo(SearchNode s) {
        int sum1=steps+ws.estimatedDistanceToGoal();
        int sum2=s.steps+s.ws.estimatedDistanceToGoal();
        return sum1-sum2;
    }
}
