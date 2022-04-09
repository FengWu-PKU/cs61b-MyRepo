package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;



public class Percolation {
    private int head=0;
    private int tail;
    private int len;
    private boolean[] open;
    private int openSitesNum=0;
    private WeightedQuickUnionUF uf;
    public Percolation(int N) {
        if(N<0) {
            throw new IllegalArgumentException();
        }
        len=N;
        tail=N*N+1;
        open=new boolean[N*N+2];  // default 0
        uf=new WeightedQuickUnionUF(N*N+2); // one head and one tail
        for(int j=0;j<len;j++) {
            int code1=xyTo1D(0,j);
            uf.union(head,code1);  // union the top level with head
            int code2=xyTo1D(len-1,j);
            uf.union(code2,tail);  // union the bottom level with tail
        }
    }
    private boolean outOfRange(int row, int col) {
        if(row<0 ||row>=len) {
            return true;
        }else if(col<0 ||col>=len) {
            return true;
        }else {
            return false;
        }
    }
    private int xyTo1D(int row, int col) {
        return (row*len)+(col)+1;  // starts at 1 to N*N
    }

    public void open(int row, int col) {
        if(outOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int code=xyTo1D(row,col);
        open[code]=true;
        openSitesNum++;
        // dealing with neighbors.
        if(!outOfRange(row-1,col)&&isOpen(row-1,col)) {
            int upNeighbor=xyTo1D(row-1,col);
            uf.union(upNeighbor,code);
        }
        if(!outOfRange(row,col-1)&&isOpen(row,col-1)) {
            int leftNeighbor=xyTo1D(row,col-1);
            uf.union(leftNeighbor,code);
        }
        if(!outOfRange(row,col+1)&&isOpen(row,col+1)) {
            int rightNeighbor=xyTo1D(row,col+1);
            uf.union(rightNeighbor,code);
        }
        if(!outOfRange(row+1,col)&&isOpen(row+1,col)) {
            int downNeighbor=xyTo1D(row+1,col);
            uf.union(downNeighbor,code);
        }

    }
    public boolean isOpen(int row, int col) {
        if(outOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int code=xyTo1D(row,col);
        return open[code];
    }
    public boolean isFull(int row, int col) {
        int code=xyTo1D(row,col);
        return uf.connected(code,head);
    }
    public int numberOfOpenSites() {
        return openSitesNum;
    }
    public boolean percolates() {
        return uf.connected(head,tail);
    }

}
