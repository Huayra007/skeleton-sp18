package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        distTo[0] = 0;
        edgeTo[0] = 0;
        dfs(0);

    }

    // Helper methods go here

    private void dfs(int v){
        dfs(v, v, false);
    }

    private boolean dfs(int v, int p, boolean foundCircle){
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (foundCircle) return true;
            if (marked[w] && w != p) {
                edgeTo[w] = v;
                foundCircle = true;
                announce();
                foundCircle = true;

            }
            if (!marked[w]){
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                announce();
                foundCircle = dfs(w, v, foundCircle);
            }
        }
        return foundCircle;
    }
}

