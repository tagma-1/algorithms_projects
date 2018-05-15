import edu.princeton.cs.algs4.StdRandom; 
    
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] a;
    private int n;
    private int tail;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
        tail = 0;
    }
    
    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }
    
    // return the number of items on the randomized queue
    public int size() {
        return n;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (tail == a.length) { adjustArray(n * 2) }; // adjust the array (resize and/or compress the items) if the tail has reached the end
        a[tail] = item;
        tail++;
        n++;
    }    
    
    // remove and return a random item
    public Item dequeue() {
    StdRandom.uniform(n)
    }
    
    private void adjustArray(int size) {
        Item[] newArray = (Item[]) new Object[size];
        int counter = 0; // counter for index to insert items into newArray
        for(int i = 0; i < tail; i++) {
            if (a[i] != null) {
                newArray[counter] = a[i];
                counter++; // counter is only incremented when an item is added - this ensures null values are not copied 
            }
        }
        tail = counter; // update the tail value 
        a = newArray;
    }
    
    public Item sample()                     // return a random item (but do not remove it)
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    public static void main(String[] args)   // unit testing (optional)
}