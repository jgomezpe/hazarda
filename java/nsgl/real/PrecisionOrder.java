package nsgl.real;


/**
 * <p>Doubles considering the double precision defined in DoubleUtil class</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class PrecisionOrder implements Order{
    /**
     * Precision for considering two double values equivalents
     */
    public static final double PRECISION = 1e-10;
    
    /**
     * Determines if the given double value is zero (according to the precision) or not
     * @param x double value to be analized
     * @return <i>true</i> if <i>abs(x) <= precision</i>, <i>false</i> otherwise
     */
    public static boolean isZero(double x) {
        return (Math.abs(x) <= PRECISION);
    }
    
    /**
     * Determines if the first Double is less than (in some order) the second Double (one<two) considering the precision
     * defined in DoubleUtil class
     * @param one First Double
     * @param two Second Double
     * @return (one<two)
     */
    @Override
    public int compare(double one, double two){ 
	return (Math.abs(one - two) <= PRECISION)?0:((Double)one).compareTo((Double)two); 
    }

    /**
     * Determines if the first Double is less than (in some order) the second Double (one<two) considering the precision
     * defined in DoubleUtil class
     * @param one First Double
     * @param two Second Double
     * @return (one<two)
     */
    public int compare(Double one, Double two){
	return (Math.abs(one - two) <= PRECISION)?0:one.compareTo(two); 
    }

    @Override
    public int compare(Object one, Object two) { return compare((Double)one,(Double)two); }
}