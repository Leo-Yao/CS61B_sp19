package hw1.es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
//    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0 ;
        last = 0 ;
        this.fillCount = 0 ;
        this.capacity = capacity ;
        rb = (T[]) new Object[this.capacity] ;
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow") ;
        }
        rb[last] = x ;
        fillCount++ ;
        if (last == this.capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow") ;
        }
        T item = rb[first] ;
        rb[first] = null ;
        fillCount-- ;
        if (first == this.capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow") ;
        }
        return rb[first] ;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int ptr = 0 ;
            @Override
            public boolean hasNext() {
                return ptr != capacity;
            }

            @Override
            public T next() {
                T t = rb[ptr];
                ptr++ ;
                return t;
            }
        };
    }

}

