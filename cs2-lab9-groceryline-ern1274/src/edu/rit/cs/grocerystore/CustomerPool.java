package edu.rit.cs.grocerystore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerPool {
    TSQueue<Cart> checkoutLine;
    int numCustomers;
    int avgLoad;
    double avgDelay;

    int max;
    double maxDelay;

    /**
     * Starts up the customer pool constructor and sets up the number of
     * customers to make and the average number of items in the carts and
     * the average time between each customer
     * @param checkoutLine: the queue that eqch customer will link to
     * @param numCustomers: the number of customers to make
     * @param avgLoad: the average number of items in the cart
     * @param avgDelay: the average time the thread will sleep
     */
    public CustomerPool(TSQueue<Cart> checkoutLine, int numCustomers, int avgLoad, double avgDelay){
        this.checkoutLine = checkoutLine;
        this.numCustomers= numCustomers;
        this.avgLoad = avgLoad;
        this.avgDelay = avgDelay;

        this.max = avgLoad*2;
        this.maxDelay = avgDelay*2;
    }

    /**
     * Simulates aka starts up the customers and starts their threads executing their run methods
     * @return the list of carts from the customers
     */
    public List<Cart> simulateCustomers(){
        List<Cart> carts = new LinkedList<>();
        for (int i = 0; i < numCustomers; i++) {
            double delay = ThreadLocalRandom.current().nextDouble(0,maxDelay);
            Cart cart = new Cart(ThreadLocalRandom.current().nextInt(1,max));


            Customer cust = new Customer(delay,cart,checkoutLine);
            carts.add(cart);

            Thread custom = new Thread(cust);
            custom.start();
            try {
                custom.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
       return carts;
    }
}
