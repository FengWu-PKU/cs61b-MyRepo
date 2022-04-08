public class OffByN implements CharacterComparator{
    int n=0;
    OffByN(int N) {
        n=N;
    }
    @Override
    public boolean equalChars(char x,char y) {
        return Math.abs(x-y)==n;
    }
}
