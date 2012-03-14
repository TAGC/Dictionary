package dictionary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

import dictionary.InsertComplexities.InstrumentedKey;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {
    	FileOutputStream fstream;
    	PrintStream pstream;
    	OrderedLinkedList<InstrumentedKey, Integer> oll;
    	BinarySearchTree<InstrumentedKey, Integer> bst;
    	InsertComplexities comp;
    	Random random;
    	int[] oll_data, bst_data;
    	
    	oll = new OrderedLinkedList<InstrumentedKey, Integer>();
    	bst = new BinarySearchTree<InstrumentedKey, Integer>();
    	
    	random = new Random();
    	comp = new InsertComplexities(random);
    	oll_data = comp.getInsertComplexities(oll, MAX_SIZE, REPITITIONS);
    	bst_data = comp.getInsertComplexities(bst, MAX_SIZE, REPITITIONS);
    	
    	fstream = new FileOutputStream("OrderedLinkedList.dat");
    	pstream = new PrintStream(fstream);
    	System.setOut(pstream);
    	
    	for (int i = 0; i < MAX_SIZE; i++) {
    		System.out.println(i + "\t" + oll_data[i]);
    	}
    	
    	fstream = new FileOutputStream("BinarySearchTree.dat");
    	pstream = new PrintStream(fstream);
    	System.setOut(pstream);
    	
    	for (int i = 0; i < MAX_SIZE; i++) {
    		System.out.println(i + "\t" + bst_data[i]);
    	}
    	
    	
    }
    
    

}
