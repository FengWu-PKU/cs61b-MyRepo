public class Planet {
    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;
    private static final double G=6.67e-11; // final ~= const

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    /** Calculate the distance between two Planets. */
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
    }
    /** Calculate the force between two planets. */
    public double calcForceExertedBy(Planet p){
        double r=calcDistance(p);
        return G*mass*p.mass/(r*r);
    }
    /** Calculate the force exerted by x. */
    public double calcForceExertedByX(Planet p){
        double dx=p.xxPos-xxPos;
        double r=calcDistance(p);
        return calcForceExertedBy(p)*dx/r;
    }
    /** Calculate the force exerted by y. */
    public double calcForceExertedByY(Planet p){
        double dy=p.yyPos-yyPos;
        double r=calcDistance(p);
        return calcForceExertedBy(p)*dy/r;
    }
    /** Calculate the x force between many planets except itself. */
    public double calcNetForceExertedByX(Planet[] ps){
        double totalForce=0;
        int pNum=ps.length;
        for(int i=0;i<pNum;i++){
            if(this.equals(ps[i])){
                continue;
            }
            totalForce+=calcForceExertedByX(ps[i]);
        }
        return totalForce;
    }
    /** Calculate the x force between many planets except itself. */
    public double calcNetForceExertedByY(Planet[] ps){
        double totalForce=0;
        int pNum=ps.length;
        for(int i=0;i<pNum;i++){
            if(this.equals(ps[i])){
                continue;
            }
            totalForce+=calcForceExertedByY(ps[i]);
        }
        return totalForce;
    }
    /** Update the planet's v and location */
    public void update(double dt,double fX,double fY){
        double ax=fX/mass;
        double ay=fY/mass;
        xxVel+=ax*dt;
        yyVel+=ay*dt;
        xxPos+=xxVel*dt;
        yyPos+=yyVel*dt;
    }
    /** Draw itself. */
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
