package dictionary;


public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {
	
	private K key;
	private V value;
	private OrderedLinkedListEntry<K, V> next;
	
	public OrderedLinkedListEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public void addNext(OrderedLinkedListEntry<K, V> next) {
		this.next = next;
	}
	
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
