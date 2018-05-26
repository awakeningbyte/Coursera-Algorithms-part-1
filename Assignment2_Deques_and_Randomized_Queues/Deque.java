import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;
    public Deque() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can not be null");
        }

        Node<Item> newNode = new Node<Item>(item,null, this.first);
        this.first.setPosition(newNode, this.first.next);
        this.first = newNode;
        this.size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can not be null");
        }
        Node<Item> newNode = new Node<Item>(item,this.last,null);
        this.last.setPosition(this.last.pre,newNode); 
        this.last = newNode;
        this.size++;
    }

    public Item removeFirst() {

        if (this.isEmpty()) {
            throw new NoSuchElementException("Item can not be null");
        }
        Node<Item> head = this.first;
        Node<Item> newFirst = head.next;
        this.first = newFirst;
        this.size--;

        if (newFirst == null) {
            this.last = null;
        } else {
            newFirst.setPosition(null, newFirst.next);
        }

        return head.value;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Item can not be null");
        }
        Node<Item> eol = this.last;
        Node<Item> newLast = eol.pre;
        this.last = newLast;
        this.size--;

        if (newLast == null) {
            this.first = null;
        } else {
            newLast.setPosition(newLast.pre, null);        
        }

        return eol.value;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> current = Deque.this.first;

            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override 
            public Item next() {
               if (current == null) {
                   throw new NoSuchElementException();
               }

               Item value = current.value;
               current = current.next;
               return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };  
    }

    public static void main(String[] args) {

    }
    
    private class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> pre;

        private Node(Item value, Node<Item> pre, Node<Item> next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }

        private void setPosition(Node<Item> pre, Node<Item> next) {
            this.pre = pre;
            this.next = next;
        }
    }
}
