
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double gravConstant = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    private double getXxPos() {
        return xxPos;
    }

    private void setXxPos(double xxPos) {
        this.xxPos = xxPos;
    }

    private double getYyPos() {
        return yyPos;
    }

    private void setYyPos(double yyPos) {
        this.yyPos = yyPos;
    }

    private double getXxVel() {
        return xxVel;
    }

    private void setXxVel(double xxVel) {
        this.xxVel = xxVel;
    }

    private double getYyVel() {
        return yyVel;
    }

    private void setYyVel(double yyVel) {
        this.yyVel = yyVel;
    }

    private double getMass() {
        return mass;
    }

    private void setMass(double mass) {
        this.mass = mass;
    }

    private String getImgFileName() {
        return imgFileName;
    }

    private void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public double calcForceExertedByX(Planet b2) {
        double totalForce = calcForceExertedBy(b2) ;
        return totalForce * (b2.xxPos - xxPos) / calcDistance(b2) ;
    }

    public double calcForceExertedByY(Planet b2) {
        double totalForce = calcForceExertedBy(b2) ;
        return totalForce * (b2.yyPos - yyPos) / calcDistance(b2) ;
    }

    /**
     * @param b2
     * @return the distance between two bodies
     */
    public double calcDistance(Planet b2) {
        double xDis = Math.pow(b2.xxPos - xxPos,2) ;
        double yDis = Math.pow(b2.yyPos - yyPos,2) ;
        return Math.sqrt(xDis + yDis) ;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceExertedByX = 0.0 ;
        for (Planet planet : planets) {
            if (!planet.equals(this)) {
                netForceExertedByX += this.calcForceExertedByX(planet) ;
            }
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceExertedByY = 0.0 ;
        for (Planet planet : planets) {
            if (!planet.equals(this)) {
                netForceExertedByY += this.calcForceExertedByY(planet) ;
            }
        }
        return netForceExertedByY;
    }

    public double calcForceExertedBy(Planet b2) {
        return gravConstant * mass * b2.mass /Math.pow(calcDistance(b2), 2);
    }

    public void update(double dt, double fX, double fY) {
        double xAcceleration = fX / this.mass ;
        double yAcceleration = fY / this.mass ;
        this.xxVel += xAcceleration * dt ;
        this.yyVel += yAcceleration * dt ;
        this.xxPos += this.xxVel * dt ;
        this.yyPos += this.yyVel * dt ;
    }

    public void draw() {
        String imgPathPrefix = "images/" ;
        String imgPath = imgPathPrefix + this.imgFileName ;
        StdDraw.picture(xxPos,yyPos,imgPath);
    }
}
