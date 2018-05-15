import java.util.Iterator;
import java.util.NoSuchElementException;

// linked list data structure to implement a deque
public class Deque<Item> implements Iterable<Item> {
   
   private Node first; 
   private Node last;
   private int n; // size of the deque
   
   // sub class to create nodes for a linked list
   private class Node {
       private Item item;
       private Node next;
       private Node previous;
       public Node(Item nodeItem, Node nextNode, Node previousNode) {
           item = nodeItem;
           next = nextNode;
           previous = previousNode;
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
       if (item == null) throw new IllegalArgumentException("No item provided to add to deque");
       if (isEmpty()) {
           addInitial(item);
       } else {
           Node oldFirst = first;
           Node newNode = new Node(item, oldFirst, null);
           oldFirst.previous = newNode;
           first = newNode;
           n++;
       }
   }
   
   // add the item to the end
   public void addLast(Item item) {
       if (item == null) throw new IllegalArgumentException("No item provided to add to deque");
       if (isEmpty()) {
           addInitial(item);
       } else {
           Node oldLast = last;
           Node newNode = new Node(item, null, oldLast);
           oldLast.next = newNode;
           last = newNode;
           n++;
       }
   }
       
   // add the first item to the deque
   private void addInitial(Item item) {
       Node newNode = new Node(item, null, null);
       first = newNode;
       last = newNode;
       n++;
   }
   
   // remove and return the item from the front
   public Item removeFirst() {
       if (isEmpty()) throw new NoSuchElementException();
       
       Item firstItem = first.item;
       if (n == 1) {
           first = null;
           last = null;
       } else {
           first = first.next;
           first.previous = null;
       }
       n--;
       return firstItem;
   }
   
   // remove and return the item from the end
   public Item removeLast() {
       if (isEmpty()) throw new NoSuchElementException();
       
       Item lastItem = last.item;
       if (n == 1) {
           first = null;
           last = null;
       } else {
           last = last.previous;
           last.next = null;
       }
       n--;
       return lastItem;
   }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() {
       return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
       private Node current = first;
       
       public boolean hasNext() { return current != null; }
     
       public void remove() { throw new UnsupportedOperationException(); }
       
       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }

}