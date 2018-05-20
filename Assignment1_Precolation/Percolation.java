import com.sun.javafx.scene.traversal.WeightedClosestCorner;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    int _size;
    int[] _grid;
    int[] _status;
    WeightedQuickUnionUF _uf;
    boolean _percolates = false;
    public Percolation(int n) {
        _size = n;
        _grid = new int[n*n];
        _status = new int[n*n];
        _uf = new WeightedQuickUnionUF(n*n);
    }

    public void open(int row, int col) {
        int pos = translate(row, col);
        //mark position open
        _grid[pos] =1;

        //mark component status
        if (row ==0) {
            _status[pos] = 1;
        } else if (row == _size ) {
            _status[pos] = 2;
        }

        //check open neighours
        for(int n: neighours(row, col)) {
            if (pos != n && isOpen(n)) {
                int n_compId = _uf.find(n); 
                int pos_compId = _uf.find(pos);

                if ((_status[pos_compId] + _status[n_compId])==3) {
                    this._percolates = true;
                    return;
                } 
                if (_status[n_compId] == _status[pos_compId]) {
                    continue;
                }

                _uf.union(pos, n);
                int newCompId = _uf.find(pos);
                
                _status[newCompId] = Math.max(_status[pos_compId], _status[n_compId]);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return isOpen(translate(row, col));
    }
    public boolean isOpen(int n){
        return _grid[n]>0;
    }

    public boolean isFull(int row, int col){
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

    private int translate(int row, int col) {
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