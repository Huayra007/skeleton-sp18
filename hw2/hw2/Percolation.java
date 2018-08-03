package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;

public class Percolation {
    private int N;
    private boolean[] gridsOpen;
    private WeightedQuickUnionUF un;
    private int numOfOpen;

    /** Constructor */
    public Percolation(int N){
        if (N <= 0) throw new IllegalArgumentException("N must be larger than 0!");

        this.N = N;
        gridsOpen = new boolean[N * N];
        un = new WeightedQuickUnionUF(N * N);
        numOfOpen = 0;

    }

    public void open(int row, int col){
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Keep row and col in bounds!");
        if (!isOpen(row,col))
            numOfOpen++;
        gridsOpen[xyTo1D(row,col)] = true;
        connectNeighbors(row,col);
    }

    public boolean isOpen(int row, int col){
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Keep row and col in bounds!");
        return gridsOpen[xyTo1D(row,col)];
    }

    public boolean isFull(int row, int col){
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Keep row and col in bounds!");
        //int r = un.find(xyTo1D(row,col));
        for (int i = 0; i < N; i++)
            if (un.find(i) == un.find(xyTo1D(row,col)) && isOpen(row,col))
                return true;
        //return r >= 0 && r < N && isOpen(row,col);
        return false;
    }

    public int numberOfOpenSites(){
        return numOfOpen;
    }

    public boolean percolates(){
        for (int i = 0; i < N; i++)
            if (isFull(N - 1, i))
                return true;
        return false;
    }

    public int xyTo1D(int row, int col){
        return row * N + col;
    }

    public void connectNeighbors(int row, int col){
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Keep row and col in bounds!");

        if (row - 1 >= 0 && isOpen(row - 1, col))
            un.union(xyTo1D(row,col),xyTo1D(row - 1,col));
        if (row + 1 < N && isOpen(row + 1, col))
            un.union(xyTo1D(row,col),xyTo1D(row + 1,col));
        if (col - 1 >= 0 && isOpen(row,col - 1))
            un.union(xyTo1D(row,col),xyTo1D(row,col - 1));
        if (col + 1 < N && isOpen(row,col + 1))
            un.union(xyTo1D(row,col),xyTo1D(row,col + 1));
    }
}