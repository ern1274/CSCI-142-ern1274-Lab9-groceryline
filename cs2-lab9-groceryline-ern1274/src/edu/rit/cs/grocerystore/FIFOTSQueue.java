package edu.rit.cs.grocerystore;

import java.util.LinkedList;
import java.util.List;

// TODO
// This code is provided so that the initial code compiles.
// It is up to you to fill it in properly and add comments.

public class FIFOTSQueue< E extends TimedObject > implements TSQueue< E > {
    private List<E> contents;

    /**
     * The Constructor for FIFO Queue
     * initializes a new linkedlist to hold the values of E/anything
     */
    public FIFOTSQueue() {
        this.contents = new LinkedList<>();
    }

    /**
     * puts a value in the queue automatically adds the value to the end of the list
     * @param value the value to be enqueued
     * @return The index of the value in the queue
     */
    @Override
    public synchronized int enqueue( E value ) {
        value.enterQueue();
        contents.add(value);
        notifyAll();
        return contents.size();
    }

    /**
     * removes the first index automatically since
     * enqueue sorts it already and returns the value at that index
     * @return A Cart
     */
    @Override
    public synchronized E dequeue() {
        while(contents.size()==0){
            try{
                this.wait();
            }
            catch(InterruptedException e){}
        }
        notifyAll();
        E value = contents.remove(0);
        value.exitQueue();
        return value;

    }
}
