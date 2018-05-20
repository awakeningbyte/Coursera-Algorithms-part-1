import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    int _size;
    int[] _grid;
    int[] _status;
    WeightedQuickUnionUF _uf;
    boolean _percolates = false;
    public Percolation(int n) throws java.lang.IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        _size = n;
        _grid = new int[n*n];
        _status = new int[n*n];
        _uf = new WeightedQuickUnionUF(n*n);
        for (int i=0;i<n*n; i++) {
            _grid[i]=0;
            _status[i]=0;
        }
    }

    public void open(int row, int col) throws java.lang.IllegalArgumentException {
        int pos = translate(row, col);
        //mark position open
        if (_grid[pos] > 0) {
            return;
        }
        _grid[pos] =1;

        //mark component status
        if (row ==1) {
            _status[pos] = 1;
        } else if (row == _size ) {
            _status[pos] = 2;
        }

        //check open neighours
        int current = 0;

        for(int n: neighours(row, col)) {
            if (pos != n && isOpen(n)) {
                int n_compId = _uf.find(n); 
                int pos_compId = _uf.find(pos);

                if ((_status[pos_compId] + _status[n_compId])==3) {
                    this._percolates = true;
                    return;
                } 

                _uf.union(pos, n);
                
                if (_status[n_compId] == _status[pos_compId]) {
                    continue;
                }
                int newCompId = _uf.find(pos);
                _status[newCompId] = Math.max(_status[pos_compId], _status[n_compId]);

            }
        }
    }

    public boolean isOpen(int row, int col) throws java.lang.IllegalArgumentException{
        return isOpen(translate(row, col));
    }
    public boolean isOpen(int n){
        return _grid[n]>0;
    }

    public boolean isFull(int row, int col) throws java.lang.IllegalArgumentException {
        return _status[_uf.find(translate(row, col))]==1;
    }

    public int numberOfOpenSites() {
        int count =0;
        for(int i=0;i < _size*_size; i++) {
            count += _grid[i];
        }
        return count;
    }
    
    public boolean percolates() {
        return _percolates;
    }

    public static void main(String[] args) {

    }

    private int translate(int row, int col) throws java.lang.IllegalArgumentException {
        if ((row < 0) || (col < 0) || (row > _size) || (col > _size)) {
            throw new java.lang.IllegalArgumentException();
        }
        return (row -1) * _size + col -1;
    }

    private int[] neighours(int row, int col){
        int up = Math.max(row-1, 1);
        int left= Math.max(col-1, 1);
        int righ = Math.min(_size, col+1);
        int down = Math.min(row+1, _size);

        int[] ab = new int[4];

        ab[0] = translate(up,col);
        ab[1] = translate(row, left);
        ab[2] = translate(row, righ );
        ab[3] = translate(down, col);
        return ab;
        
    }
}