package nsgl.real.array;

public class Shuffle {
	/**
	 * Shuffles the given array of integers
	 * @param set Array of integers to be shuffled
	 */
	public static void apply(double[] set) {
		int j, k;
		double temp;
		int[] indices = nsgl.integer.array.Shuffle.indices(set.length);
		for (int i = 0; i<indices.length; i+=2) {
			j = indices[i];
			k = indices[i+1];
			temp = set[j];
			set[j] = set[k];
			set[k] = temp;
		}
	}

}
