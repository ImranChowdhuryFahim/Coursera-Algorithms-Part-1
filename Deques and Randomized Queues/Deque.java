import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal argument exception");
        }

        Node newNode = new Node(item);
        newNode.next = first;
        newNode.prev = null;
        if (last == null) {
            last = newNode;
        }
        if (first != null) {
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal argument exception");
        }

        Node newNode = new Node(item);
        newNode.next = null;
        newNode.prev = last;
        if (first == null) {
            first = newNode;

        }
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        size++;

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No such element");
        }

        Item currentItem = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        size--;
        if (isEmpty()) {
            last = first;
        }
        return currentItem;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No such element");
        }

        Item curreItem = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        size--;
        if (isEmpty()) {
            first = last;
        }
        return curreItem;
    }

    @Override
    public Iterator<Item> iterator() {

        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No such elements");
            }
            Item curreItem = current.item;
            current = current.next;
            return curreItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation");
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        // deque.removeLast();
        System.out.println(deque.size());
        deque.addFirst(5);
        System.out.println(deque.size());
        deque.addLast(4);
        System.out.println(deque.size());
        deque.addLast(6);
        System.out.println(deque.size());

        for (int i : deque) {
            System.out.println(i);
        }

        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
    }

}
