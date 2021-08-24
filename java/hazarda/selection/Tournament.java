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

import hazarda.Hazarda;
import speco.jxon.JXON;

/**
 * <p>A tournament selection mechanism.</p>
 *
 */
public class Tournament implements Selection{
	/**
	 * Tournament size tag
	 */
	public static final String SIZE = "size";
    
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
		for( int i=0; i<m; i++ ) candidates[i] = Hazarda.uniform(n);
		return candidates;
	}
    
	/**
	 * Selects <i>n</i> elements according to their quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] natural(int n, double[] q) {
		int[] s = new int[n];
		for( int k=0; k<n; k++ ) {
			int[] c = uniform(q.length);
			s[k] = c[0];
			for( int i=1; i<c.length; i++ ) if(q[c[i]] > q[s[k]]) s[k] = c[i];
		}
		return s;
	}

	/**
	 * Selects <i>n</i> elements according to their 'reversed' quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] reversed(int n, double[] q) {
		int[] s = new int[n];
		for( int k=0; k<n; k++ ) {
			int[] c = uniform(q.length);
			s[k] = c[0];
			for( int i=1; i<c.length; i++ ) if(q[c[i]] < q[s[k]]) s[k] = c[i];
		}
		return s;
	}

	/**
	 * Configures the selection according to the JXON information
	 * @param jxon Configuration information
	 */
	@Override
	public void config(JXON jxon) { m=jxon.integer(SIZE, 4); }
}