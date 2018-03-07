package crossword.datastructures;

/**
 * Nodes of the SearchTree
 *
 * @author Vanamo Piirainen
 */
public class SearchTreeNode {

    private char key;
    private boolean last;
    private SearchTreeNode child;
    private SearchTreeNode next;
    private String word;

    /**
     *
     * @param key   the letter which the node contains 
     */
    public SearchTreeNode(char key) {
        this.key = key;
        this.last = true;
        this.child = null;
        this.next = null;
        this.word = null;
    }

    /**
     * If this node does not have a child node yet, the newChild is set as a child node.
     * Otherwise it is set as the next node of the last child node. 
     * 
     * @param newChild  the node to be added as a child node
     */
    public void setChild(SearchTreeNode newChild) {
        if (this.child == null) {
            this.child = newChild;
        } else {
            setNext(newChild);
        }
    }

    /**
     * Sets the new child node as the last child of this node 
     * 
     * @param newChild the node to be added as a child node
     */
    private void setNext(SearchTreeNode newChild) {
        SearchTreeNode prevChild = this.child;
        while (!prevChild.last) {
            prevChild = prevChild.next;
        }
        prevChild.next = newChild;
        prevChild.last = false;
    }
    
    /**
     * Method to set a child node manually, used only for testing
     * 
     * @param newChild the node to be added as a child node
     * @see   setNextForTesting
     */
    public void setChildForTesting(SearchTreeNode newChild) {
        this.child = newChild;
    }

    
    /**
     * Method to set a child node manually, used only for testing
     * 
     * @param newNext   node to be set as next
     * @see     setChildForTesting
     */
    public void setNextForTesting(SearchTreeNode newNext) {
        this.next = newNext;
    }
    
    /**
     *
     * @param word  the node having the last letter of the word as a key contains the word
     */
    public void setWord(String word) {
        this.word = word;
    }
    
    /**
     *
     * @return  the word in this node, null if there is not a word
     */
    public String getWord() {
        return this.word;
    }

    /**
     *
     * @return  true if this is the last child node, false otherwise
     */
    public boolean isLast() {
        return last;
    }
    
    /**
     * Sets the last node manually for testing purposes
     * 
     * @param value true if this is the last child node, false otherwise
     */
    public void setIsLastForTesting(boolean value) {
        this.last = value;
    }

    /**
     *
     * @return  child node of this node
     */
    public SearchTreeNode getChild() {
        return child;
    }

    /**
     *
     * @return  next node of this node
     */
    public SearchTreeNode getNext() {
        return next;
    }

    /**
     *
     * @return  key of this node (a letter)
     */
    public char getKey() {
        return key;
    }
}
