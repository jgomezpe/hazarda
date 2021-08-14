/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import hazarda.Hazarda;

/**
 *
 * @author jgomez
 */
public class RandRealDemo{      
	public static void main( String[] args ){
	    System.out.println("*********Raw generator [0.0,1.0)**********");
	    double[] x = Hazarda.next(100);
	    for( int i=0; i<x.length; i++ ){
		System.out.println( x[i] );
	    }
	    
	    System.out.println("*********Uniform generator [-5.0,5.0)**********");
	    for( int i=0; i<100; i++ ){
		System.out.println( Hazarda.uniform(-5.0, 5.0) );
	    }
	    
	    System.out.println("*********Gauss generator N(0.0,1.0)**********");
	    for( int i=0; i<100; i++ ){
		System.out.println( Hazarda.gaussian() );
	    }

	    System.out.println("*********Power Law generator PL(2.0,1.0,0.0)**********");
	    for( int i=0; i<100; i++ ){
		System.out.println( Hazarda.powerlaw() );
	    }
	}    
}