package byog.Core;

import byog.TileEngine.TETile;

public class Commands {
    public static TETile[][] FirstChoose(char firstChoice, String param) {
        switch (firstChoice){
            case 'N':
                return newGame(param);
            case 'L':
                return loadGame();
            case 'Q':
                quitGame();
                break;
        }
        return null;
    }

    /** If the first choice is 'N', begins a new game. */
    public static TETile[][] newGame(String param) {
        return null;
    }
    /** If the first choice is 'L', loads a game. */
    public static TETile[][] loadGame() {
        return null;
    }
    /** If the first choice is 'Q', just quits the program. */
    public static void quitGame() {

    }
}
