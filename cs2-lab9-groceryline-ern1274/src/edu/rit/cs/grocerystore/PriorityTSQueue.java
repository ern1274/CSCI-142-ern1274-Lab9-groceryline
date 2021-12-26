package edu.rit.cs.grocerystore;

import java.util.LinkedList;
import java.util.List;


public class PriorityTSQueue< E extends TimedObject & Comparable< E > >
        implements TSQueue< E > {
    private List<E> contents;
    /**
     * The Constructor for Priority Queue
     * initializes a new linkedlist to hold the values of E/anything
     */
    public PriorityTSQueue() {
        this.contents = new LinkedList<>();
    }

    /**
     * You can't edit the queue during a loop of the queue
     * so use an index and a boolean value to indicate if
     * the queue has found a spot for the current value
     * if not, then it is the biggest value
     *
     * @param value the value to be enqueued
     *
     * @return the size of the queue
     */
    @Override
    public synchronized int enqueue( E value ) {
        int index = 0;
        boolean check = false;
        if(contents.size()==0){
            value.enterQueue();
            contents.add(index,value);
            notifyAll();
            return contents.size();
        }
        else {
            for (E e : contents) {
                if (value.compareTo(e) < 0) {
                    index = contents.indexOf(e);
                    check = true;
                    break;
                }
            }
        }
        if(check) {
            value.enterQueue();
            contents.add(index, value);
        }
        else{
            value.enterQueue();
            contents.add(value);
        }
        notifyAll();
        return contents.size();
    }

    /**
     * Dequeue, to take out a cart out of the queue and return that cart
     * it automatically takes out the first one because enqueue already orders
     * queue
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
        contents.get(0).exitQueue();
        notifyAll();
        return contents.remove(0);
    }
}
