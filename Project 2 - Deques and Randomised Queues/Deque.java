public class Deque<Item> implements Iterable<Item> {
   
   private Item first; 
   private Item last;
   private int n; // size of the deque
   
   // sub class to create nodes for a linked list
   private class Node {
       private Item item;
       private Item next;
   }
   
   // construct an empty deque
   public Deque() {
       first = null;
       last = null;
       n = 0;
   }
   
   // is the deque empty?
   public boolean isEmpty() {
       return n == 0;
   }
   
   // return the number of items on the deque
   public int size() {
       return n;
   }
   
   public void addFirst(Item item)          // add the item to the front
   public void addLast(Item item)           // add the item to the end
   public Item removeFirst()                // remove and return the item from the front
   public Item removeLast()                 // remove and return the item from the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing (optional)
}