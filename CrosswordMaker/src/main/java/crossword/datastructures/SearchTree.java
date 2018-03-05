package crossword.datastructures;


/**
 * Generates a search tree for strings. Strings can be added to the tree as a list
 * or one by one. The strings are added to the tree letter by letter so that the next letter
 * is in the child node of the previous letter. If the letter is the last letter 
 * of the string, the node will contain also the string. Pseudocode from the 
 * lecture notes of Jyrki Kivinen, Datastructures and algorithms, fall 2017 
 * (general search tree).
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

    /**
     *
     * @param wordList  list of strings to be added to the SearchTree
     */
    public void addListOfWords(CustomArrayList<String> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            addWord(wordList.get(i));
        }
    }

    /**
     *
     * @param word  string to be added to the SearchTree
     */
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
     * @param parent the children of this node will be searched
     * @param node   the key of this node will be searched for   
     * @return       the node which contains the same key as the node that was searched for
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
    
    /**
     * 
     * @return  the root of the SearchTree
     */
    public SearchTreeNode getRoot() {
        return this.root;
    }
}
