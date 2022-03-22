package byog.Core;

import byog.TileEngine.*;

import  java.util.Random;
public class GenerateNewWorld {
    /** Generate a new world using the param(a randomSeed). */
    public TETile[][] randomWorld(String param) {
        /** The world size. */
        final int worldWidth=60;
        final int worldHeight=30;

        /** TERender. */
        TERenderer render=new TERenderer();
        render.initialize(worldWidth,worldHeight);

        /** World. */
        TETile[][] world;

        /** Start position. */
        Position p=new Position(0,0);

        long seed=Long.parseLong(param);  //  Convert param to long.
        Random random=new Random(seed);






        /** Just satisfy the compiler. */
        return null;
    }

}

/** Return*/
