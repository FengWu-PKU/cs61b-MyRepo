public class NBody {
    /** Return the radius of the univ. */
    public static double readRadius(String fileName){
        In in=new In(fileName);
        int planetsNum=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    /** Return an array of Planets in the given file. */
    public static Planet[] readPlanets(String fileName){
        In in=new In(fileName);
        int num=in.readInt();
        double radius=in.readDouble();
        Planet[] planets=new Planet[num];
        for(int i=0;i<num;i++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i]=new Planet(xp,yp,vx,vy,m,img);
        }
        return planets;
    }
    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=readRadius(filename);
        Planet[] planets=readPlanets(filename);

        int planetsNum=planets.length;

        StdDraw.enableDoubleBuffering();

        for(int time=0;time<=T;time+=dt){
            StdDraw.clear();
            /** Draw the background. */
            StdDraw.setScale(-radius,radius); // set the scale.
            StdDraw.picture(0,0,"images/starfield.jpg");
            double[] xForces=new double[planetsNum];
            double[] yForces=new double[planetsNum];
            for(int i=0;i<planetsNum;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<planetsNum;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            /** Draw all of the planets. */
            for(int i=0;i<planetsNum;i++){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        /** Print the univ. */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
