
public class TestBody {
    /**
     * test pairForce between two bodies in Body.class
     */
    public static void main(String[] args) {
        checkPairForce();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  Checks the Body class to make sure pairForce works.
     */
    public static void checkPairForce() {
        System.out.println("check pairForce......");
        Planet planetA = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet planetB = new Planet(3.0, 4.0, 9.0, 5.0, 100.0, "sun.gif");

        double forceExertedByXB = planetA.calcForceExertedByX(planetB);
        double forceExertedByYB = planetA.calcForceExertedByY(planetB);
        double forceExertedByXA = planetB.calcForceExertedByX(planetA);
        double forceExertedByYA = planetB.calcForceExertedByY(planetA);
        checkEquals(forceExertedByXB,-forceExertedByXA,"check pair force in X-axis",0.01);
        checkEquals(forceExertedByYB,-forceExertedByYA,"check pair force in Y-axis",0.01);
    }

}
