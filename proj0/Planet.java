public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Constructor with parameters*/
    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /** Constructor for copy*/
    public Planet(Planet p){
        this(p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);
    }

    /** Calculate the distance*/
    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow(xxPos - p.xxPos,2) + Math.pow(yyPos - p.yyPos,2));
    }

    /** Calculate the force exerted by another Planet*/
    public double calcForceExertedBy(Planet p){
        return 6.67e-11 * mass * p.mass / (calcDistance(p) * calcDistance((p)));
    }

    /** Calculate the force exerted by p on x-axis*/
    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    /** Calculate the force exerted by p on y-axis*/
    public double calcForceExertedByY(Planet p){
        return  calcForceExertedBy(p) * (p.yyPos - xxPos) / calcDistance(p);
    }

    /** Calculate the net force on x-axis*/
    public double calcNetForceExertedByX(Planet[] P){
        double sum = 0;
        for(Planet p : P)
            sum += calcForceExertedByX(p);
        return sum;
    }

    /** Calculate the net force on y-axis*/
    public double calcNetForceExertedByY(Planet[] P){
        double sum = 0;
        for(Planet p : P)
            sum += calcForceExertedByY(p);
        return sum;
    }

    /** Update the planet in a short time*/
    public void update(double dt,double fX,double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    /** Draw a Planet*/
    public void draw(){
        StdDraw.picture(xxPos,yyPos,imgFileName);
    }

}
