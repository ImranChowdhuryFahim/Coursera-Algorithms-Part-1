import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;
    private int capacity;

    public RandomizedQueue() {
        size = 0;
        capacity = 1;
        arr = (Item[]) new Object[capacity];

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal argument");

        }

        if (size == capacity) {
            capacity = capacity * 2;
            resize(capacity);
        }

        arr[size++] = item;

    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No such element");

        }

        if (size <= capacity / 2) {
            capacity = capacity / 2;
            resize(capacity);
        }

        int pos = StdRandom.uniform(size);
        Item curreItem = arr[pos];
        arr[pos] = arr[--size];
        arr[size] = null;
        return curreItem;
    }

    private void resize(int i) {
        Item[] newArr = (Item[]) new Object[i];
        for (int k = 0; k < size; k++) {
            newArr[k] = arr[k];
        }

        arr = newArr;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("No such element");

        }
        int currentPos = StdRandom.uniform(size);
        return arr[currentPos];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private int currentIndex = 0;

        private final Item[] newArr;

        public ListIterator() {
            newArr = (Item[]) new Object[size()];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }

            StdRandom.shuffle(newArr);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < newArr.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No such elements");
            }
            Item curreItem = newArr[currentIndex];
            currentIndex++;
            return curreItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation");
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        System.out.println(randomizedQueue.size());

        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(5);
        randomizedQueue.enqueue(6);
        for (int i : randomizedQueue) {
            System.out.println(i);
        }
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
    }

}
