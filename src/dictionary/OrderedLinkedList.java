package dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/*
 * Ordered linked list based implementation of the Dictionary
 * interface. The nodes of the list are ordered in ascending order by
 * the key-attribute of type K. Duplicate keys are not permitted.
 */
public class OrderedLinkedList<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {
	
	private OrderedLinkedListEntry<K, V> root;
	private int dict_modified = 0;
	
	/*
	 * 
	 * @return an iterator through the BST dictionary from 
	 *         the entry with the smallest key to the 
	 *         element with the greatest key
	 * 
	 */
	@Override
	public Iterator<DictionaryEntry<K, V>> iterator() {
		LinkedList<DictionaryEntry<K, V>> entry_list;
		Iterator<DictionaryEntry<K, V>> iterator;
		OrderedLinkedListEntry<K, V> current;
		
		entry_list = new LinkedList<DictionaryEntry<K, V>>();
		current = root;
		while (current != null) {
			entry_list.add(current);
			current = current.getNext();
		}
		
		iterator = new OLLIterator<K, V>(this, entry_list);
		return iterator;
	}
	
	/*
	 * @return the number of entries in the dictionary
	 */
	@Override
	public int size() {
		OrderedLinkedListEntry<K, V> current;
		int size;
		
		current = root; size = 0;
		while (current != null) {
			size++;
			current = current.getNext();
		}
		
		return size;
	}
	
	/*
	 * @return true if the dictionary contains no entries, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	/*
	 * @param key 
	 *        	The key being searched for in the dictionary
	 * 
	 * @return the value associated with the key if it exists in the 
	 *         dictionary
	 * 
	 * @throws NoSuchElementException
	 *         	if key does not exist within the dictionary
	 */
	@Override
	public V get(K key) throws NoSuchElementException {
		OrderedLinkedListEntry<K, V> current;
		current = root;
		
		while (current != null) {
			if (current.getKey() == key) {
				return current.getValue();
			} else {
				current = current.getNext();
			}
		}
		
		throw new NoSuchElementException();
	}
	
	/*
	 * @param key
	 *        	the key to be inserted or modified in the dictionary
	 *
	 * @param value
	 *          the value associated with the key
	 */
	@Override
	public void put(K key, V value) {
		OrderedLinkedListEntry<K, V> current;
		OrderedLinkedListEntry<K, V> next;
		OrderedLinkedListEntry<K, V> new_entry;
		
		dict_modified++;
		current = root;
		if (isEmpty()) {
			root = new OrderedLinkedListEntry<K, V>(key, value);
			return;
		} else if (root.getKey().compareTo(key) > 0) {
			new_entry = new OrderedLinkedListEntry<K, V>(key, value);
			new_entry.addNext(root);
			root = new_entry;
			return;
		}
		
		while (current.getNext() != null) {
			next = current.getNext();
			if (next.getKey().compareTo(key) < 0) {
				//the next entry's key is smaller than the key to insert
				current = next;
			} else if (next.getKey().compareTo(key) == 0) {
				//found a matching key
				next.setValue(value);
				return;
			} else {
				//the next entry's key is greater than the key to insert
				new_entry = new OrderedLinkedListEntry<K, V>(key, value);
				current.addNext(new_entry);
				new_entry.addNext(next);
				return;
			}
		}
		
		new_entry = new OrderedLinkedListEntry<K, V>(key, value);
		current.addNext(new_entry);
	}
	
	/*
	 * @param key
	 *          the key to be removed from the dictionary
	 * 
	 * @throws NoSuchElementException
	 *           if the key does not exist in the dictionary
	 */
	@Override
	public void remove(K key) throws NoSuchElementException {
		OrderedLinkedListEntry<K, V> current;
		OrderedLinkedListEntry<K, V> next;
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		current = root;
		if (root.getKey().compareTo(key) == 0) {
			//the first entry matches
			dict_modified++;
			root = root.getNext();
			return;
		}
		while (current.getNext() != null) {
			next = current.getNext();
			if (next.getKey().compareTo(key) < 0) {
				//the next entry's key is smaller than the key to insert
				current = next;
			} else if (next.getKey().compareTo(key) == 0) {
				//found a matching key
				dict_modified++;
				current.addNext(next.getNext());
				return;
			} else {
				//the next entry's key is greater than the key to insert
				throw new NoSuchElementException();
			}
		}
		
		throw new NoSuchElementException();
	}
	
	/*
	 * Removes all elements from the dictionary
	 */
	@Override
	public void clear() {
		dict_modified++;
		root = null;	
	}
	
	/*
	 * @return the number of modifications carried out on the
	 *         dictionary 
	 */
	public int getModifications() {
		return dict_modified;
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
