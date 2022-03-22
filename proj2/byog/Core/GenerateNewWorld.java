package byog.Core;

import byog.TileEngine.*;
import  java.util.Random;



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
                    int straightHallWayLen=len.nextInt(maxLen)+1;  // [1, maxLen+1)
                    // if out of range, reset the param.
                    if(outOfRange(p.x+straightHallWayLen,p.y)) {
                        int rest=worldWidth-p.x-1;
                        straightHallWayLen=len.nextInt(rest);
                    }
                    WorldParts.straightHallWay(p,straightHallWayLen,Tileset.FLOOR);
                    // updates the position
                    p.x+=straightHallWayLen;
                    break;
                case 1:  // turnDownHallWay
                    int straightLen=len.nextInt(maxLen);
                    int downLen=len.nextInt(maxLen);
                    // if out of the range, reset the param.
                    if(outOfRange(p.x+straightLen,p.y-downLen)) {
                        int straightLenRest=worldWidth-p.x-1;
                        int downLenRest=p.y-0+1;
                        straightLen=len.nextInt(straightLenRest);
                        downLen=len.nextInt(downLenRest);
                    }
                    WorldParts.turnDownHallWay(p,straightLen,downLen,Tileset.FLOOR);
                    // updates the position
                    p.x+=straightLen;
                    p.y-=downLen;
                    break;
                case 2:  //  turnUpHallWay
                    int straightLen2=len.nextInt(maxLen);
                    int upLen=len.nextInt(maxLen);
                    if(outOfRange(p.x+straightLen2,p.y+upLen)) {
                        int straightLen2Rest=worldWidth-p.x-1;
                        int upLenRest=worldHeight-p.y-1;
                        straightLen2=len.nextInt(straightLen2Rest);
                        upLen=len.nextInt(upLenRest);
                    }
                    WorldParts.turnUpHallWay(p,straightLen2,upLen,Tileset.FLOOR);
                    // updates the position
                    p.x+=straightLen2;
                    p.y+=upLen;
                    break;
                case 3:
                    int roomWidth=len.nextInt(maxLen);
                    int roomHeight=len.nextInt(maxLen);

            }
        }






        /** Just satisfy the compiler. */
        return null;
    }

    public boolean outOfRange(int x, int y) {
        if(x>=worldWidth || y>=worldHeight) {
            return true;
        }else if(x<0 || y<0) {
            return true;
        }
        return false;
    }

}

/** Return*/
