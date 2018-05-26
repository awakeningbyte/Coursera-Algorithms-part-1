import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int ptr;
    public RandomizedQueue() {
        this.array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return this.ptr == 0;
    }

    public int size() {
        return this.ptr;
    }

    public void enqueue(Item item) {
        if (item == null){
            throw new IllegalArgumentException();
        }
        this.array[ptr] = item;
        this.ptr++;
        if (this.ptr  == this.array.length) {
            this.resize(2 * ptr);
        }
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int n = StdRandom.uniform(0, ptr);
        Item item = this.array[n];
        this.array[n] = this.array[ptr--];
        this.array[ptr]=null;
        if (this.ptr > 0 && this.ptr <= this.array.length / 4) {
            this.resize(this.array.length / 2);
        }
        return item;
    }
    
    private void resize(int n) {
        Item[] newArray = (Item[])new Object[n];
        for (int i =0; i < this.ptr; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int n = StdRandom.uniform(0, ptr);
        return this.array[n];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int[] pmt = StdRandom.permutation(RandomizedQueue.this.size());
            private int i = 0;
            @Override 
            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }

                return RandomizedQueue.this.array[pmt[i++]];
            }

            @Override
            public boolean hasNext() {
                return i < pmt.length;
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }



        };
    }

    public static void main(String[] args) {

    }

   }