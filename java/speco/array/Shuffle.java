package speco.array;

import hazarda.Hazarda;

public class Shuffle {
     /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void apply(Array<T> set) {
	Hazarda.shuffle(set.buffer);
    }
}
