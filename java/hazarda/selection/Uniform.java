package hazarda.selection;

public class Uniform implements Selection{
    /**
     * Default constructor
     */
    public Uniform(){ }

    /**
     * Selects a subset of candidate solutions from a set of candidates
     * @param n Number of candidate solutions to be selected
     * @param q Quality associated to each candidate solution
     * @return Indices of the selected candidate solutions
     */
    @Override
    public int[] natural(int n, double[] q){
	hazarda.integer.Uniform u = new hazarda.integer.Uniform(q.length);
 	return u.generate(n);
    }

    @Override
    public int[] reversed(int n, double[] q) {
	return natural(n,q);
    }	  
}