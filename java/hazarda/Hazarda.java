package hazarda;

import java.lang.reflect.Array;

import hazarda.raw.JavaGenerator;
import hazarda.raw.RawGenerator;
import hazarda.raw.rngpack.RanMT;
import hazarda.raw.rngpack.Ranlux;
import hazarda.raw.rngpack.Ranmar;

public class Hazarda{
    
    protected static RawGenerator raw = new JavaGenerator(); // Raw Generator [0,1)
    
    /**
     * Sets the raw generator for the Hazarda library
     * @param raw RawGenerator used by Hazarda (generates double numbers in the [0,1) interval)
     */
    public static void raw(RawGenerator raw) { Hazarda.raw = raw; }
    
    public static RawGenerator raw() { return raw; }
    
    public static void java() {
	raw(new JavaGenerator());
    }
    
    public static void ranlux(long seed) {
	raw( new Ranlux(seed) );
    }
    
    public static void ranmar(long seed) {
	raw( new Ranmar(seed) );
    }
    
    public static void mersennetwister(long seed) {
	raw( new RanMT(seed) );
    }
    
    public static double next() { return raw.next(); }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void next(double[] v, int offset,  int m){ raw.raw(v, offset, m); }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] next(int m) { return raw.raw(m); }
    
    /**
     * Generates a random number following the Gaussian distribution G(0,1)
     * @return A random number following the Gaussian distribution G(0,1) 
     */
    public static double gaussian(){
	double x,y;
	double r;
	do {
	    x = 2.0 * raw.next() - 1.0;
	    y = 2.0 * raw.next() - 1.0;
	    r = x * x + y * y;
	} while (r >= 1.0);

	double z = Math.sqrt( -2.0 * Math.log(r) / r);
	return (y * z);
    }

    /**
     * Generates a random number following the Gaussian distribution G(0,sigma)
     * @param sigma Standard deviation
     * @return A random number following the Gaussian distribution G(0,sigma) 
     */
    public static double gaussian(double sigma){ return gaussian()*sigma; }

    /**
     * Generates a random number following the Gaussian distribution G(mu,sigma)
     * @param mu Mean
     * @param sigma Standard deviation
     * @return A random number following the Gaussian distribution G(mu,sigma) 
     */
    public static double gaussian(double mu, double sigma){ return mu+gaussian(sigma); }
   
    public static double powerlaw(){ return 1.0/(1.0-raw.next()); }

    public static double powerlaw(double alpha){
	alpha += 1.0;
	return Math.pow(1-raw.next(), 1.0/alpha); 
    }

    public static double powerlaw(double alpha, double min, double max){
	alpha += 1.0;
	min = Math.pow(min, alpha);
	max = (max==0.0)?0.0:Math.pow(max, alpha);
	
	return Math.pow((max-min)*raw.next()+min, 1.0/alpha); 
    }
    
    /**
     * Generates a real number in the interval [0,max)
     * @param max Right side of the interval [0,max)
     * @return A real number in the interval [0,max)
     */
    public static double uniform( double max ) {
	return max * raw.next();
    }
     
    /**
     * Generates a real number in the interval [min,max)
     * @param min Left side of the interval [min,max)
     * @param max Right side of the interval [min,max)
     * @return A real number in the interval [min,max)
     */
    public static double uniform(double min, double max){ 
	return min+uniform(max-min); 
    }
 
    // Integer generation
    public static int roulette(double[] density){
	double x = raw.next();
	int length = density.length;
	int i = 0;
	while (i < length && x >= density[i]) {
		x -= density[i];
		i++;
	}
	return i;
    }

    public static int uniform( int max ) {
	return (int)(max * raw.next());
    }
     
    public static int uniform(int min, int max){ 
	return min+uniform(max-min); 
    }
 
    // Boolean 
    public static boolean bool(){ return bool(0.5); }

    public static boolean bool(double p_false){ return raw.next()>=p_false; }
       
    // Shuffle
    protected static int[] indices(int n){
	int[] x = new int[2*n];
	for( int i=0; i<x.length; i++) x[i] = uniform(n);
	return x;
    }
    
    /**
     * Shuffles the given array 
     * @param set Array of integers to be shuffled
     */
    public static void shuffle(Object set) {
	int n = Array.getLength(set);
	int j, k;
	Object temp;
	int[] indices = indices(n);
	for (int i = 0; i<indices.length; i+=2) {
		j = indices[i];
		k = indices[i+1];
		temp = Array.get(set, j);
		Array.set(set, j, Array.get(set,k));
		Array.set(set, k, temp);
	}
    }
    
    // Permutation
    public static int[] permutation(int n) {
	int[] set = new int[n];
	for( int i=0; i<n; i++) set[i] = i;
	shuffle(set);
	shuffle(set);
	return set;
    }
    
    // Sample
    public static int[] sample(int n, int m) {
	int[] s = new int[m];
	int[] p = permutation(n);
	System.arraycopy(p, 0, s, 0, m);
	return s;
    }
}