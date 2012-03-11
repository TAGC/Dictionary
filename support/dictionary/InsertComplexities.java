package dictionary;

import java.util.Random;

/**
 * This is a utility class that can keep track of the number of comparisons
 * performed by the keys that it generates.
 */
public class InsertComplexities {

    private final Random random;

    private int keyComparisonCount;

    /**
     * Create a new instance of the tracker, with the given Random data source.
     * 
     * @param random
     *            A source of random data to be used by the class.
     */
    public InsertComplexities(Random random) {
        this.random = random;
    }

    /**
     * Analyse the number of comparisons used when inserting into a Dictionary
     * of various sizes.
     * 
     * @param d
     *            An instance of the Dictionary to analyse.
     * @param maxSize
     *            the maximum size of a Dictionary to test up to.
     * @param repititions
     *            The number of times to repeat the inserts to calculate an
     *            average.
     * @return an array with length maxSize, where the element at index i in the
     *         array is the average number of comparisons needed to insert a
     *         single unique piece of randomly generated data into a dictionary
     *         of size i. The averaging happens by performing these operations
     *         reptitions number of times.
     * 
     *         For example, if the returned array was { 0, 1, 3, 6 }, then
     *         inserting into an empty dictionary takes 0 comparisons on
     *         average. Inserting a key-value pair into a dictionary of size 1
     *         takes 2 comparisons, and inserting a key-value pair into a
     *         dictionary of sizes 2 and 3 takes 3 and 6 comparisons
     *         respectively, on average.
     */
    public int[] getInsertComplexities(Dictionary<InstrumentedKey, Integer> d,
            int maxSize, int repititions) {
        int[] totals = new int[maxSize];

        for (int i = 0; i < repititions; i++) {
            int[] counts = doInserts(d, maxSize);

            for (int j = 0; j < maxSize; j++) {
                totals[j] += counts[j];
            }
        }

        for (int j = 0; j < maxSize; j++) {
            totals[j] /= repititions;
        }

        return totals;
    }

    private int[] doInserts(Dictionary<InstrumentedKey, Integer> d, int limit) {
        int[] counts = new int[limit];
        d.clear();
        for (int i = 0; i < limit; i++) {
            keyComparisonCount = 0;
            d.put(new InstrumentedKey(random.nextInt() + "-" + i), i);
            counts[i] = keyComparisonCount;
        }
        return counts;
    }

    public class InstrumentedKey implements Comparable<InstrumentedKey> {

        private final String key;

        public InstrumentedKey(String key) {
            this.key = key;
        }

        @Override
        public int compareTo(InstrumentedKey o) {
            keyComparisonCount++;
            return this.key.compareTo(o.key);
        }

    }

}
