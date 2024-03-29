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
package hazarda.integer;

import hazarda.Hazarda;

/**
 * <p>Generates integer numbers following an uniform probability distribution</p>
 *
 */
public class IntUniform implements IntRandom{
	/**
	 * Low Limit
	 */
	protected int min;

	/**
	 * Interval Length
	 */
	protected int length;
	
	/**
	 * Creates a uniform integer number generator in the interval [0,max)
	 * @param max Upper Limit
	 */
	public IntUniform(int max){ this( 0, max ); }
	
	/**
	 * Creates a uniform integer number generator in the interval [min,max)
	 * @param min Lower limit
	 * @param max Upper limit
	 */
	public IntUniform(int min, int max) {
		this.min = min;
		this.length = max - min;
	}
	
	/**
	 * Fixes the uniform integer number generator to the interval [0,max)
	 * @param max Sup limit
	 */
	public void set( int max ){ set( 0, max ); }

	/**
	 * Fixes the uniform integer number generator to the interval [min,max)
	 * @param min Low limit
	 * @param max Sup limit
	 */
	public void set( int min, int max ){
		this.min = min;
		this.length = max-min; 
	}
	
	/**
	 * Generates the integer number in the interval [min,max) associated to the real number x in [0,1)
	 * @return The integer number in the interval [min,max) associated to the real number x in [0,1)
	 */
	@Override
	public int next() {	return min + Hazarda.uniform(length);	}
}