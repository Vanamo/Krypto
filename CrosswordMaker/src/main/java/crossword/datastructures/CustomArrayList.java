package crossword.datastructures;

import java.util.Arrays;

/**
 * My own implementation of ArrayList. I took inspiration from these sites:
 * http://www.docjar.com/html/api/java/util/ArrayList.java.html
 * https://coderanch.com/t/607340/java/Implementing-ArrayList-class-manually
 * This class also implements the method addArray and replace which don't 
 * exist in the Java implementation of ArrayList.
 * 
 * @author Vanamo Piirainen
 */
public class CustomArrayList {

    private Object[] customArray;
    private int size;

    /**
     *
     */
    public CustomArrayList() {
        this.customArray = new Object[10];
        this.size = 0;
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
    public void add(Object o) {
        if (this.size >= this.customArray.length) {
            this.increaseCapacity();
        }
        this.customArray[this.size] = o;
        this.size++;
    }

    /**
     * Replaces an object at the specified index with another object.
     * @param index
     * @param o 
     */
    public void replace(int index, Object o) {
        this.rangeCheck(index);
        this.customArray[index] = o;
    }
    
    /**
     * Adds the contents of an object array to the custom ArrayList
     * @param array
     */
    public void addArray(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        this.rangeCheck(index);
        return this.customArray[index];
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
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    /**
     *
     * @param o
     * @return
     */
    public int indexOf(Object o) {
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
    public boolean remove(Object o) {
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
        int size = this.size;
        for (int ind = i; ind < size; ind++) {
            this.customArray[ind] = this.customArray[ind + 1];
        }
        this.size--;
    }

    private void rangeCheck(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *
     * @return
     */
    public Object[] getCustomArrayForTesting() {
        return customArray;
    }
}
