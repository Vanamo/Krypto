package crossword.datastructures;

/**
 * My own implementation of ArrayList. I took inspiration from these sites:
 * http://www.docjar.com/html/api/java/util/ArrayList.java.html
 * https://coderanch.com/t/607340/java/Implementing-ArrayList-class-manually
 * This class also implements the method addArray and replace which don't exist
 * in the Java implementation of ArrayList.
 *
 * @author Vanamo Piirainen
 * @param <E>
 */
public class CustomArrayList<E> {

    protected Object[] customArray;
    private int size;

    /**
     *
     */
    public CustomArrayList() {
        this.customArray = new Object[10];
        this.size = 0;
    }

    /**
     * Constructs a copy of the CustomArrayList given as a parameter
     *
     * @param c
     */
    public CustomArrayList(CustomArrayList<E> c) {
        this.size = c.size();
        this.customArray = new Object[this.size + 1];
        for (int i = 0; i < this.size; i++) {
            this.customArray[i] = c.get(i);
        }
    }

    private void increaseCapacity() {
        int oldCapacity = this.customArray.length;
        Object[] copyOfArray = new Object[oldCapacity + this.size];
        for (int i = 0; i < oldCapacity; i++) {
            copyOfArray[i] = this.customArray[i];
        }
        this.customArray = copyOfArray;
    }

    /**
     *
     * @param o
     */
    public void add(E o) {
        if (this.size >= this.customArray.length) {
            this.increaseCapacity();
        }
        this.customArray[this.size] = o;
        this.size++;
    }

    /**
     * Replaces an object at the specified index with another object.
     *
     * @param index
     * @param o
     */
    public void replace(int index, E o) {
        this.rangeCheck(index);
        this.customArray[index] = o;
    }

    /**
     * Adds the contents of an object array to the custom ArrayList
     *
     * @param array
     */
    public void addArray(E[] array) {
        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public E get(int index) {
        this.rangeCheck(index);
        return (E) this.customArray[index];
    }

    /**
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean contains(E o) {
        return this.indexOf(o) >= 0;
    }

    /**
     *
     * @param o
     * @return
     */
    public int indexOf(E o) {
        if (o == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.customArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (o.equals(this.customArray[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean remove(E o) {
        if (o == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.customArray[i] == null) {
                    this.removeByIndex(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (o.equals(this.customArray[i])) {
                    this.removeByIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void removeByIndex(int i) {
        for (int ind = i; ind < this.size; ind++) {
            this.customArray[ind] = this.customArray[ind + 1];
        }
        this.size--;
    }

    private void rangeCheck(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            array[i] = this.customArray[i];
        }
        return array;
    }
}
