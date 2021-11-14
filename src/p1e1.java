/******************************************************************************
* Compilation: javac p1e1.java
* Execution: java p1e1 < in-p1e1.txt > out-p1e1.txt
* Dependencies: StdIn.java StdOut.java QueueCircular.java
* Data files: in-p1e1.txt
*
******************************************************************************/


public class p1e1 {
	/**
	 * @param args
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


