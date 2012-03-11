package dictionary;

public interface DictionaryEntry<K, V> {

    /**
     * @return the key in this entry
     */
    public K getKey();

    /**
     * @return the value in this entry
     */
    public V getValue();
}