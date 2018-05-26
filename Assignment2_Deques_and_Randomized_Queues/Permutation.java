import java.util.Iterator;

import javax.print.DocFlavor.STRING;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        Iterator<String> itr = rq.iterator();

        for(int i = 0; i < n; i++) {
            StdOut.println(itr.next());
        }
    }
}