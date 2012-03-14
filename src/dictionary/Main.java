package dictionary;

import java.io.FileNotFoundException;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {
    	OrderedLinkedList<String, Integer> oll;
    	oll = new OrderedLinkedList<String, Integer>();
    	
    	System.out.println(oll.isEmpty());
    	
    	oll.put("Hey", 150);
    	oll.put("Test", 120);
    	oll.put("Ha", 160);
    	oll.put("Ah", 200);
    	System.out.println(oll);
    	System.out.println(oll.size());
    	
    	oll.remove("Hey");
    	oll.remove("Ah");
    	
    	System.out.println(oll);
    	System.out.println(oll.size());
    	
    	System.out.println(oll.isEmpty());
    }
    
    

}
