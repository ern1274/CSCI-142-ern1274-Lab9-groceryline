package edu.rit.cs.grocerystore;

public class Customer implements  Runnable{
    static int id = 0;
    double delay;
    Cart cart;
    TSQueue<Cart> queue;

    /**
     * Starts up the Customer variables with the cart and queue
     * @param delay: the time the customer/thread will sleep
     * @param cart: the cart, the amount of items in it
     * @param queue: the queue holding all the carts
     */
    public Customer(double delay, Cart cart, TSQueue<Cart>queue){
        this.delay = delay;
        this.cart = cart;
        this.queue = queue;
        id++;
    }

    /**
     * The threads run method, essentially sleeps the customer for the
     * delayed time and enqueues the item onto the queue, this is the initialization of the
     * customer pool
     */
    @Override
    public void run() {

        try {
            Thread.sleep((long) delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num = queue.enqueue(this.cart)-1;
        Utilities.println("Customer #"+id+" with "+cart+" has entered the line, with "+num+" customers in front");
    }
}
