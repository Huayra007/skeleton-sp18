package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;
import java.util.LinkedList;

public class Solver {

    private Node start;
    private int moves;
    private LinkedList<WorldState> solution;

    private class Node implements Comparable<Node>{
        WorldState s;
        int m;
        Node prev;

        public Node(WorldState s, int m, Node prev){
            this.s = s;
            this.m = m;
            this.prev = prev;
        }

        public int compareTo(Node n){
            if (this.m + this.s.estimatedDistanceToGoal() > n.m + n.s.estimatedDistanceToGoal()) return 1;
            else if (m + s.estimatedDistanceToGoal() < n.m + n.s.estimatedDistanceToGoal()) return -1;
            else return 0;
        }

    }

    public Solver(WorldState initial){
        this.start = new Node(initial, 0, null);
        this.solution = new LinkedList<WorldState>();

        LinkedList<Node> dq = new LinkedList<>();
        MinPQ<Node> pq = new MinPQ<>();
        pq.insert(start);

        while (!pq.isEmpty()){
            Node v = pq.delMin();
            dq.add(v);
            if (v.s.isGoal()){
                Node t = v;
                while (t != null) {
                    solution.addFirst(t.s);
                    t = t.prev;
                }
                return;
            }

            for (WorldState w : v.s.neighbors())
                if (v.prev != null ? !w.equals(v.prev.s) : true)
                    pq.insert(new Node(w, v.m + 1, v));
        }
    }

    public int moves(){
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution(){
        return solution;
    }
}
