package test;

import hazarda.Hazarda;

public class RandIntDemo {
	public static void main( String[] args ){
		// Random g = uniform();
		int n = 10;
		// Generating an array of ten random values
		for( int i=0; i<n; i++ ){
			System.out.println( Hazarda.uniform(0,100) );
		}
		System.out.println("****************");
		double[] p = new double[]{0.4,0.3,0.2,0.1}; //Roulette generation of  n integer numbers
		for( int i=0; i<n; i++ ){
			System.out.println( Hazarda.roulette(p) );
		}		
	}
}