/**
 * A Binary Search Tree implementation of the interface WordMap.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

//import java.util.Arrays;

public class TreeWordMap implements WordMap {

    private static class Elem {

        private String key;
        private int count;
        private Elem left, right;

        private Elem(String key) {
            this.key = key;
            count = 1;
        }
    }

    private Elem root;
    private int size;
    private LinkedList<Elem> linkedList = new LinkedList<Elem>();

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified key
     * @throws NullPointerException if the value of the parameter is null
     */

    public boolean contains(String key) {

        if (key == null) {
            throw new NullPointerException();
        }

        boolean found = false;
        Elem current = root;
        while (! found && current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                found = true;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return found;
    }

    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param key the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public void update(String key) {

        if (key == null) {
            throw new NullPointerException();
        }

        Elem current = root;

        if (current == null){
            root = new Elem(key);
            size = 1;
        }

        else{
            boolean done = false;

            while (! done){
                int test = key.compareTo(current.key);
                if (test == 0){
                    current.count++;
                    done = true;
                }
                else if(test < 0){
                    if (current.left == null){
                        current.left = new Elem(key);
                        size++;
                        done = true;
                    }
                    else{
                      current = current.left;
                    }
                }
                else{
                    if (current.right == null){
                        current.right = new Elem(key);
                        size++;
                        done = true;
                    }
                    else{
                        current = current.right;
                    }
                }
            }
        }
    }

    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param key the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */

    public int get(String key) {

        if (key == null) {
            throw new NullPointerException();
        }

        boolean found = false;
        Elem current = root;
        while (current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                return current.count;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return 0;
    }

    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */

    public int size() {
        return size;
    }

    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */

    public String[] keys() {

        inOrder(root);

        String[] keys = new String[size()];

        for (int i = 0; i < size(); i++){
            keys[i] = linkedList.get(i).key;
        }

        return keys;
    }

    /**
     * Returns all the counts associated with keys in this
     * WordMap. The counts are in the same order as that of the keys
     * returned by the method keys().
     *
     * @return all the counts
     */

    public Integer[] counts() {

        Integer[] counts = new Integer[size()];
        String[] keys = keys();

        for (int i = 0; i<size(); i++){
            counts[i] = get(keys[i]);
        }
        return counts;
    }

    //adds all elements in order to instance variable linkedList
    //so all values can be accessed

    private void inOrder(Elem current){
        if (current != null){
            inOrder(current.left);
            linkedList.addLast(current);
            inOrder(current.right);
        }
    }
}
