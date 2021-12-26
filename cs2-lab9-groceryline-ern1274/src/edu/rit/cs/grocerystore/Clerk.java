package edu.rit.cs.grocerystore;

public class Clerk implements Runnable{
    TSQueue<Cart> checkoutLine;

    /**
     * only initializes the queue
     * @param checkoutLine: the queue
     */
    public Clerk(TSQueue<Cart> checkoutLine){
        this.checkoutLine = checkoutLine;
    }

    /**
     * the threads run method for clerk
     * Loops until the next cart is the Utilities.NO_MORE_CARTS
     * until then it removes the cart and sleeps to simulate checkout time and finishes
     * the timedObject for that cart
     */
    @Override
    public void run() {
        boolean test = true;
        while(test){
            Cart next = checkoutLine.dequeue();
            if(next.compareTo(Utilities.NO_MORE_CARTS)==0){
                test = false;
            }
            else{
                Utilities.println("Clerk got "+next.toString());
                try {
                    Thread.sleep(next.numItems*Utilities.TIME_PER_CART_ITEM);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                next.servicingDone();
            }
        }
    }
}
