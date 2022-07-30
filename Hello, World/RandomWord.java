import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        String champion = "";
        String current = "";
        int count = 1;
        while (!StdIn.isEmpty()) {
            current = StdIn.readString();

            if (StdRandom.bernoulli((double) 1 / count)) {
                champion = current;
            }

            count++;

        }

        StdOut.println(champion);

    }

}
