import java.util.Iterator;
import java.util.NoSuchElementException;
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
        if (item == null) throw new IllegalArgumentException("Null item value provided");
        if (tail == a.length) adjustArray((n + 1) * 2); // adjust the array (resize and/or compress the items) if the tail has reached the end
        a[tail] = item;
        tail++;
        n++;
    }    
    
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = randomIndex(tail); 
        Item item = a[index];
        a[index] = null;
        n--;
        if (index == tail - 1) tail--; // re-use the array position if the item deleted is the last array item
        if (n > 0 && n == a.length / 4) adjustArray(a.length / 2); // adjust the array (resize and/or compress the items) if items fill less than a quarter of array
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = randomIndex(tail);
        return a[index];
    }
    
    // generate random array indices until an item is found    
    private int randomIndex(int n) {
        int randomInt = StdRandom.uniform(n);
        while (a[randomInt] == null) {
            randomInt = StdRandom.uniform(n);
        }
        return randomInt;
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
    
    public int arrayLength() { return a.length; }
    
    public void printArray() { 
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private Item[] iterationArray;
        private int i;
        
        // create a copy of the data structure with items only and shuffle
        public RandomizedQueueIterator() {
            iterationArray = (Item[]) new Object[n];
            int counter = 0;
            for (int i = 0; i < tail; i++) {
                if(a[i] != null) {
                    iterationArray[counter] = a[i];
                    counter++;
                }
            }
            StdRandom.shuffle(iterationArray);
        }
        
        public void remove() { throw new UnsupportedOperationException(); }
        
        public boolean hasNext() {
            return i < iterationArray.length;
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = iterationArray[i];
            i++;
            return item;
        }
        
    };
}