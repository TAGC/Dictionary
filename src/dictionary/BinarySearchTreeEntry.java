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
	
	/*
	 * @return key associated with this entry
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
	 * @return the parent entry of this node
	 */
	public BinarySearchTreeEntry<K, V> getParent() {
		return parent;
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
	 * @param parent
	 *        	the entry to make the parent of this entry
	 */
	public void setParent(BinarySearchTreeEntry<K, V> parent) {
		this.parent = parent;
	}
	
	/*
	 * @param node
	 *        	the node to make the root node of this node's left subtree
	 */
	public void addLeft(BinarySearchTreeEntry<K, V> node) {
		left = node;
		node.setParent(this);
	}
	
	/*
	 * @param node
	 *        	the node to make the root node of this node's right subtree
	 */
	public void addRight(BinarySearchTreeEntry<K, V> node) {
		right = node;
		node.setParent(this);
	}
	
	/*
	 * @return the root node of this node's left subtree
	 */
	public BinarySearchTreeEntry<K, V> getLeft() {
		return left;
	}
	
	/*
	 * @return the root node of this node's right subtree
	 */
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
