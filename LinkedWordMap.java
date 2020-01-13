/**
 * An implementation of the interface WordMap using linked elements.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

public class LinkedWordMap implements WordMap {

    //implementation of the doubly linked nodes

    private static class Elem{

        private String key;
        private int count;
        private Elem next;
        private Elem prev;

        private Elem(String key, Elem prev, Elem next){
            this.key = key;
            count = 1;
            this.prev = prev;
            this.next = next;
        }
    }

    //instance variable

    private Elem head;

    //counstructor

    public LinkedWordMap(){
        head = new Elem(null, null, null);
        head.next = head;
        head.prev = head;
    }

    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */

    public int size() {

        Elem c = head;
        int size = 0;

        while (c.next != head){
            c = c.next;
            size++;
        }
        return size;
    }

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public boolean contains(String key) {

        if (key == null){
            throw new NullPointerException();
        }

        Elem c = head;

        while (c.next != head){
            c = c.next;
            if (key == c.key){
                return true;
            }
        }
        return false;
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

        if (key == null){
            throw new NullPointerException();
        }

        if (contains(key)){

            Elem c = head;

            while (c.next != head){
                c = c.next;
                if(key == c.key){
                    return c.count;
                }
            }
        }
        return 0;
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

        if (key == null){
            throw new NullPointerException();
        }

        if ( !contains(key) ){


            Elem current = head;
            if (current.next == head){
                Elem newElem = new Elem(key, head, head);
                head.next = newElem;
                head.prev = newElem;
            }
            else{
                while ( current.next != head && key.compareTo(current.next.key) > 0){
                    current = current.next;
                }
                Elem newElem = new Elem (key, current, current.next);
                current.next.prev = newElem;
                current.next = newElem;
            }
        }

        else{
            Elem c = head;
            while (c.next != head){
                c = c.next;
                if( key == c.key ){
                    c.count++;
                    return;
                }
            }
        }
    }

    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */

    public String[] keys() {

        String[] keys = new String[size()];
        Elem c = head;
        int i = 0;

        while (c.next != head){
            c = c.next;
            keys[i] = c.key;
            i++;
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

        int size = size();
        Integer[] counts = new Integer[size];
        String[] keys = keys();

        for (int i = 0; i<size; i++){
            counts[i] = get(keys[i]);
        }
        return counts;
    }
}
