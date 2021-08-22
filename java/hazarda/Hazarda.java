/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package hazarda;

import java.lang.reflect.Array;

import hazarda.raw.JavaGenerator;
import hazarda.raw.RawGenerator;
import hazarda.raw.rngpack.RanMT;
import hazarda.raw.rngpack.Ranecu;
import hazarda.raw.rngpack.Ranlux;
import hazarda.raw.rngpack.Ranmar;

/**
 * <p>Title: Hazarda</p>
 *
 * <p>Description: Random functions.</p>
 *
 */
public class Hazarda{
	/**
	 * RawGenerator, [0,1) generator, used by Hazarda 
	 */
	protected static RawGenerator raw = new JavaGenerator(); // Raw Generator [0,1)
    
	/**
	 * Sets the raw generator used by Hazarda
	 * @param raw RawGenerator used by Hazarda (generates double numbers in the [0,1) interval)
	 */
	public static void raw(RawGenerator raw) { Hazarda.raw = raw; }
    
    /**
     * Gets the raw generator used by Hazarda
     * @return RawGenerator used by Hazarda
     */
	public static RawGenerator raw() { return raw; }
    
	/**
	 * Sets a JavaGenerator, wrap of the Java Standard Generator (Math.random()), 
	 * as the RawGenerator used by Hazarda
	 */
	public static void java() { raw(new JavaGenerator()); }
    
	/**
	 * Sets a Ranecu as the RawGenerator used by Hazarda
	 * @param seed  Specific seed for the Ranecu
	 */
	public static void ranecu(long seed) { raw( new Ranecu(seed) ); }
    
	/**
	 * Sets a RanLux as the RawGenerator used by Hazarda
	 * @param seed  Specific seed for the RanLux
	 */
	public static void ranlux(long seed) { raw( new Ranlux(seed) ); }
    
	/**
	 * Sets a RanMar as the RawGenerator used by Hazarda
	 * @param seed  Specific seed for the RanMar
	 */
	public static void ranmar(long seed) { raw( new Ranmar(seed) ); }
    
	/**
	 * Sets a MersenneTwister as the RawGenerator used by Hazarda
	 * @param seed  Specific seed for the MersenneTwister
	 */
	public static void mersennetwister(long seed) { raw( new RanMT(seed) ); }
    
	/**
	 * Gets a random double in the [0,1) interval (uses its internal RawGenerator)
	 * @return Random double in the [0,1) interval
	 */
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
	 * Generates a random number following the Gaussian distribution <i>G(0,1)</i>
	 * @return A random number following the Gaussian distribution <i>G(0,1)</i> 
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
	 * Generates a random number following the Gaussian distribution <i>G(0,sigma)</i>
	 * @param sigma Standard deviation
	 * @return A random number following the Gaussian distribution <i>G(0,sigma)</i> 
	 */
	public static double gaussian(double sigma){ return gaussian()*sigma; }

	/**
	 * Generates a random number following the Gaussian distribution G(mu,sigma)
	 * @param mu Mean
	 * @param sigma Standard deviation
	 * @return A random number following the Gaussian distribution <i>G(mu,sigma)</i> 
	 */
	public static double gaussian(double mu, double sigma){ return mu+gaussian(sigma); }
   
    /**
     * Generates a double following a Power Law distribution <i>PL(-2)</i>
     * @return a random number following the Power Law distribution <i>PL(-2)</i>
     */
	public static double powerlaw(){ return 1.0/(1.0-raw.next()); }

    /**
     * Generates a double following a Power Law distribution <i>PL(alpha)</i>
     * @param alpha Exponential factor of the power law distribution
     * @return a random number following the Power Law distribution <i>GPL(alpha)</i>
     */
	public static double powerlaw(double alpha){
		alpha += 1.0;
		return Math.pow(1-raw.next(), 1.0/alpha); 
	}

    /**
     * Generates a double following a Generalized Power Law distribution GPL(alpha,min,max)
     * @param alpha Exponential factor of the power law distribution
     * @param max Scale factor of the power law (max-min)
     * @param min Location of the power law distribution (kind of minimum value generated by the power law distribution)
     * @return a random number following the Power Law distribution <i>GPL(alpha, min, max)</i>
     */
	public static double powerlaw(double alpha, double min, double max){
		alpha += 1.0;
		min = Math.pow(min, alpha);
		max = (max==0.0)?0.0:Math.pow(max, alpha);
		return Math.pow((max-min)*raw.next()+min, 1.0/alpha); 
	}

	/**
	 * Generates a real number in the interval <i>[0,max)</i>
	 * @param max Right side of the interval
	 * @return A real number in the interval <i>[0,max)</i>
	 */
	public static double uniform( double max ) { return max * raw.next(); }
 
	/**
	 * Generates a real number in the interval <i>[min,max)</i>
	 * @param min Left side of the interval
	 * @param max Right side of the interval
	 * @return A real number in the interval <i>[min,max)</i>
	 */
	public static double uniform(double min, double max){  return min+uniform(max-min); }
 
    // Integer generation
	/**
	 * Gets an integer (picked following its density function) in the interval <i>0,..n-1</i> with <i>n = density.length</i>
	 * @param density A probability density (<i>density[i]&gt;=0</i> and summation of <i>density[i]</i> for all <i>i=0,..,n-1 = 1.0</i>) 
	 * @return A random integer (picked following its density function) in the interval <i>0,..n-1</i> 
	 */
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

	/**
	 * Gets a random integer in the interval <i>[0,max)</i>
	 * @param max Upper limit of the interval
	 * @return Random integer in the interval <i>[0,max)</i>
	 */
	public static int uniform( int max ) { return (int)(max * raw.next()); }
     
	/**
	 * Gets a random integer in the interval <i>[min,max)</i>
	 * @param min Lower limit of the interval
	 * @param max Upper limit of the interval
	 * @return Random integer in the interval <i>[min,max)</i>
	 */
	public static int uniform(int min, int max){ return min+uniform(max-min); }
 
    // Boolean 
	/**
	 * Gets a random boolean
	 * @return Random boolean
	 */
	public static boolean bool(){ return bool(0.5); }

	/**
	 * Gets a random boolean: <i>false</i> with probability <i>p_false</i>, <i>true</i> with probability <i>1-p_false</i> 
	 * @param p_false Probability of generating a <i>false</i> value
	 * @return Random boolean
	 */
	public static boolean bool(double p_false){ return raw.next()>=p_false; }
       
    // Shuffle
	protected static int[] indices(int n){
		int[] x = new int[2*n];
		for( int i=0; i<x.length; i++) x[i] = uniform(n);
		return x;
	}
    
    /**
     * Shuffles the given array 
     * @param set Objects to be shuffled
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
	/**
	 * Permutes the set of natural numbers <i>0,1,..,n-1</i>
	 * @param n Upper limit of the set of natural numbers
	 * @return Array with a permutation of the natural numbers <i>0,1,..,n-1</i>
	 */
	public static int[] permutation(int n) {
		int[] set = new int[n];
		for( int i=0; i<n; i++) set[i] = i;
		shuffle(set);
		shuffle(set);
		return set;
	}
    
    // Sample
	/**
	 * Gets a sampled set (without repetitions) of size <i>m</i> 
	 * from the set of natural numbers <i>0,1,..,n-1</i> 
	 * @param n Upper limit of the set of natural numbers
	 * @param m Size of the sampled set
	 * @return sampled set (without repetitions) of size <i>m</i> 
	 * from the set of natural numbers <i>0,1,..,n-1</i>
	 */
	public static int[] sample(int n, int m) {
		if( m>n ) m = n;
		int[] s = new int[m];
		int[] p = permutation(n);
		System.arraycopy(p, 0, s, 0, m);
		return s;
	}
}