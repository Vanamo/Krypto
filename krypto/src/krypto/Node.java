/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krypto;

/**
 * Nodes of the search tree
 *
 * @author vseppane
 */
class Node {

    private char key;
    private boolean last;
    private Node child;
    private Node next;
    private String word;

    public Node(char key) {
        this.key = key;
        this.next = this;
        this.last = true;
    }

    public void setChild(Node newChild) {
        if (this.child == null) {
            this.child = newChild;
            this.child.next = this;
        } else {
            setNext(this.child, newChild);
        }
    }

    private void setNext(Node child, Node newChild) {
        Node lastChild = child;
        while (!lastChild.last) {
            lastChild = lastChild.next;
        }
        lastChild.next = newChild;
        newChild.next = this;
        lastChild.last = false;
    }
    
    public void addWord(String word) {
        this.word = word;
    }
    
    public String getWord() {
        return this.word;
    }

    public boolean isLast() {
        return last;
    }

    public Node getChild() {
        return child;
    }

    public Node getNext() {
        return next;
    }
}
