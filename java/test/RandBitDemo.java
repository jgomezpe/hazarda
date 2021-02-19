package test;

import hazarda.Hazarda;

public class RandBitDemo {
    public static void main( String[] args ){
	// false is generated with probability 0.7
	// true is generated with probability 0.3
	int n = 100;
	// Generating an array of ten random values
	boolean[] x = Hazarda.bool(0.7,n);
	int count = 0;
	for( int i=0; i<n; i++ ) {
	    System.out.println( x[i] );
	    if(x[i]) count++;
	}
	System.out.println(count);
	System.out.println("****************");
	count = 0;
	// Generating ten random values (equal probability)
	for( int i=0; i<n; i++ ){
	    boolean b = Hazarda.bool();
	    System.out.println(b);
	    if(b) count++;
	}		
	System.out.println(count);
    }
}