public class Palindrome {
    /** Convert a String to a Deque. */
    public Deque<Character> wordToDeque(String word) {
        int len=word.length();
        String revertWord=revert(word);
        ArrayDeque<Character> result=new ArrayDeque<Character>();
        for (int i=0;i<len;i++) {
            result.addFirst(revertWord.charAt(i));
        }
        return result;
    }
    public String revert(String word) {
        int len=word.length();
        String revertWord="";
        for(int i=0;i<len;i++) {
            char tmp=word.charAt(len-i-1);
            revertWord+=tmp;
        }
        return revertWord;
    }
    public boolean isPalindrome(String word) {
        if(word==null || word.length()<=1)
            return true;
        String revertWord=revert(word);
        return  word.equals(revertWord);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if(word==null || word.length()<=1)
            return true;
        int len=word.length();
        for(int i=0;i<len/2;i++) {
            if(!cc.equalChars(word.charAt(i),word.charAt(len-i-1)))
                return false;
        }
        return true;
    }
}
