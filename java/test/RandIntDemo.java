package test;

import hazarda.Hazarda;

public class RandIntDemo {
	public static void main( String[] args ){
		// Random g = uniform();
		int n = 10;
		// Generating an array of ten random values
		int[] x = Hazarda.uniform(0,100,n); //Uniform generation of  n integer numbers in the interval [0,100)
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}
		System.out.println("****************");
		x = Hazarda.roulette(new double[]{0.4,0.3,0.2,0.1},n); //Roulette generation of  n integer numbers
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}		
	}
}