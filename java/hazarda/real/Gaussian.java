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
 * <p>Generates real numbers following a Gaussian distribution.</p>
 *
 */
public class Gaussian implements RealRandom{
	/**
	 * Mean
	 */
	protected double mu;
	
	/**
	 * Deviation
	 */
	protected double sigma;
  
	/**
	 * Constructor: Creates a Standard Gaussian Number Generator G(0,1)
	 */
	public Gaussian(){ this(0.0,1.0); }

	/**
	 * Constructor: Creates a Gaussian Number Generator G(0,sigma)
	 * @param sigma Standard deviation of the Gaussian generator
	 */
	public Gaussian( double sigma ){  this(0.0,sigma);  }
  
	/**
	 * Constructor: Creates a Gaussian Number Generator G(mu,sigma)
	 * @param mu Mean
	 * @param sigma Standard deviation
	 */
	public Gaussian( double mu, double sigma ){  
		this.mu = mu;
		this.sigma = sigma;  
	}

	/**
	 * Generates a random number following the Gaussian distribution G(mu,sigma)
	 * @return A random number following the Gaussian distribution G(mu,sigma) 
	 */
	@Override
	public double next() { return Hazarda.gaussian(mu, sigma); }
}