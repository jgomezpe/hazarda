package hazarda.selection;

import hazarda.Hazarda;

public class Tournament implements Selection{
    /**
     * The tournament size
     */
    protected int m;

    /**
     * Constructor: Create a tournament selection strategy with 4 players.
     */
    public Tournament(){ this(4); }

    /**
     * Constructor: Create a tournament selection strategy with m players.
     * @param m The number of players in the tournament
     */
    public Tournament( int m ){ this.m = m; }

    protected int[] uniform(int n){
	int[] candidates = new int[m];
	for( int i=0; i<m; i++ ){
	    candidates[i] =Hazarda.uniform(n);
	}
	return candidates;
    }
    
    @Override
    public int[] natural(int n, double[] q) {
	int[] s = new int[n];
	for( int k=0; k<n; k++ ) {
	    int[] c = uniform(q.length);
	    s[k] = c[0];
	    for( int i=1; i<c.length; i++ )
		if(q[c[i]] > q[s[k]]) s[k] = c[i];
	}
	return s;
    }

    @Override
    public int[] reversed(int n, double[] q) {
	int[] s = new int[n];
	for( int k=0; k<n; k++ ) {
	    int[] c = uniform(q.length);
	    s[k] = c[0];
	    for( int i=1; i<c.length; i++ )
		if(q[c[i]] < q[s[k]]) s[k] = c[i];
	}
	return s;
    }
}