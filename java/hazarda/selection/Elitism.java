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
package hazarda.selection;

import hazarda.integer.Roulette;
import speco.jxon.JXON;
import kompari.real.RealH2LOrder;
import kompari.real.RealL2HOrder;
import kompari.real.RealOrder;
import speco.array.Array;
import speco.array.SortedArrayMultiSet;

/**
 * <p>An elitist selection mechanism.</p>
 *
 */
public class Elitism implements Selection{
	/**
	 * Elite percentag tag
	 */
	public static final String ELITE = "elite";
    
	/**
	 * Cull percentage tag
	 */
	public static final String CULL = "cull";
    
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
	 * Creates an empty elitist mechanism (used by Configurable)
	 */
	public Elitism() {}
    
	/**
	 * Constructor: Create an Elitist selection strategy.
	 * @param _elite_percentage Percentage of individuals to be included in the selection
	 * @param _cull_percentage Percentage of individuals to be excluded in the selection
	 */
	public Elitism( double _elite_percentage, double _cull_percentage ){	
		elite_percentage = _elite_percentage;
		cull_percentage = _cull_percentage;
	}
	  
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param q Quality associated to each candidate solution
	 * @param order Quality order (low to high, high to low, etc)
	 * @return Indices of the selected candidate solutions
	 */
	public int[] apply( int n, double[] q, RealOrder order ){
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

	/**
	 * Selects <i>n</i> elements according to their quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] natural(int n, double[] q) { return apply(n, q, new RealH2LOrder()); }

	/**
	 * Selects <i>n</i> elements according to their 'reversed' quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] reversed(int n, double[] q) { return apply(n, q, new RealL2HOrder()); }
 
	/**
	 * Configures the selection according to the JXON information
	 * @param jxon Configuration information
	 */
	@Override
	public void config(JXON jxon) {
		cull_percentage=jxon.real(CULL,0.0); 
		elite_percentage=jxon.real(ELITE, 0.1);
	}
}