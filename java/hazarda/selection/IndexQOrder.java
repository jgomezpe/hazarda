package hazarda.selection;

import kompari.integer.IntOrder;
import kompari.real.DoubleOrder;

public class IndexQOrder implements IntOrder{
    protected double[] q;
    protected DoubleOrder order;
	  
    public IndexQOrder( double[] q, DoubleOrder order ){
	this.q = q; 
	this.order = order;
    }
    
    /**
     * Determines if the first Double is less than (in some order) the second Double (one<two)
     * @param one First Double
     * @param two Second Double
     * @return (one<two)
     */
    public int compare(Integer one, Integer two){
	return order.compare(q[two],q[one]); 
    }
}
