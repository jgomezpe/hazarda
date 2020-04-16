/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.random;

import nsgl.real.random.PowerLaw;
import nsgl.real.random.Gaussian;
import nsgl.real.random.Random;
import nsgl.real.random.Symmetric;
import nsgl.real.random.Uniform;

/**
 *
 * @author jgomez
 */
public class RandRealDemo{
	public static Random uniform(){
		System.out.println( "********************Uniform********************" );
		return  new Uniform(0.0,2.0);
	}  
  
	public static Random gaussian(){
		System.out.println( "********************Gaussian********************" );
		return new Gaussian();
	}  
  
	public static Random power_law(){
		System.out.println( "********************Power Law********************" );
		return new PowerLaw();
	}
    
	public static Random symmetric_power_law(){
	    System.out.println( "**********************Symmetric Power Law********************" );
	    Symmetric g = new Symmetric(new PowerLaw(2,1,0));
	    return g;
	}
	
	public static void test( Random g ){
		int n = 10;
		// Generating an array of ten random values
		double[] x = g.generate(n);
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}
		System.out.println("****************");
		// Generating ten random values
		for( int i=0; i<n; i++ ){
			System.out.println(g.next());
		}
	}
      
	public static void main( String[] args ){
		test( uniform() );
		test( gaussian() );
		test( power_law() );
		test( symmetric_power_law() );
	}    
}