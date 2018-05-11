public class Deque<Item> implements Iterable<Item> {
   
   private Node first; 
   private Node last;
   private int n; // size of the deque
   
   // sub class to create nodes for a linked list
   private class Node {
       private Item item;
       private Node next;
       public Node(Item nodeItem, Node nextNode) {
           item = nodeItem;
           next = nextNode;
       }
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
   
   // add the item to the front
   public void addFirst(Item item) {
       if (n == 0) {
           return addInitial(item);
       } else {
           Node oldFirst = first;
           newNode = new Node(item, oldFirst);
           first = newNode;
           n++;
       }
   }
   
   // add the item to the end
   public void addLast(Item item) {
       if (n == 0) {
           return addInitial(item);
       } else {
           Node oldLast = last;
           newNode = new Node(item, null);
           oldLast.next = newNode;
           last = newNode;
           n++;
       }
   }
       
   // add the first item to the deque
   private void addInitial(Item item) {
       newNode = new Node(item, null);
       first = newNode;
       last = newNode;
       n++;
   }
   
   public Item removeFirst()                // remove and return the item from the front
   public Item removeLast()                 // remove and return the item from the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing (optional)
}