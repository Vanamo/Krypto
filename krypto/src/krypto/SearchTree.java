/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krypto;

import java.util.ArrayList;

/**
 * Generate a search tree from a list of words
 *
 * @author Vanamo Piirainen
 * 
 */
public class SearchTree {
    
    private Node root;
    private char rootChar;
    private ArrayList<String> wordList;

    public SearchTree(ArrayList<String> wordList) {
        this.wordList = wordList;
        this.rootChar = ' ';
        this.root = new Node(rootChar);
        generateSearchTree();
    }
    
    private void generateSearchTree() {
        for (String word : this.wordList) {
            addWordToTree(word);
        }
    }

    private void addWordToTree(String word) {
        
    }

}
