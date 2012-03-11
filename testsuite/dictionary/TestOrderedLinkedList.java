package dictionary;

public class TestOrderedLinkedList extends TestDictionary {

    @Override
    public void setUp() {
        d = new OrderedLinkedList<String, Integer>();
    }

    @Override
    public void tearDown() {
        d = null;
    }

}
