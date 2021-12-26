package edu.rit.cs.grocerystore;

// TODO
// This code is provided so that the initial code compiles.
// It is up to you to fill it in properly and add comments.

public class Cart extends TimedObject implements Comparable< Cart > {
    int numItems;

    /**
     * The constructor for the utilities.NO_MORE_CARTS
     */
    public Cart() {
        this.numItems = 0;
    }

    /**
     * the normal constructor where it initializes the cart with the number of items
     * @param numItems: the num of items in the cart
     */
    public Cart( int numItems ) {
        this.numItems = numItems;
    }

    /**
     * returns cart size/ num of items in cart
     * @return
     */
    public int getCartSize() {
        return numItems;
    }

    /**
     * Compares itself to other carts by the number of items in the cart since it is
     * the only variable it can compare itself to the other
     * @param other: the other Cart
     * @return: -1 for this is smaller than other, 0 for this is equal, 1 for this is bigger than the other
     */
    @Override
    public int compareTo( Cart other ) {
        return Integer.compare(this.numItems, other.getCartSize());
    }

    /**
     * A toString to return the carts with the num of items in it
     * @return: A String
     */
    @Override
    public String toString(){
        return "Cart("+numItems+")";
    }

}
