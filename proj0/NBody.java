package proj0;

public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt() ;
        double radius = in.readDouble() ;
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int num = in.readInt() ;
        double radius = in.readDouble() ;
        Body[] bodies = new Body[5] ;
        int count = 0 ;
        while (count < 5) {
            Body body = new Body();
            body.xxPos = in.readDouble() ;
            body.yyPos = in.readDouble() ;
            body.xxVel = in.readDouble() ;
            body.yyVel = in.readDouble() ;
            body.mass = in.readDouble() ;
            body.imgFileName = in.readString() ;
            bodies[count] = body ;
            count ++ ;
        }
        return bodies ;
    }
}
