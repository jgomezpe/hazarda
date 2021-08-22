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

import speco.jxon.JXON;
import kompari.real.RealOrder;
import kompari.real.H2LOrder;
import kompari.real.L2HOrder;
import speco.array.Array;
import speco.array.SortedArrayMultiSet;

/**
 * <p>Title: Ranking</p>
 *
 * <p>Description: A ranking selection method.</p>
 *
 */
public class Ranking implements Selection {
	/**
	 * Creates an empty ranking mechanism (used by Configurable)
	 */
	public Ranking(){}
	
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param q Quality associated to each candidate solution
	 * @param order Quality order (low to high, high to low, etc)
	 * @return Indices of the selected candidate solutions
	 */
	public int[] apply(int n, double[] q, RealOrder order) {
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

	/**
	 * Selects <i>n</i> elements according to their quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] natural(int n, double[] q) {
		return apply(n, q, new L2HOrder());
	}

	/**
	 * Selects <i>n</i> elements according to their 'reversed' quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] reversed(int n, double[] q) {
		return apply(n, q, new H2LOrder());
	}

	/**
	 * Configures the selection according to the JXON information
	 * @param jxon Configuration information
	 */
	@Override
	public void config(JXON jxon) {}	  
}