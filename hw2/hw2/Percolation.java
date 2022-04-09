package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;

public class Percolation {
    private int maxRow;
    private int maxCol;
    private int[][] sites;  // -1: blocked, 0: empty open, 1: full open
    /** Creates N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if(N<0) {
            throw new IllegalArgumentException("Illegal: N<0");
        }
        sites=new int[N][N];
        maxCol=maxRow=N-1;
    }
    /** Returns true if (row, col) is out of range. */
    private boolean outOfRange(int row, int col) {
        if(row<0 || row>maxRow) {
            return false;
        }else if(col<0 || col>maxCol) {
            return false;
        }else {
            return true;
        }
    }

    /** Opends the site (row, col) if it's not open already. */
    public void open(int row, int col) {
        if(outOfRange(row,col)) {
            throw new IndexOutOfBoundsException("(row, col) is out of range");
        }
        sites[row][col]=0;
    }
    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if(outOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if(sites[row][col]==-1) {
            return false;
        }else {
            return true;
        }
    }
    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if(outOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if(sites[row][col]==1) {
            return true;
        }else {
            return false;
        }
    }
    /** Number of open sites. */
    public int numberOfOpenSites() {
        int noos=0;
        for(int i=0;i<=maxRow;i++) {
            for(int j=0;j<=maxCol;j++) {
                if(isOpen(i,j)) {
                    noos++;
                }
            }
        }
        return noos;
    }
    /** Does the system percolate? */
    public boolean percolates() {
        for(int j=0;j<maxCol;j++) {
            if(isFull(maxRow,j)) {
                return true;
            }
        }
        return false;
    }
}
