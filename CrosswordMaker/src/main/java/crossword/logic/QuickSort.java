package crossword.logic;

import crossword.datastructures.CustomArrayList;

/**
 * My own implementation of quick sort. Code from
 * http://www.java2novice.com/java-sorting-algorithms/quick-sort/
 * 
 * @author Vanamo Piirainen
 */
public class QuickSort {

    private CustomArrayList<WordPosition> array;
    
    /**
     *
     */
    public QuickSort() {
    }
     
    /**
     * Uses quick sort to sort the contents of the custom arraylist. 
     * 
     * @param array CustomArrayList containing WordPositions to be sorted
     */
    public void sort(CustomArrayList<WordPosition> array) {
        this.array = array;
        this.quicksort(0, array.size() - 1);
    }

    /**
     * 
     * @param left  index defining the beginning of the part of the array to be sorted out
     * @param right index defining the end of the part of the array to be sorted out
     */
    private void quicksort(int left, int right) {
        int[] partition = this.makePartition(left, right);
        int i = partition[0];
        int j = partition[1];
        if (left < j) {
            quicksort(left, j);
        }
        if (i < right) {
            quicksort(i, right);
        }

    }

    /**
     * Helper method for quicksort
     *
     * @param left  index defining the beginning of the part of the array to be sorted out
     * @param right index defining the end of the part of the array to be sorted out
     * @return      array containing the indices to make the partition
     */
    private int[] makePartition(int left, int right) {
        WordPosition partitionElement = array.get(left);
        int i = left;
        int j = right;
        while (i <= j) {
            while (array.get(i).compareTo(partitionElement) == -1) {
                i++;
            }
            while (array.get(j).compareTo(partitionElement) == 1) {
                j--;
            }
            if (i <= j) {
                this.switchPlaces(i, j);
                i++;
                j--;
            }
        }
        int[] ij = {i, j};
        return ij;
    }

    /**
     * Helper method for quicksort
     *
     * @param i index of the array
     * @param j index of the array
     */
    private void switchPlaces(int i, int j) {
        WordPosition temp = array.get(i);
        array.add(i, array.get(j));
        array.add(j, temp);
    }

}
