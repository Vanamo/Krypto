package crossword.logic;

import crossword.datastructures.CustomArrayList;

/**
 *
 * @author Vanamo Piirainen
 */
public class QuickSort {

    private CustomArrayList<WordPosition> array;
    
    public QuickSort() {
    }
     
    /**
     * Uses quick sort to sort the contents of the custom arraylist. Code from
     * http://www.java2novice.com/java-sorting-algorithms/quick-sort/
     */
    public void sort(CustomArrayList<WordPosition> array) {
        this.array = array;
        this.quicksort(0, array.size() - 1);
    }

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
     * @param left
     * @param right
     * @return
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
     * @param i
     * @param j
     */
    private void switchPlaces(int i, int j) {
        WordPosition temp = array.get(i);
        array.add(i, array.get(j));
        array.add(j, temp);
    }

}
