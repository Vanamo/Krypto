package crossword.datastructures;

/**
 * Nodes of the search tree
 *
 * @author vseppane
 */
public class SearchTreeNode {

    private char key;
    private boolean last;
    private SearchTreeNode child;
    private SearchTreeNode next;
    private String word;

    public SearchTreeNode(char key) {
        this.key = key;
        this.last = true;
        this.child = null;
        this.next = null;
        this.word = null;
    }

    public void setChild(SearchTreeNode newChild) {
        if (this.child == null) {
            this.child = newChild;
        } else {
            setNext(newChild);
        }
    }
    
    public void setChildForTesting(SearchTreeNode newChild) {
        this.child = newChild;
    }

    private void setNext(SearchTreeNode newChild) {
        SearchTreeNode prevChild = this.child;
        while (!prevChild.last) {
            prevChild = prevChild.next;
        }
        prevChild.next = newChild;
        prevChild.last = false;
    }

    public void setNextForTesting(SearchTreeNode newNext) {
        this.next = newNext;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
    
    public String getWord() {
        return this.word;
    }

    public boolean isLast() {
        return last;
    }
    
    public void setIsLastForTesting(boolean value) {
        this.last = value;
    }

    public SearchTreeNode getChild() {
        return child;
    }

    public SearchTreeNode getNext() {
        return next;
    }

    public char getKey() {
        return key;
    }
}
