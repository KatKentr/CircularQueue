/******************************************************************************
* Compilation: javac p1ex.java
* Execution: java p1ex < in-p1ex.txt > out-p1ex.txt
* Dependencies: StdIn.java StdOut.java Stack.java ……
* Data files: in-p1ex.txt
*
******************************************************************************/
 
//imlementation of the circular queue with iterator not completed
import java.util.NoSuchElementException;

/**
 *  This implemetation of a circular queue is based on the {@code Queue} class, which represents a first-in-first-out (FIFO)
 *  queue of generic items, presented in the book Algorithms by the authors Robert Sedgewick and Kevin Wyne.
 *  @param <Item> the generic type of an item in this queue
 */
public class QueueCircularIterator<Item>  {
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
    public QueueCircularIterator() {
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
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
//    public Item peek() {
//        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
//        return first.item;
//    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(Item item) {
    	// Initialize a temporary item -> temp to hold always the first item
    	QueueCircularIterator.Node<Item> temp = new Node<Item>();
    	
    	// Save the current last item of the list before adding a new one
    	Node<Item> oldlast = last;
    	
    	// Initialize a new item -> which will be the new last item of our list
        last = new Node<Item>();
        // Set the new item value to the one given by the user
        last.item=item;
       
        // Check if list is empty and handle the queue accordingly
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
        //Item item = first.item;
        //first = first.next;
    	
    	Item item=last.next.item;
    	last.next=last.next.next;
    	n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
        

        
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (Item item : this) {
//            s.append(item);
//            s.append(' ');
//        }
//        return s.toString();
//    } 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
//    public Iterator<Item> iterator()  {
//        return new LinkedIterator(first);  
//    }

    // an iterator, doesn't implement remove() since it's optional
//    private class LinkedIterator implements Iterator<Item> {
//        private Node<Item> current;
//
//        public LinkedIterator(Node<Item> first) {
//            current = first;
//        }
//
//        public boolean hasNext()  { return current != null;                     }
//        public void remove()      { throw new UnsupportedOperationException();  }
//
//        public Item next() {
//            if (!hasNext()) throw new NoSuchElementException();
//            Item item = current.item;
//            current = current.next; 
//            return item;
//        }
//    }


    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        QueueCircularIterator<String> queue = new QueueCircularIterator<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            //else if (!queue.isEmpty())
                //StdOut.print(queue.dequeue() + " ");
        }
        
        StdOut.println("Queue size: " + queue.size());
        
        while (!queue.isEmpty()) {
        	
        	StdOut.print(queue.dequeue() + " ");
        	
        }
        StdOut.println("\nQueue size: " + queue.size());
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
