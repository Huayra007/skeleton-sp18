package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

import java.lang.*;

public class Board implements WorldState{

    private int[][] tiles;
    private final int BLANK = 0;

    public Board(int[][] tiles){
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++)
            this.tiles[i] = tiles[i].clone();
    }

    public int tileAt(int i, int j){
        if (i < 0 || j < 0 || i > tiles.length - 1 || j > tiles.length - 1)
            throw new IndexOutOfBoundsException("Both i and j should be between 0 ~ N-1");
        return tiles[i][j];
    }

    public int size(){
        if (tiles == null) return 0;
        return tiles.length;
    }

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int N = size();
        int bug = -1;
        int zug = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    bug = i;
                    zug = j;
                }
            }
        }
        int[][] tiles0 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles0[i][j] = tileAt(i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(-bug + i) + Math.abs(j - zug) - 1 == 0) {
                    tiles0[bug][zug] = tiles0[i][j];
                    tiles0[i][j] = BLANK;
                    Board neighbor = new Board(tiles0);
                    neighbors.enqueue(neighbor);
                    tiles0[i][j] = tiles0[bug][zug];
                    tiles0[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming(){
        int result = 0;
        int N = tiles.length;

        for (int item = 1; item <= N * N - 1; item++){
            int i = (item - 1) / N;
            int j = (item - 1) % N;
            if (tileAt(i,j) != item)
                result++;
        }

        return result;
    }

    public int manhattan(){
        int result = 0;
        int N = tiles.length;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                int item = tiles[i][j];
                if (item == BLANK) continue;
                int i0 = (item - 1) / N;
                int j0 = (item - 1) % N;
                result += Math.abs(i0 - i) + Math.abs(j0 - j);
            }
        }

        return result;
    }

    public int estimatedDistanceToGoal(){
        return manhattan();
    }

    public boolean equals(Object y){
        if (this == y) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        Board b = (Board) y;
        if (b.tiles.length != this.tiles.length) return false;

        return this.tiles.equals(b.tiles);
    }


    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
