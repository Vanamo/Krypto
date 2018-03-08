package crossword.datastructures;

/**
 * My own implementation of ArrayList. I took inspiration from these sites:
 * http://www.docjar.com/html/api/java/util/ArrayList.java.html
 * https://coderanch.com/t/607340/java/Implementing-ArrayList-class-manually
 * This class also implements the method addArray and replace which don't exist
 * in the Java implementation of ArrayList.
 *
 * @author Vanamo Piirainen
 * @param <E> element class
 */
public class CustomArrayList<E> {

    protected Object[] customArray;
    private int size;

    public CustomArrayList() {
        this.customArray = new Object[10];
        this.size = 0;
    }

    /**
     * Constructs a CustomArrayList of predefined length.
     * @param length length of the array which stores the elements of the CustomArrayList 
     */
    public CustomArrayList(int length) {
        this.customArray = new Object[length];
        this.size = 0;
    }

    /**
     * Constructs a copy of the CustomArrayList given as a parameter
     *
     * @param c the contents of c will be copied to the new CustomArrayList 
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
     * Adds a new element to the CustomArrayList.
     * 
     * @param o element to be added
     */
    public void add(E o) {
        if (this.size >= this.customArray.length) {
            this.increaseCapacity();
        }
        this.customArray[this.size] = o;
        this.size++;
    }

    /**
     * Inserts an element to the specified index. Unlike in Java implementation,
     * does not shift the elements. If the position is already occupied by an
     * element, replaces the element with a new one.
     *
     * @param index index of the array position where a new element will be added
     * @param o     new element to be added
     */
    public void add(int index, E o) {
        this.rangeCheckForAdd(index);
        this.customArray[index] = o;
        if (this.size <= index) {
            this.size = index + 1;
        }
    }

    /**
     * Adds the contents of an element array to the custom ArrayList
     *
     * @param array array of elements to be added to the CustomArrayList
     */
    public void addArray(E[] array) {
        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    /**
     *
     * @param index the element at this position index will be returned 
     * @return      the element to be returned
     */
    public E get(int index) {
        this.rangeCheck(index);
        return (E) this.customArray[index];
    }

    /**
     *
     * @return  the amount of elements in the CustomArrayList
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @return  true if there are no elements in the CustomArrayList, otherwise false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     *
     * @param o the element to be searched from the CustomArrayList
     * @return  true if the CustomArrayList contains the element, false otherwise
     */
    public boolean contains(E o) {
        return this.indexOf(o) >= 0;
    }

    /**
     *
     * @param o the element to be searched from the CustomArrayList
     * @return  index of the element, -1 if the CustomArrayList does not contain the element
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
     * Removes the specified element from the CustomArrayList if it contains the element.
     * Shifts the elements on the wright side of the removed element to the left.
     * 
     * @param o the element to be removed
     * @return  true if the CustomArrayList contains the element, false otherwise
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

    /**
     * Helper method for the remove method.
     * Removes the element at the specified index of the CustomArrayList.
     * 
     * @param i the index position of the element to be removed 
     */
    private void removeByIndex(int i) {
        for (int ind = i; ind < this.size - 1; ind++) {
            this.customArray[ind] = this.customArray[ind + 1];
        }
        this.size--;
    }

    /**
     * Checks that there is an element at the specified index of the CustomArrayList
     * 
     * @param index index to be checked 
     */
    private void rangeCheck(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks that the array containing the elements of the CustomArrayList is large enough
     * 
     * @param index index to be checked 
     */
    private void rangeCheckForAdd(int index) {
        if (index > this.customArray.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the elements of the CustomArrayList as an array
     * 
     * @return  array containing the elements of the CustomArrayList
     */
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            array[i] = this.customArray[i];
        }
        return array;
    }
}
