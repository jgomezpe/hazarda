package hazarda.selection;

import kompari.real.DoubleOrder;
import kompari.real.H2LOrder;
import kompari.real.L2HOrder;
import speco.array.Array;
import speco.array.SortedArrayMultiSet;

public class Ranking implements Selection {
    public Ranking(){}
	
    public int[] apply(int n, double[] q, DoubleOrder order) {
	int s = q.length;
	Array<Integer> array = new Array<Integer>();
	SortedArrayMultiSet<Integer> indexq = new SortedArrayMultiSet<Integer>(array, new IndexQOrder(q, order));
	for( int i=0; i<s; i++ ) indexq.add(i);

	double[] nq = new double[q.length];
	for( int i=0; i<nq.length; i++ ) nq[i] = i+1;
	
	Roulette roulette = new Roulette();
	
	int[] sel = roulette.natural(n, nq);
	for( int i=0; i<sel.length; i++ ) sel[i] = array.get(sel[i]);
	return sel;
    }

    @Override
    public int[] natural(int n, double[] q) {
	return apply(n, q, new L2HOrder());
    }

    @Override
    public int[] reversed(int n, double[] q) {
	return apply(n, q, new H2LOrder());
    }
}