/******************************************************************************
* Compilation: javac QueueCircular.java
* Execution: java QueueCircular < in-p1e1.txt > out-p1e1.txt
* Dependencies: StdIn.java StdOut.java 
* Data files: in-p1e1.txt
*
******************************************************************************/
 
import java.util.NoSuchElementException;

/**
 *  This implemetation of a circular queue is based on the {@code Queue} class, which represents a first-in-first-out (FIFO)
 *  queue of generic items, presented in the book Algorithms by the authors Robert Sedgewick and Kevin Wyne.
 *  @param <Item> the generic type of an item in this queue
 */
public class QueueCircular<Item>  {
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty queue.
     */
    public QueueCircular() {
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return n==0;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue, not used though
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return last.next.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(Item item) {
    	// Initializes a temporary item -> temp to hold always the first item
    	QueueCircular.Node<Item> temp = new Node<Item>();
    	
    	// Saves the current last item of the queue before adding a new one
    	Node<Item> oldlast = last;
    	
    	// Declares a new item -> which will be the new last item of our list
        last = new Node<Item>();
        // Set the new item value to the one given by the user
        last.item=item;
       
        // Checks if list is empty and handles the queue accordingly
         if (isEmpty()) {
        	 // If the queue is empty we want to keep the first item accessible from the next items.
        	 // Therefore, we queue the first item to itself, so basically the next item of the first should be itself
             last.next=last;
         }else {
        	 // Up to here the next item of my current last item -> oldlast.next, is always the first item (circular queue)
        	 temp = oldlast.next;
        	 // Now we can change the pointer of the current last item -> oldlast.next to the new last item -> last 
        	 oldlast.next=last;
        	 // Set the next item of my actual last item -> last.next, to the first item (which was saved before) ensuring a circular queue
        	 last.next=temp;
         }
         n++;
//         StdOut.print("\nThe first item: " + temp.item);
//         StdOut.print("\nThe last item: " + last.item);
//         StdOut.print("\nThe next after the last item: " + last.next.item);         
    }
    
    

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
           	
    	Item item=last.next.item;
    	last.next=last.next.next;
    	n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
               
    }

   
    /**
     * Unit tests the {@code QueueCircular} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        QueueCircular<String> queue = new QueueCircular<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);           
        }
        
        StdOut.println("Queue size: " + queue.size());
        
        while (!queue.isEmpty()) {
        	
        	StdOut.print(queue.dequeue() + " ");
        	
        }
        StdOut.println("\nQueue size: " + queue.size());
    }
}


