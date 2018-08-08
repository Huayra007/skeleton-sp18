package lab11.graphs;


import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private class Node implements Comparable<Node>{
        private int item;
        private int priority;
        public Node(int i, int p){
            item = i;
            priority = p;
        }
        public int priority(){
            return priority;
        }

        public int item(){
            return item;
        }

        @Override
        public int compareTo(Node n){
            if (this.priority() < n.priority()) return -1;
            else if (this.priority() > n.priority()) return 1;
            else return 0;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        double[] H = new double[maze.V()];
        double[] F = new double[maze.V()];

        for (int i = 0; i < F.length; i++)
            F[i] = Double.POSITIVE_INFINITY;
        F[s] = distTo[s] + h(s);
        marked[s] = true;

        Queue<Node> openPQ = new PriorityQueue<>();
        Set<Integer> openList = new HashSet<>();
        List<Integer> closeList = new LinkedList<>();

        openPQ.offer(new Node(s,distTo[s] + h(s)));
        openList.add(s);

        while (!openList.isEmpty()){
            //int v = findMinIndex(F);
            Node v = openPQ.poll();
            int vi = v.item();
            openList.remove(vi);
            closeList.add(vi);
            marked[vi] = true;
            if (vi == t) targetFound = true;
            announce();
            if (targetFound) return;

            for (int w : maze.adj(vi)){

                if (!openList.contains(w) && !closeList.contains(w)){
                    distTo[w] = distTo[vi] + 1;
                    edgeTo[w] = vi;
                    F[w] = distTo[w] + h(w);
                    openList.add(w);
                    openPQ.offer(new Node(w,distTo[w] + h(w)));
                }
                else {
                    if (distTo[w] > distTo[vi] + 1){
                        distTo[w] = distTo[vi] + 1;
                        edgeTo[w] = vi;
                    }
                }
                announce();
            }
        }
    }

    private int findMinIndex(double[] l){
        int minInd = 0;

        for (int i = 0; i < l.length; i++)
            if (l[minInd] >= l[i])
                minInd = i;
        return minInd;
    }

    @Override
    public void solve() {
        astar(s);
    }

}

