import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private int size;
    private int[] grid;
    private int[] status;
    private WeightedQuickUnionUF uf;
    private boolean percolates = false;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be lager than 1");
        }
        size = n;
        grid = new int[n*n];
        status = new int[n*n];
        uf = new WeightedQuickUnionUF(n * n);
        for (int i = 0; i < n * n; i++) {
            grid[i] = 0;
            status[i] = 0;
        }
    }

    public void open(int row, int col) {
        int pos = translate(row, col);
        // mark position open
        if (grid[pos] > 0) {
            return;
        }
        grid[pos] = 1;

        // mark component status
        if (row ==1 && row ==size){
            status[pos] = 1;
            percolates = true;
            return;
        } else if (row == 1) {
            status[pos] = 1;
        } else if (row == size) {
            status[pos] = 2;
        }

        // check open neighours
        for (int n : neighours(row, col)) {
            if (pos != n && isOpen(n)) {
                int nCompId = uf.find(n); 
                int posCompId = uf.find(pos);
                uf.union(pos, n);

                if ((status[posCompId] + status[nCompId]) == 3) {
                    status[nCompId] = 1;
                    status[posCompId] =1;
                    this.percolates = true;
                    return;
                } 

                
                if (status[nCompId] == status[posCompId]) {
                    continue;
                }
                int newCompId = uf.find(pos);
                status[newCompId] = Math.max(status[posCompId], status[nCompId]);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return isOpen(translate(row, col));
    }

    private boolean isOpen(int n) {
        return grid[n] > 0;
    }

    public boolean isFull(int row, int col) {
        return status[uf.find(translate(row, col))] == 1;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size * size; i++) {
            count += grid[i];
        }
        return count;
    }
    
    public boolean percolates() {
        return percolates;
    }

    public static void main(String[] args) {

    }

    private int translate(int row, int col) {
        if ((row < 1) || (col < 1) || (row > size) || (col > size)) {
            throw new java.lang.IllegalArgumentException();
        }
        return (row -1) * size + col -1;
    }

    private int[] neighours(int row, int col){
        int up = Math.max(row - 1, 1);
        int left = Math.max(col - 1, 1);
        int righ = Math.min(size, col + 1);
        int down = Math.min(row + 1, size);

        int[] ab = new int[4];

        ab[0] = translate(up, col);
        ab[1] = translate(row, left);
        ab[2] = translate(row, righ);
        ab[3] = translate(down, col);
        return ab;
    }
}