package crossword.logic;

import java.util.ArrayList;

/**
 * Generates a search tree for strings. Strings can be added to the tree as a list
 * or one by one.
 *
 * @author Vanamo Piirainen
 *
 */
public class SearchTree {

    private SearchTreeNode root;
    private char rootChar;

    public SearchTree() {
        this.rootChar = ' ';
        this.root = new SearchTreeNode(rootChar);
    }

    public void addListOfWords(ArrayList<String> wordList) {
        for (String word : wordList) {
            addWord(word);
        }
    }

    public void addWord(String word) {
        if (word.isEmpty()) return;
        SearchTreeNode parentNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            SearchTreeNode node = new SearchTreeNode(word.charAt(i));
            SearchTreeNode inTreeNode = inTree(parentNode, node);
            if (inTreeNode == null) {
                parentNode.setChild(node);
                parentNode = node;
            } else {
                parentNode = inTreeNode;
            }
        }
        parentNode.setWord(word);
    }

    /**
     * Checks if the parent node already has the letter as a child node and 
     * returns the node if it exists. Otherwise returns null. 
     *
     * @param parent
     * @param node
     * @return
     */
    private SearchTreeNode inTree(SearchTreeNode parent, SearchTreeNode node) {
        SearchTreeNode child = parent.getChild();
        if (child != null) {
            while (true) {
                if (child.getKey() == node.getKey()) {
                    return child;
                }
                if (child.isLast()) {
                    break;
                }
                child = child.getNext();
            }
        }
        return null;
    }

    public void printTree() {
        preorderTreeWalk(this.root);
    }

    private void preorderTreeWalk(SearchTreeNode node) {
        System.out.println(node.getKey() + " (" + node.getWord() + ") ");
        SearchTreeNode childNode = node.getChild();
        while (childNode != null) {
            preorderTreeWalk(childNode);
            childNode = childNode.getNext();
        }
    }
    
    public SearchTreeNode getRoot() {
        return this.root;
    }
}
