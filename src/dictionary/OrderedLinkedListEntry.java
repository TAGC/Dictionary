package dictionary;


public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {
	
	private K key;
	private V value;
	private OrderedLinkedListEntry<K, V> next;
	
	public OrderedLinkedListEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/*
	 * @return the key associated with this entry
	 */
	@Override
	public K getKey() {
		return key;
	}
	
	/*
	 * @return value associated with this entry
	 */
	@Override
	public V getValue() {
		return value;
	}
	
	/*
	 * @param value
	 *        	the new value to set the entry to
	 *        
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	/*
	 * @param next
	 *        	the entry that should follow from this node
	 */
	public void addNext(OrderedLinkedListEntry<K, V> next) {
		this.next = next;
	}
	
	/*
	 * @return the entry that follows from this node
	 */
	public OrderedLinkedListEntry<K, V> getNext() {
		return next;
	}
	
	@Override
	public String toString() {
		String output;
		output = String.format("(%s, %s)", key, value);
		return output;
	}
}
