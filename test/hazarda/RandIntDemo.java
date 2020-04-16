package hazarda;

import nsgl.integer.random.Random;
import nsgl.integer.random.Roulette;
import nsgl.integer.random.Uniform;

public class RandIntDemo {
	public static Random roulette(){
		System.out.println("Roulette");
		// 0  is generated with probability 0.4
		// 1 is generated with probability 0.3
		// 2 is generated with probability 0.2
		// 3 is generated with probability 0.1
		return new Roulette(new double[]{0.4,0.3,0.2,0.1});
	} 
	
	public static Random uniform(){
		System.out.println("Uniform");
		// Integer numbers between 0 and 99 are generated with the same probability
		return new Uniform(100);
	}
	
	public static void main( String[] args ){
		Random g = roulette();
		// Random g = uniform();
		int n = 10;
		// Generating an array of ten random values
		int[] x = g.generate(n);
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}
		System.out.println("****************");
		// Generating ten random values
		for( int i=0; i<n; i++ ){
			System.out.println(g.next());
		}		
	}
}