package hazarda.integer.array;

import hazarda.integer.Uniform;

public class Shuffle {
	/**
	 * Generates an array with all the integers in the interval [0,n) stored in a random fashion
	 * @param n Sup limit (the generated array has <i>n</i> elements (the integer numbers in the interval [0,n))
	 * @return An array with all the integers in the interval [0,n) stored in a random fashion
	 */
	public static int[] apply(int n) {
		int[] set = new int[n];
		for (int i = 0; i < n; i++) set[i] = i;
		apply(set);
		return set;
	}
	
	public static int[] indices(int n){
	    Uniform ig = new Uniform(n);
	    return ig.generate(2 * n);
	}
	
	/**
	 * Shuffles the given array of integers
	 * @param set Array of integers to be shuffled
	 */
	public static void apply(int[] set) {
		int j, k;
		int temp;
		int[] indices = indices(set.length);
		for (int i = 0; i<indices.length; i+=2) {
			j = indices[i];
			k = indices[i+1];
			temp = set[j];
			set[j] = set[k];
			set[k] = temp;
		}
	}
}
