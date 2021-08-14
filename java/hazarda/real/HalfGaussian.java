package hazarda.real;

import hazarda.Hazarda;

public class HalfGaussian extends Gaussian{
	/**
	 * Constructor: Creates a Half Gaussian Number Generator |G(0,1)|
	 */
	public HalfGaussian(){  this(1.0);  }

	/**
	 * Constructor: Creates a Half Gaussian Number Generator |G(0,sigma)|
	 * @param sigma standard deviation
	 */
	public HalfGaussian( double sigma ){  this(0.0,sigma);  }

	/**
	 * Constructor: Creates a Half Gaussian Number Generator |G(0,sigma)|
	 * @param mu Mean of the Gaussian distribution
	 * @param sigma Standard deviation of the Gaussian distribution 
	 */
	public HalfGaussian( double mu, double sigma ){  super(mu,sigma);  }

	/**
	 * Generates a number following a Half Gaussian distribution
	 * @return A number following a Half Gaussian distribution
	 */
	@Override
	public double next() { return mu+sigma*Math.abs(Hazarda.gaussian()); }
}