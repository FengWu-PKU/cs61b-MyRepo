import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('r','q'));
        assertFalse(offByOne.equalChars('a','z'));
    }
    @Test
    public void testIsPalindrome() {
        Palindrome p=new Palindrome();
        assertTrue(p.isPalindrome("acb",offByOne));
        assertTrue(p.isPalindrome("a",offByOne));
        assertFalse(p.isPalindrome("aa",offByOne));
    }
    static CharacterComparator offBy2=new OffByN(2);

}
