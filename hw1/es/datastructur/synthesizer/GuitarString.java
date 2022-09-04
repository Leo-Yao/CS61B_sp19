package hw1.es.datastructur.synthesizer;

import java.util.ArrayList;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        while(!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue() ;
            double r = Math.random() - 0.5;
            while (list.contains(r)) {
                r = Math.random() - 0.5;
            }
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        Double dequeue = buffer.dequeue();
        Double peek = buffer.peek();
        double newDouble = (dequeue + peek) / 2 * DECAY ;
        buffer.enqueue(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
