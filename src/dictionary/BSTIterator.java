package dictionary;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

public class BSTIterator<K, V> implements Iterator<DictionaryEntry<K, V>> {
	
	private LinkedList<DictionaryEntry<K, V>> dict_list;
	private int dict_modifications;
	private int current_index;
	private final BinarySearchTree<?, V> bst;
	
	public BSTIterator(BinarySearchTree<?, V> bst, 
			LinkedList<DictionaryEntry<K, V>> dict_list) {
		this.bst = bst;
		this.dict_list = dict_list;
		dict_modifications = bst.getModifications();
		current_index = 0;
	}
	
	/*
	 * @return true if there are still remaining items that can be returned
	 *         by the iterator, otherwise false
	 *         
	 * @throws ConcurrentModificationException
	 *        	if the dictionary is modified when this method is called  
	 */
	@Override
	public boolean hasNext() {
		if (dict_modifications != bst.getModifications()) {
			throw new ConcurrentModificationException();
		}
		
		return current_index < dict_list.size();
	}
	
	/*
	 * @return the following item in the list if one exists
	 * 
	 * @throws ConcurrentModificationException
	 *         	if the dictionary is modified when this method is called
	 */
	@Override
	public DictionaryEntry<K, V> next() {
		if (dict_modifications != bst.getModifications()) {
			throw new ConcurrentModificationException();
		}
		
		if (hasNext()) {
			current_index++;
			return dict_list.get(current_index-1);
		} else {
			return null;
		}
	}
	
	/*
	 * @throw UnsupportedOperationException
	 *        	if this method is ever called
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	

}
