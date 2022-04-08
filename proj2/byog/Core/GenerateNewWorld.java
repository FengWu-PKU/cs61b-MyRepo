package byog.Core;

import byog.TileEngine.*;
import  java.util.Random;

/** NOTICE!!!!
 *      There may be no place to built the wall!
 *      So copy the TETile[worldWidth][worldHeight] world
 *      to TETile[worldWidth+10][worldHeight+10] worldWithWall.
 *      Makes the world at the middle of worldWithWall.
 *
 *
 *
 *      Maybe some param be 0 after reset!
 */


public class GenerateNewWorld {

    /** The world size. */
    public final int worldWidth=60;
    public final int worldHeight=30;

    /** Generate a new world using the param(a randomSeed). */
    public TETile[][] randomWorld(String param) {
        /** MainIdea:
         *      Imagines that there's a architecture walk around in the empty world,
         *      and build something randomly.
         */

        /** The max number of the hallway's length. */
        final int maxLen=8;

        /** The direction of the builder. */
        char[] directions={'R','D','U'}; // right, down, up
        char direction=directions[0]; // starts by toward right, for the special case when first he builts a room.

        /** TERender. */
        TERenderer render=new TERenderer();
        render.initialize(worldWidth,worldHeight);

        /** World. */
        TETile[][] world = new TETile[worldWidth][worldHeight];

        /** Start position. */
        Position p=new Position(0,0);

        long seed=Long.parseLong(param);  //  Convert param to long.
        Random random=new Random(seed);  // for the world generation
        Random len=new Random();  // for the length of the hallway and the size of the room

        while(p.x<worldWidth) {
            int nextRandom=random.nextInt();
            int nextKind=nextRandom%4;
            switch(nextKind) {
                case 0:  // straightHallWay
                    int straightHallWayLen=len.nextInt(maxLen)+1;  // [1, maxLen+1) add one to avoid 0
                    // if out of range, reset the param.
                    if(XoutOfRange(p.x+straightHallWayLen)) {
                        int rest=worldWidth-p.x-1;
                        straightHallWayLen=len.nextInt(rest);
                    }
                    WorldParts.straightHallWay(p,straightHallWayLen,Tileset.FLOOR);
                    // updates the position
                    p.x+=straightHallWayLen;
                    direction=directions[0];
                    break;
                case 1:  // turnDownHallWay
                    int straightLen=len.nextInt(maxLen)+1;
                    int downLen=len.nextInt(maxLen)+1;
                    // if out of the range, reset the param.
                    if(XoutOfRange(p.x+straightLen)) {
                        int straightLenRest=worldWidth-p.x-1;
                        straightLen=len.nextInt(straightLenRest);
                    }
                    if(YoutOfRange(p.y-downLen)) {
                        int downLenRest=p.y-0+1;
                        downLen=len.nextInt(downLenRest);
                    }
                    WorldParts.turnDownHallWay(p,straightLen,downLen,Tileset.FLOOR);
                    // updates the position
                    p.x+=straightLen;
                    p.y-=downLen;
                    direction=directions[1];
                    break;
                case 2:  //  turnUpHallWay
                    int straightLen2=len.nextInt(maxLen)+1;
                    int upLen=len.nextInt(maxLen)+1;
                    if(XoutOfRange(p.x+straightLen2)) {
                        int straightLen2Rest=worldWidth-p.x-1;
                        straightLen2=len.nextInt(straightLen2Rest);
                    }
                    if(YoutOfRange(p.y+upLen)) {
                        int upLenRest=worldHeight-p.y-1;
                        upLen=len.nextInt(upLenRest);
                    }
                    WorldParts.turnUpHallWay(p,straightLen2,upLen,Tileset.FLOOR);
                    // updates the position
                    p.x+=straightLen2;
                    p.y+=upLen;
                    direction=directions[2];
                    break;
                case 3:
                    int roomWidth=len.nextInt(maxLen);
                    int roomHeight=len.nextInt(maxLen);
                    // to vary the entrance position
                    int part1Height=len.nextInt(roomHeight+1);  // [0, roomHeight+1)
                    int part2Height=roomHeight-part1Height;

                    // can move to the room() function
                    if(direction=='R') {
                        if(YoutOfRange(p.y-part1Height)) {
                            int part1HeightRest=p.y-0+1;
                            part1Height=len.nextInt(part1HeightRest);
                        }
                        if(YoutOfRange(p.y+part2Height)) {
                            int part2HeightRest=worldHeight-part2Height-1;
                            part2Height=len.nextInt(part2HeightRest);
                        }
                        if(XoutOfRange(p.x+roomWidth)) {
                            int roomWidthRest=worldWidth-p.x-1;
                            roomWidth=len.nextInt(roomWidthRest);
                        }
                    }

            }
        }






        /** Just satisfy the compiler. */
        return null;
    }


    /** The reason why I break them apart:
     *          when one of the two if out of range,
     *          both will be reset according to the rest,
     *          which could be too large for the one not out of the range.
     */
    public boolean XoutOfRange(int x) {
        if(x>=worldWidth || x<0) {
            return true;
        }
        return false;
    }
    public boolean YoutOfRange(int y) {
        if(y>=worldHeight || y<0) {
            return true;
        }
        return false;
    }

}

/** Return*/
