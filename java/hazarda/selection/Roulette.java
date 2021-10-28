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
import speco.json.JSON;
import speco.real.RealUtil;

/**
 * <p>Random partition of a set.</p>
 *
 */
public class Roulette implements Selection{
	protected static final int ZERO = 0;
	protected static final int NEGATIVE = -1;
	protected static final int POSITIVE = 1;
	protected static final int UNDEFINED = 2;
	
	protected int type( double[] q ) {
		int p = 0;
		int n = 0;
		for(int i=0; i<q.length; i++)
			if(q[i] < 0.0) n++;
			else if(q[i] > 0.0) p++;
			else {
				n++;
				p++;
			}
		if(n==q.length) 
			if(n==p) return ZERO;
			else return NEGATIVE; 
		else 
			if(p==q.length) return POSITIVE;
			else return UNDEFINED;
	}
    
	protected double[] natural_density(double[] q) {
		double[] p = new double[q.length];
		int type = type(q);
		switch(type) {
			case UNDEFINED:
			case NEGATIVE: 
				double min = 0;
				double max = Double.MIN_VALUE;
				for(int i=0; i<q.length; i++) {
					if(q[i] < min) min = q[i];
					if(q[i] > max && q[i] < 0) max = q[i];
				}
				for(int i=0; i<q.length; i++)
					p[i] = q[i] -(max+min);
			break;
			case ZERO:
				for(int i=0; i<q.length; i++)
					p[i] = 1.0;
			break;
			case POSITIVE:
				for(int i=0; i<q.length; i++)
					p[i] = q[i];
		}
		RealUtil.normalize(p);
		return p;
	}

	protected double[] reversed_density(double[] q) {
		double[] p = new double[q.length];
		int type = type(q);
		switch(type) {
			case UNDEFINED:
			case POSITIVE: 
				double min = Double.MAX_VALUE;
				double max = 0;
				for(int i=0; i<q.length; i++) {
					if(q[i] < min && q[i] > 0) min = q[i];
					if(q[i] > max) max = q[i];
				}
				for(int i=0; i<q.length; i++)
					p[i] = (max+min)-q[i];
				break;
			case ZERO:
				for(int i=0; i<q.length; i++)
					p[i] = 1.0;
			break;
			case NEGATIVE:
				for(int i=0; i<q.length; i++)
					p[i] = -q[i];
		}
		RealUtil.normalize(p);
		return p;
	}

	/**
	 * Selects <i>n</i> elements according to their quality measure
	 * @param n Number of elements to select
	 * @param q Real quality measure
	 * @return Selected elements
	 */
	@Override
	public int[] natural(int n, double[] q) {
		q = natural_density(q);
		int[] s = new int[n];
		for( int i=0; i<n; i++) s[i] = Hazarda.roulette(q);
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
		q = reversed_density(q);
		int[] s = new int[n];
		for( int i=0; i<n; i++) s[i] = Hazarda.roulette(q);
		return s;
	}

	/**
	 * Configures the selection according to the JSON information
	 * @param json Configuration information
	 */
	@Override
	public void config(JSON json) {}
}