package krypto;

import java.util.ArrayList;

/**
 * Generates a search tree for words. Words can be added to the tree as a list
 * or one by one.
 *
 * @author Vanamo Piirainen
 *
 */
public class SearchTree {

    private Node root;
    private char rootChar;

    public SearchTree() {
        this.rootChar = ' ';
        this.root = new Node(rootChar);
    }

    public void addListOfWords(ArrayList<String> wordList) {
        for (String word : wordList) {
            addWord(word);
        }
    }

    public void addWord(String word) {
        Node parentNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            Node node = new Node(word.charAt(i));
            Node inTreeNode = inTree(parentNode, node);
            if (inTreeNode == null) {
                parentNode.setChild(node);
                parentNode = node;
            } else {
                parentNode = inTreeNode;
            }
        }
        parentNode.addWord(word);
    }

    /**
     * Checks if the parent node already has the letter as a child node and 
     * returns the node if it exists. Otherwise returns null. 
     *
     * @param parent
     * @param node
     * @return
     */
    private Node inTree(Node parent, Node node) {
        Node child = parent.getChild();
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
        System.out.println("Search Tree");
        preorderTreeWalk(this.root);
    }

    private void preorderTreeWalk(Node node) {
        System.out.println(node.getKey() + " (" + node.getWord() + ")");
        Node childNode = node.getChild();
        while (childNode != null) {
            preorderTreeWalk(childNode);
            childNode = childNode.getNext();
        }
    }
}
