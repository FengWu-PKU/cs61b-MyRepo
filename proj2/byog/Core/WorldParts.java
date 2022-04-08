package byog.Core;

import byog.TileEngine.TETile;

/** Parts(hallways and rooms) consisting of the world. */
public class WorldParts {
    /** #### */
    public static void straightHallWay(Position p, int length, TETile t) {  // 0

    }
    /** #####
     *      #
     *      #
     */
    public static void turnDownHallWay(Position p,int strightLen,int downLen, TETile t) { // 1

    }
    /**       #
     *        #
     *        #
     *   ######
     */
    public static void turnUpHallWay(Position p, int strightLen,int upLen,TETile t) {  // 2

    }
    /**  #### !###
     *   #      #
     *   #      #
     *   ########
     */
    public static void room(Position p,int width,int part1Hight,int part2Height,TETile t) {  // 3

    }
}
