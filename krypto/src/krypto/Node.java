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
        this.last = true;
        this.child = null;
        this.next = null;
        this.word = null;
    }

    public void setChild(Node newChild) {
        if (this.child == null) {
            this.child = newChild;
        } else {
            setNext(newChild);
        }
    }
    
    public void setChildForTesting(Node newChild) {
        this.child = newChild;
    }

    private void setNext(Node newChild) {
        Node prevChild = this.child;
        while (!prevChild.last) {
            prevChild = prevChild.next;
        }
        prevChild.next = newChild;
        prevChild.last = false;
    }

    public void setNextForTesting(Node newNext) {
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

    public Node getChild() {
        return child;
    }

    public Node getNext() {
        return next;
    }

    public char getKey() {
        return key;
    }
}
