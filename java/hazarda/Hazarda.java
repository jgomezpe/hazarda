package hazarda;

import nsgl.array.Array;
import nsgl.random.raw.JavaGenerator;
import nsgl.random.raw.RawGenerator;
import nsgl.random.raw.rngpack.RanMT;
import nsgl.random.raw.rngpack.Ranlux;
import nsgl.random.raw.rngpack.Ranmar;

public class Hazarda{
    
    // Raw Generator [0,1)
    
    public static void raw(RawGenerator raw) { RawGenerator.set(raw); }
    
    public static RawGenerator raw() { return RawGenerator.get(); }
    
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
    
    public static double next() { return raw().next(); }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void next(double[] v, int offset,  int m){ raw().raw(v, offset, m); }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] next(int m) { return raw().raw(m); }
    
    // Double generation
    
    public static nsgl.real.random.Gaussian gauss_rand(double mu, double sigma){
	return new nsgl.real.random.Gaussian(mu,sigma);
    }

    public static double gauss(){ return gauss_rand(0.0, 1.0).next(); }
    public static double gauss(double sigma){ return gauss_rand(0.0, sigma).next(); }
    public static double gauss(double mu, double sigma){ return gauss_rand(mu, sigma).next(); }

    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void gauss(double[] v, int offset,  int m){
	gauss_rand(0.0,1.0).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void gauss(double sigma, double[] v, int offset,  int m){
	gauss_rand(0.0,sigma).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void gauss(double mu, double sigma, double[] v, int offset,  int m){
	gauss_rand(mu,sigma).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] gauss(int m) {
	return gauss_rand(0.0, 1.0).generate(m); 
    }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] gauss(double sigma, int m) {
	return gauss_rand(0.0, sigma).generate(m); 
    }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] gauss(double mu, double sigma, int m) {
	return gauss_rand(mu, sigma).generate(m); 
    }
    
    
    public static nsgl.real.random.PowerLaw powerlaw_rand(double alpha, double b, double c){
	return new nsgl.real.random.PowerLaw(alpha,b,c);
    }

    public static double powerlaw(){ return powerlaw_rand(2.0, 1.0, 0.0).next(); }
    public static double powerlaw(double alpha){ return powerlaw_rand(alpha, 1.0, 0.0).next(); }
    public static double powerlaw(double alpha, double b, double c){ return powerlaw_rand(alpha, b, c).next(); }

    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void powerlaw(double[] v, int offset,  int m){
	powerlaw_rand(2.0,1.0,0.0).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void powerlaw(double alpha, double[] v, int offset,  int m){
	powerlaw_rand(alpha,1.0,0.0).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void powerlaw(double alpha, double b, double c, double[] v, int offset,  int m){
	powerlaw_rand(alpha,b,c).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] powerlaw(int m) {
	return powerlaw_rand(2.0, 1.0, 0.0).generate(m); 
    }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] powerlaw(double alpha, int m) {
	return powerlaw_rand(alpha,1.0,0.0).generate(m); 
    }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] powerlaw(double alpha, double b, double c, int m) {
	return powerlaw_rand(alpha, b, c).generate(m); 
    }
    
    public static nsgl.real.random.Uniform rand(double min, double max ){
	return new nsgl.real.random.Uniform(min, max);
    }

    public static double uniform(double min, double max){ return rand(min,max).next(); }

    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void uniform(double max, double[] v, int offset,  int m){
	uniform(0.0,max,v,offset,m);
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void uniform(double min, double max, double[] v, int offset,  int m){
	rand(min, max).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static double[] uniform(double min, double max, int m) {
	return rand(min,max).generate(m); 
    }
 
    // Integer generation
    
    public static nsgl.integer.random.Roulette rand(double[] density){
	return new nsgl.integer.random.Roulette(density);
    }

    public static int roulette(double[] density){ return rand(density).next(); }

    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void roulette(double[] density, int[] v, int offset,  int m){
	rand(density).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static int[] roulette(double[] density, int m) {
	return rand(density).generate(m); 
    }
 
    public static nsgl.integer.random.Uniform rand(int min, int max ){
	return new nsgl.integer.random.Uniform(min, max);
    }

    public static int uniform(int min, int max){ return rand(min,max).next(); }

    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void uniform(int max, int[] v, int offset,  int m){
	uniform(0,max,v,offset,m);
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void uniform(int min, int max, int[] v, int offset,  int m){
	rand(min, max).generate(v, offset, m); 
    }
	
    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static int[] uniform(int min, int max, int m) {
	return rand(min,max).generate(m); 
    }
 
    // Boolean 
    
    public static nsgl.bit.random.Random rand(double p_false){
	return new nsgl.bit.random.Random(p_false);
    }

    public static boolean bool(){ return bool(0.5); }

    public static boolean bool(double p_false){ return rand(p_false).next(); }
    
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void bool(boolean[] v,  int offset,  int m){
	bool(0.5,v,offset,m);
    }
	
    /**
     * Returns a set of random boolean values
     * @param v Array where boolean values will be stored
     * @param offset Initial position for storing the generated bits
     * @param m The total number of random boolean values
     */
    public static void bool(double p_false, boolean[] v, int offset,  int m){
	rand(p_false).generate(v, offset, m); 
    }

    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static boolean[] bool(int m) { return bool(0.5,m); }
	
    /**
     * Returns a set of random boolean values
     * @param m The total number of random boolean values
     * @return A set of m random boolean values
     */
    public static boolean[] bool(double p_false, int m) {
	return rand(p_false).generate(m); 
    }
   
    // Shuffle
    /**
     * Shuffles the given array of integers
     * @param set Array of integers to be shuffled
     */
    public static void shuffle(int[] set) {
	nsgl.integer.array.Shuffle.apply(set);
    }
    
    public static int[] shuffle(int n) {
	return nsgl.integer.array.Shuffle.apply(n);
    }

    /**
     * Shuffles the given array of integers
     * @param set Array of integers to be shuffled
     */
    public static void shuffle(double[] set) {
	nsgl.real.array.Shuffle.apply(set);
    } 

    /**
     * Shuffles the given array of longs
     * @param set Array of longs to be shuffled
     */
    public static void apply(long[] set) {
	int j, k;
	long temp;
	int[] indices = nsgl.integer.array.Shuffle.indices(set.length);
	for (int i = 0; i<indices.length; i+=2) {
	    j = indices[i];
	    k = indices[i+1];
	    temp = set[j];
	    set[j] = set[k];
	    set[k] = temp;
	}
    }

    /**
     * Shuffles the given array of characters
     * @param set Array of characters to be shuffled
     */
    public static void apply(char[] set) {
	int j, k;
	char temp;
	int[] indices = nsgl.integer.array.Shuffle.indices(set.length);
	for (int i = 0; i<indices.length; i+=2) {
	    j = indices[i];
	    k = indices[i+1];
	    temp = set[j];
	    set[j] = set[k];
	    set[k] = temp;
	}
    }

    /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void shuffle(T[] set) {
	nsgl.array.Shuffle.apply(set);
    }

    /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void shuffle(Array<T> set) {
	nsgl.array.Shuffle.apply(set);
    }
}