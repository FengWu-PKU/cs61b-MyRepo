package byog.Core;

public class InputResolve {
    public char firstCommand;
    public String inputString;
    public char lastCommand;

    /** Resolves the input string to three parts. */
    public InputResolve(String input) {
        int len=input.length();
        firstCommand=input.charAt(0);
        inputString=input.substring(1,len-2);
        lastCommand=input.charAt(len-1);
    }
}
