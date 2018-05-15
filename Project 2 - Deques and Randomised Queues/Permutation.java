import edu.princeton.cs.algs4.StdIn; 

public class Permutation {
   
   /*
    takes an integer k as a command-line argument; 
    reads in a sequence of strings from standard input; and 
    prints exactly k of them, uniformly at random.   
    */
   
    
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>(); 
        
        while (!StdIn.isEmpty()) {
             queue.enqueue(StdIn.readString());
        }
        
        for(String item : queue) {
            if (k > 0) {
                System.out.println(item);
                k--;
            }
        }
    }
}