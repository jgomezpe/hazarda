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
package hazarda.real;

import hazarda.Hazarda;

/**
 * <p>Generates random real numbers with uniform distribution.</p>
 *
 */
public class RealUniform implements RealRandom{
	/**
	 * Lower limit of interval (not included)
	 */
	protected double min;

	/**
    * Upper limit of the interval
    */
	protected double length;

	/**
	 * Constructor: Creates a uniform random number generator that generates numbers in the interval [0, max)
	 * @param max Upper limit of the interval (not included)
	 */
	public RealUniform(double max){ this(0.0, max); }

	/**
	 * Constructor: Creates a uniform random number generator that generates numbers in the interval [minVal, maxVal)
	 * @param max Upper limit of the interval (not included)
	 * @param min Lower limit of the interval
	 */
	public RealUniform(double min, double max) { 
		this.min = min;
		this.length = max-length;
	}

	/**
	 * Fixes the uniform integer number generator to the interval [0,max)
	 * @param max Upper limit of the interval (not included)
	 */
	public void set( double max ){ set( 0.0, max ); }

	/**
	 * Fixes the uniform integer number generator to the interval [min,max)
 	 * @param max Upper limit of the interval (not included)
	 * @param min Lower limit of the interval
    */
	public void set(double min, double max ){
		this.min = min;
		this.length = max-min; 
	}
	
	/**
	 * Generates the integer number in the interval [min,max) associated to the real number x in [0,1)
	 * @return The integer number in the interval [min,max) associated to the real number x in [0,1)
	 */
	@Override
	public double next() { return min + Hazarda.uniform(length); }
}