package hw2;
import java.lang.*;
import java.util.Map;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int T;
    private PercolationFactory pf;
    private double[] factor;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("Keep N and T larger than 0!");
        this.T = T;
        this.pf = pf;
        this.factor = new double[T];

        for (int i  = 0; i < T; i++){
            Percolation p = pf.make(N);
            while (!p.percolates())
                p.open(StdRandom.uniform(N),StdRandom.uniform(N));
            factor[i] = p.numberOfOpenSites();
        }
    }

    public double mean(){ return StdStats.mean(factor); }
    public double stddev(){ return StdStats.stddev(factor);}
    public double confidenceLow(){return mean() - 1.96 * stddev() / Math.sqrt(T);}
    public double confidenceHigh(){ return mean() + 1.96 * stddev() / Math.sqrt(T);}

    public static void main(String[] args){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(50,5,pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceHigh());
        System.out.println(ps.confidenceLow());
    }
}
