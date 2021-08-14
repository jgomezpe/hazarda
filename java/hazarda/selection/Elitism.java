package hazarda.selection;

import hazarda.integer.Roulette;
import kompari.real.DoubleOrder;
import kompari.real.H2LOrder;
import kompari.real.L2HOrder;
import speco.array.Array;
import speco.array.SortedArrayMultiSet;

public class Elitism implements Selection{
    /**
     * Elite percentage: Percentage of individuals to be included in the selection
     * according to their OptimizationFunction
     */
    protected double elite_percentage = 0.1;

    /**
     * Cull percentage: percentage of individuals to be excluded in the selection
     * according to their OptimizationFunction
     */
    protected double cull_percentage = 0.1;

    /**
     * Constructor: Create a Elitist selection strategy.
     * @param _elite_percentage Percentage of individuals to be included in the selection
     * @param _cull_percentage Percentage of individuals to be excluded in the selection
     */
    public Elitism( double _elite_percentage, double _cull_percentage ){
	elite_percentage = _elite_percentage;
	cull_percentage = _cull_percentage;
    }
	  
    protected class IndexQ{
	public int index;
	public double q;      
	public IndexQ( int index, double q ){
	    this.index = index;
	    this.q = q;
	}
    }
	  
    /**
     * Selects a subset of candidate solutions from a set of candidates
     * @param n Number of candidate solutions to be selected
     * @param q Quality associated to each candidate solution
     * @param order Quality order (low to high, high to low, etc)
     * @return Indices of the selected candidate solutions
     */
    public int[] apply( int n, double[] q, DoubleOrder order ){
	int[] sel = new int[n];
	int s = q.length;
	Array<Integer> array = new Array<Integer>();
	SortedArrayMultiSet<Integer> indexq = new SortedArrayMultiSet<Integer>(array, new IndexQOrder(q, order));
	for( int i=0; i<s; i++ ) indexq.add(i);
	int m = (int) (s * elite_percentage);
	for (int i = 0; i < n && i < m; i++) sel[i] = array.get(i);
	if( m<n ){
	    int k = (int) (s * (1.0 - cull_percentage));
	    double[] weight = new double[k];
	    double total = k * (k + 1) / 2.0;
	    for (int i = 0; i < k; i++) weight[i] = (k - i) / total;
	    Roulette generator = new Roulette(weight);
	    n -= m;
	    int[] index = generator.generate(n);
	    for (int i=0; i<n; i++) sel[m+i] = array.get(index[i]);
	}
	return sel;
    }

    @Override
    public int[] natural(int n, double[] q) { return apply(n, q, new H2LOrder()); }

    @Override
    public int[] reversed(int n, double[] q) { return apply(n, q, new L2HOrder()); }
}