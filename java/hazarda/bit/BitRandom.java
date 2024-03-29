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
package hazarda.bit;

import hazarda.Hazarda;

/**
 * <p>Generates random boolean values.</p>
 *
 */
public class BitRandom{
	/**
	 * Probability of generating a <i>false</i> value
	 */	
	protected double falseProbability;
	
	/**
	 * Creates a boolean generator with the same probability of generating a <i>true</i> and <i>false</i> value
	 */
	public BitRandom(){ this(0.5); }
	
	/**
	 * Creates a boolean generator with the given probability of generating a <i>false</i> value (1.0-falseProbability) is
	 * the probability of generating a <i>true</i> value
	 * @param falseProbability Probability of generating a <i>false</i> value
	 */
	public BitRandom(double falseProbability ){ this.falseProbability = falseProbability; }
	
	/**
	 * Produces a boolean value according to the stored probability distribution
	 * @return A boolean value according to the stored probability distribution
	 */
	public boolean next(){ return Hazarda.bool(falseProbability); }

	/**
	 * Returns a set of random boolean values
	 * @param v Array where boolean values will be stored
	 * @param offset Initial position for storing the generated bits
	 * @param m The total number of random boolean values
	 */
	public void generate(boolean[] v, int offset,  int m){ for (int i = 0; i < m; i++) v[i+offset] = next(); }

	/**
	 * Returns a set of random boolean values
	 * @param m The total number of random boolean values
	 * @return A set of m random boolean values
	 */
	public boolean[] generate(int m) {
		boolean[] v = null;
		if (m > 0) {
			v = new boolean[m];
			generate( v, 0, m );
		}
		return v;
	}    
}