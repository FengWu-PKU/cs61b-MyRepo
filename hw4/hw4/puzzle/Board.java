package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState{
    private static final int BLANK=0;
    private int N;
    private int[][] tiles;
    private int[][] goal;
    public Board(int[][] t) {
        tiles=t;
        N=t.length;
        int[][] tmp=new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(i==N-1&&j==N-1) tmp[i][j]=BLANK;
                else tmp[i][j]=i*N+j+1;
            }
        }
        goal=tmp;
    }
    public int tileAt(int i, int j) {
        if(i>=N||j>=N||i<0||j<0) throw new java.lang.IndexOutOfBoundsException();
        return tiles[i][j];
    }
    public int size() {
        return N;
    }
    @Override
    /** Cite from: http://joshh.ug/neighbors.html */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming() {
        int ham=0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(tileAt(i,j)!=BLANK&&tileAt(i,j)!=goal[i][j]) ham++;
            }
        }
        return ham;
    }
    public int Manhattan() {
        int man=0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(tileAt(i,j)!=BLANK&&tileAt(i,j)!=goal[i][j]) {
                    int corectR=(tileAt(i,j)-1)/N;
                    int corectC=(tileAt(i,j)-1)%N;
                    man+=Math.abs(corectR-i)+Math.abs(corectC-j);
                }
            }
        }
        return man;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return Manhattan();
    }
    public boolean equals(Object y) {
        if(y==this) return true;
        if(y==null||this.getClass()!=y.getClass()) return false;
        Board yy=(Board)y;
        if(this.size()!=yy.size()) return false;
        int n=this.size();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(this.tileAt(i,j)!=yy.tileAt(i,j))
                    return false;
            }
        }
        return true;
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
