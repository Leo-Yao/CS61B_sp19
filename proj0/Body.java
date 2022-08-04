package proj0;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body() {
    }

    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Body(Body b) {

    }

    public double getXxPos() {
        return xxPos;
    }

    public void setXxPos(double xxPos) {
        this.xxPos = xxPos;
    }

    public double getYyPos() {
        return yyPos;
    }

    public void setYyPos(double yyPos) {
        this.yyPos = yyPos;
    }

    public double getXxVel() {
        return xxVel;
    }

    public void setXxVel(double xxVel) {
        this.xxVel = xxVel;
    }

    public double getYyVel() {
        return yyVel;
    }

    public void setYyVel(double yyVel) {
        this.yyVel = yyVel;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public double calcForceExertedByX(Body b2) {
        double totalForce = calcForceExertedBy(b2) ;
        return totalForce * (b2.xxPos - xxPos) / calcDistance(b2) ;
    }

    public double calcForceExertedByY(Body b2) {
        double totalForce = calcForceExertedBy(b2) ;
        return totalForce * (b2.yyPos - yyPos) / calcDistance(b2) ;
    }

    /**
     * @param b2
     * @return the distance between two bodies
     */
    public double calcDistance(Body b2) {
        double xDis = Math.pow(b2.xxPos - xxPos,2) ;
        double yDis = Math.pow(b2.yyPos - yyPos,2) ;
        return Math.sqrt(xDis + yDis) ;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double netForceExertedByX = 0.0 ;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            netForceExertedByX += this.calcForceExertedByX(body) ;
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double netForceExertedByY = 0.0 ;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            netForceExertedByY += this.calcForceExertedByY(body) ;
        }
        return netForceExertedByY;
    }

    public double calcForceExertedBy(Body b2) {
        return 6.67e-11 * mass * b2.mass /Math.pow(calcDistance(b2), 2);
    }

    public void update(double v, double v1, double v2) {
        double xAcceleration = v1 / this.mass ;
        double yAcceleration = v2 / this.mass ;
        this.xxVel += xAcceleration * v ;
        this.yyVel += yAcceleration * v ;
        this.xxPos += this.xxVel * v ;
        this.yyPos += this.yyVel * v ;
    }
}
