package nsgl.array;

public class Shuffle {
    /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void apply(T[] set) {
	int j, k;
	T temp;
	int[] indices = nsgl.integer.array.Shuffle.indices(set.length);
	for (int i = 0; i<indices.length; i+=2) {
	    j = indices[i];
	    k = indices[i+1];
	    temp = set[j];
	    set[j] = set[k];
	    set[k] = temp;
	}
    }

    /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void apply(Array<T> set) {
	int j, k;
	T temp;
	int[] indices = nsgl.integer.array.Shuffle.indices(set.size());
	for (int i = 0; i<indices.length; i+=2) {
	    j = indices[i];
	    k = indices[i+1];
	    temp = set.get(j);
	    set.set(j,set.get(k));
	    set.set(k, temp);
	}
    }
}
