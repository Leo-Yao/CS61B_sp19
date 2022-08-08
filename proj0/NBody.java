import java.util.Arrays;

public class NBody {

    public static void main(String[] args) {
        args = new String[] {"157788000.0", "25000.0","data/planets.txt"} ;
        double T = Double.parseDouble(args[0]) ;
        double dt = Double.parseDouble(args[1]) ;
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double uniRadius = readRadius(filename);

        //draw universe background
        drawBackground(uniRadius) ;

        //draw all planets
        Arrays.stream(planets).forEach(planet -> planet.draw());
        StdDraw.enableDoubleBuffering();

        double time = 0.0 ;
        while (time <= T) {
            double[] xForces  = new double[planets.length];
            double[] yForces  = new double[planets.length];
            for (int j = 0; j < planets.length; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets) ;
                yForces[j] = planets[j].calcNetForceExertedByY(planets) ;
            }
            for (int k = 0; k < planets.length; k++) {
                planets[k].update(dt, xForces[k], yForces[k]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            Arrays.stream(planets).forEach(planet -> planet.draw());
            StdDraw.show();
            StdDraw.pause(5);
            time += dt ;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt() ;
        double radius = in.readDouble() ;
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int number = in.readInt() ;
        double radius = in.readDouble() ;
        Planet[] planets = new Planet[number] ;
        for (int i = 0; i < number; i++) {
            double xP = in.readDouble() ;
            double yP = in.readDouble() ;
            double xV = in.readDouble() ;
            double yV = in.readDouble() ;
            double mass = in.readDouble() ;
            String imgFileName = in.readString() ;
            planets[i] = new Planet(xP, yP, xV, yV, mass, imgFileName);
        }
        return planets ;
    }

    private static void drawBackground(Double uniRadius) {
        String imgPath = "images/starfield.jpg" ;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-uniRadius,uniRadius);
        StdDraw.clear();
        StdDraw.picture(0,0,imgPath);
        StdDraw.show();
        StdDraw.pause(20);
    }

}
