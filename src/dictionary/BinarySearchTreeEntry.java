package dictionary;

import dictionary.DictionaryEntry;

// Implementation class representing nodes of the binary search tree.
public class BinarySearchTreeEntry<K, V> implements DictionaryEntry<K, V> {
	
	private K key;
	private V value;
	private BinarySearchTreeEntry<K, V> left;
	private BinarySearchTreeEntry<K, V> right;
	private BinarySearchTreeEntry<K, V> parent;
	
	public BinarySearchTreeEntry(K key, V value) {
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
	
	public BinarySearchTreeEntry<K, V> getParent() {
		return parent;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public void setParent(BinarySearchTreeEntry<K, V> parent) {
		this.parent = parent;
	}
	
	public void addLeft(BinarySearchTreeEntry<K, V> node) {
		left = node;
		node.setParent(this);
	}
	
	public void addRight(BinarySearchTreeEntry<K, V> node) {
		right = node;
		node.setParent(this);
	}
	
	public BinarySearchTreeEntry<K, V> getLeft() {
		return left;
	}
	
	public BinarySearchTreeEntry<K, V> getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		String output;
		output = String.format("(%s, %s)", key, value);
		return output;
	}
}
