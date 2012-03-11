package dictionary;

import java.util.NoSuchElementException;

/**
 * The Dictionary interface provides a simplified interface for implementations
 * of abstract data types which map keys to values. Each key is associated with
 * at most one value.
 * 
 * Given a Dictionary and a key, the associated value can be looked up provided
 * they are non-null.
 * 
 * Keys are Comparable objects.
 * 
 * The iterator returns dictionary-entries in ascending order by key.
 * 
 * <K> is type for the comparable key element <V> is type for the associated
 * value element
 */
public interface Dictionary<K extends Comparable<? super K>, V> extends
        Iterable<DictionaryEntry<K, V>> {

    /**
     * 
     * @return the number of key-value associations stored in this dictionary
     */
    public int size();

    /**
     * 
     * @return true if and only if this dictionary is empty
     */
    public boolean isEmpty();

    /**
     * Returns the value associated with the key. There can be at most one value
     * associated with each key.
     * 
     * @param key
     *            The key to look for inside the dictionary
     * @return the value associated with the key
     * @throws NoSuchElementException
     *             if given key does not exist in the dictionary
     */
    public V get(K key) throws NoSuchElementException;

    /**
     * Creates a dictionary entry associating the given key and value. If the
     * dictionary previously contained an entry for this key, the old value is
     * replaced with the given value.
     * 
     * @param key
     *            The key to associate the value with
     * @param value
     *            The value to be associated with the key
     */
    public void put(K key, V value);

    /**
     * Removes the entry for the key from the dictionary if it is present.
     * 
     * @param key
     *            The key to remove from the dictionary
     * @throws NoSuchElementException
     *             if the key is not in the dictionary
     */
    public void remove(K key) throws NoSuchElementException;

    /**
     * Removes all entries from the dictionary
     */
    public void clear();

}
