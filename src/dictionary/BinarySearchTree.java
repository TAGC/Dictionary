package dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/*
 * Binary search tree based implementation of the Dictionary
 * interface. The nodes of the tree are ordered by an associated key-attribute
 * key of type K, such that every node's left subtree contains only nodes 
 * whose key-attributes are less than key, and every node's right subtree
 * contains only nodes whose key-attributes are greater than key. A
 * linear order is defined on keys through the Comparable interface.
 * Duplicate keys are not permitted.
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {
	
	private BinarySearchTreeEntry<K, V> root;
	private int dict_modifications = 0;

	@Override
	public Iterator<DictionaryEntry<K, V>> iterator() {
		LinkedList<DictionaryEntry<K, V>> entry_list;
		Iterator<DictionaryEntry<K, V>> iterator;
		
		if (root == null) {
			entry_list = new LinkedList<DictionaryEntry<K, V>>();
		} else {
			entry_list = traverse(root, new LinkedList<DictionaryEntry<K, V>>());
		}
		
		iterator = new BSTIterator<K, V>(this, entry_list);
		return iterator;
	}
	
	/*
	 * Performs in-order traversal of the binary tree
	 */
	private LinkedList<DictionaryEntry<K, V>> traverse(
	        BinarySearchTreeEntry<K, V> entry,
			LinkedList<DictionaryEntry<K, V>> entry_list) {
		
		if (entry.getLeft() != null) {
			entry_list = traverse(entry.getLeft(), entry_list);
		}
		
		entry_list.add(entry);
		
		if (entry.getRight() != null) {
			entry_list = traverse(entry.getRight(), entry_list);
		}
		
		return entry_list;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return entryCounter(root);
		}
	}
	
	private int entryCounter(BinarySearchTreeEntry<K, V> current) {
		if (current.getLeft() == null && current.getRight() == null) {
			return 1;
		} else if (current.getLeft() != null && current.getRight() == null) {
			return 1 + entryCounter(current.getLeft());
		} else if (current.getLeft() == null && current.getRight() != null) {
			return 1 + entryCounter(current.getRight());
		} else {
			return 1 + entryCounter(current.getLeft()) 
				     + entryCounter(current.getRight());
		}
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public V get(K key) throws NoSuchElementException {
		V value;
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			value = find(key, root);
			if (value == null) {
				throw new NoSuchElementException();
			}
		}
		
		return value;
	}
	
	/*
	 * Returns the value of the entry in the tree that matches with the 
	 * given key if it exists. Otherwise returns null.
	 */
	private V find(K key, BinarySearchTreeEntry<K, V> entry) {
		if (key.compareTo(entry.getKey()) < 0) {
			//key is less than this entry's key
			if (entry.getLeft() != null) {
				return find(key, entry.getLeft());
			} else {
				return null;
			}
		} else if (key.compareTo(entry.getKey()) == 0) {
			//found the entry
			return entry.getValue();
		} else {
			//key is greater than this entry's key
			if (entry.getRight() != null) {
				return find(key, entry.getRight());
			} else {
				return null;
			}
		}
	}

	@Override
	public void put(K key, V value) {
		BinarySearchTreeEntry<K, V> new_entry;
		
		dict_modifications++;
		new_entry = new BinarySearchTreeEntry<K, V>(key, value);
		if (isEmpty()) {
			root = new_entry;
		} else {
			insert(key, value, root);
		}
	}
	
	/*
	 * Traverses the tree and inserts a key-value pair at the appropriate place
	 */
	private void insert(K key, V value, BinarySearchTreeEntry<K, V> entry) {
		if (key.compareTo(entry.getKey()) < 0) {
			// the key is smaller than this entry's key
			if (entry.getLeft() != null) {
				insert(key, value, entry.getLeft());
			} else {
				entry.addLeft(new BinarySearchTreeEntry<K, V>(key, value));
			}
		} else if (key.compareTo(entry.getKey()) == 0) {
			//the key is the same as the entry's key
			entry.setValue(value);
		} else {
			//the key is greater than this entry's key
			if (entry.getRight() != null) {
				insert(key, value, entry.getRight());
			} else {
				entry.addRight(new BinarySearchTreeEntry<K, V>(key, value));
			}
		}
	}

	@Override
	public void remove(K key) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			
			if (!removeHelper(key, root)) {
				throw new NoSuchElementException();
			} else {
				dict_modifications++;
			}
		}
	}
	
	private boolean removeHelper(K key, BinarySearchTreeEntry<K, V> entry) {
		if (key.compareTo(entry.getKey()) < 0) {
			// the key is smaller than this entry's key
			if (entry.getLeft() != null) {
				return removeHelper(key, entry.getLeft());
			} else {
				return false;
			}
		} else if (key.compareTo(entry.getKey()) == 0) {
			//the entry has been located
			if (entry.getLeft() == null && entry.getRight() == null) {
				entry = null;
			} else if (entry.getLeft() != null && entry.getRight() == null) {
				BinarySearchTreeEntry<K, V> parent;
				parent = entry.getParent();
				
				if (parent == null) {
					root = entry.getLeft();
				} else {
					parent.addLeft(entry.getLeft());
				}
			} else if (entry.getLeft() == null && entry.getRight() != null) {
				BinarySearchTreeEntry<K, V> parent;
				parent = entry.getParent();
				
				if (parent == null) {
					root = entry.getRight();
				} else {
					parent.addRight(entry.getRight());
				}
			} else {
				BinarySearchTreeEntry<K, V> current;
				BinarySearchTreeEntry<K, V> parent;
				
				parent = entry.getParent();
				
				current = entry.getRight();
				while (current.getLeft() != null) {
					current = current.getLeft();
				}
				//current is now set as the left-most entry of the
				//right-hand branch
				
				removeHelper(current.getKey(), entry.getRight());
				
				if (parent == null) {
					root = current;
					root.addLeft(entry.getLeft());
					root.addRight(entry.getRight());
				} else {
					parent = current;
					parent.addLeft(entry.getLeft());
					parent.addRight(entry.getRight());
				}
			}
			
			return true;
		} else {
			//the key is greater than this entry's key
			if (entry.getRight() != null) {
				return removeHelper(key, entry.getRight());
			} else{
				return false;
			}
		}
	}
	
	
	@Override
	public void clear() {
		dict_modifications++;
		root = null;
	}
	
	public int getModifications() {
		return dict_modifications;
	}
	
	@Override
	public String toString() {
		Iterator<DictionaryEntry<K, V>> iterator;
		String output;
		
		iterator = iterator();
		output = "";
		while (iterator.hasNext()) {
			output += String.format(" %s, ", iterator.next());
		}
		
		output = output.substring(0, output.length() - 2);
		return output;
	}
}
