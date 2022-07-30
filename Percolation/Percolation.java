import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int openCount;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF weightedQuickUnionUFull;
    private final int size;
    private final int virtualTop;
    private final int virtualBootm;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        openCount = 0;
        size = n;
        virtualTop = n * n;
        virtualBootm = n * n + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        weightedQuickUnionUFull = new WeightedQuickUnionUF(n * n + 1);
        grid = new boolean[n][n];

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col))
            return;

        grid[row - 1][col - 1] = true;
        openCount++;

        if (row == 1) {
            weightedQuickUnionUF.union(virtualTop, flatten(row, col));
            weightedQuickUnionUFull.union(virtualTop, flatten(row, col));
        }

        if (row == size) {
            weightedQuickUnionUF.union(virtualBootm, flatten(row, col));
        }

        if (isInGrid(row, col - 1) && isOpen(row, col - 1)) {
            weightedQuickUnionUF.union(flatten(row, col), flatten(row, col - 1));
            weightedQuickUnionUFull.union(flatten(row, col), flatten(row, col - 1));
        }
        if (isInGrid(row, col + 1) && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(flatten(row, col), flatten(row, col + 1));
            weightedQuickUnionUFull.union(flatten(row, col), flatten(row, col + 1));
        }
        if (isInGrid(row + 1, col) && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(flatten(row, col), flatten(row + 1, col));
            weightedQuickUnionUFull.union(flatten(row, col), flatten(row + 1, col));
        }
        if (isInGrid(row - 1, col) && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(flatten(row, col), flatten(row - 1, col));
            weightedQuickUnionUFull.union(flatten(row, col), flatten(row - 1, col));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return weightedQuickUnionUFull.find(flatten(row, col)) == weightedQuickUnionUFull.find(virtualTop);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.find(virtualTop) == weightedQuickUnionUF.find(virtualBootm);
    }

    private void validate(int row, int col) {
        if (!isInGrid(row, col)) {
            throw new IllegalArgumentException("Index is out of bound.");
        }
    }

    private int flatten(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    private boolean isInGrid(int row, int col) {

        return row > 0 && col > 0 && row <= size && col <= size;
    }

    // test client (optional)
    public static void main(String[] args) {
        // nothing to do here
    }
}