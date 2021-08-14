package hazarda.selection;

import hazarda.Hazarda;

public class Roulette implements Selection{
    protected static final int ZERO = 0;
    protected static final int NEGATIVE = -1;
    protected static final int POSITIVE = 1;
    protected static final int UNDEFINED = 2;
    protected int type( double[] q ) {
	int p = 0;
	int n = 0;
	for(int i=0; i<q.length; i++)
	    if(q[i] < 0.0) n++;
	    else if(q[i] > 0.0) p++;
	    else {
		n++;
		p++;
	    }
	if(n==q.length) 
	    if(n==p) return ZERO;
	    else return NEGATIVE; 
	else 
	    if(p==q.length) return POSITIVE;
	    else return UNDEFINED;
    }
    
    public static double[] normalize(double[] p) {
	double s = 0.0;
	for(int i=0; i<p.length; i++)
	    s += p[i];
	for(int i=0; i<p.length; i++)
	    p[i] /= s;
	return p;	
    }

    protected double[] natural_density(double[] q) {
	double[] p = new double[q.length];
	int type = type(q);
	switch(type) {
	case UNDEFINED:
	case NEGATIVE: 
	    double min = 0;
	    double max = Double.MIN_VALUE;
	    for(int i=0; i<q.length; i++) {
		if(q[i] < min) min = q[i];
		if(q[i] > max && q[i] < 0) max = q[i];
	    }
	    for(int i=0; i<q.length; i++)
		p[i] = q[i] -(max+min);
	break;
	case ZERO:
	    for(int i=0; i<q.length; i++)
		p[i] = 1.0;
	break;
	case POSITIVE:
	    for(int i=0; i<q.length; i++)
		p[i] = q[i];
	}
	return normalize(p);
    }

    protected double[] reversed_density(double[] q) {
	double[] p = new double[q.length];
	int type = type(q);
	switch(type) {
	case UNDEFINED:
	case POSITIVE: 
	    double min = Double.MAX_VALUE;
	    double max = 0;
	    for(int i=0; i<q.length; i++) {
		if(q[i] < min && q[i] > 0) min = q[i];
		if(q[i] > max) max = q[i];
	    }
	    for(int i=0; i<q.length; i++)
		p[i] = (max+min)-q[i];
	break;
	case ZERO:
	    for(int i=0; i<q.length; i++)
		p[i] = 1.0;
	break;
	case NEGATIVE:
	    for(int i=0; i<q.length; i++)
		p[i] = -q[i];
	}
	return normalize(p);
    }

    @Override
    public int[] natural(int n, double[] q) {
	q = natural_density(q);
	int[] s = new int[n];
	for( int i=0; i<n; i++)
	    s[i] = Hazarda.roulette(q);
	return s;
    }

    @Override
    public int[] reversed(int n, double[] q) {
	q = reversed_density(q);
	int[] s = new int[n];
	for( int i=0; i<n; i++)
	    s[i] = Hazarda.roulette(q);
	return s;
    }
}