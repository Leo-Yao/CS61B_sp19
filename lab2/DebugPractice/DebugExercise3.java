package lab2.DebugPractice;


import proj0.examples.In;

import java.io.File;

/**
 * Created by jug on 1/22/18.
 */
public class DebugExercise3 {
    public static int countTurnips(In in) {
        int totalTurnips = 0;
        while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            int numAvailable = in.readInt();
            if (foodType.equals("turnip")) {
                int newTotal = totalTurnips + numAvailable;
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        File file = new File("D:\\java_workspace\\CS61B\\lab2\\DebugPractice\\foods.csv");
        In in = new In(file);
        System.out.println(countTurnips(in));
    }
}
