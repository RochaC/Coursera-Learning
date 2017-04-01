package spelling;

import java.util.List;
import java.util.Set;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		word = word.toLowerCase();
//		System.out.println("word to add:   "+ word);
//		System.out.println("\n");
		Boolean result = null;
		if(isWord(word))
			return false;
		
		TrieNode pointer = root;
		char[] word_a = word.toCharArray();
		
		for(char c:word_a){
//			System.out.println("Process :" + c);
			if(pointer.getValidNextCharacters().contains(c)){
				if(pointer.getChild(c).getText().equals(word)){
//					System.out.println("add result:   " + pointer.getChild(c).getText());
//					System.out.println("\n");
					pointer.getChild(c).setEndsWord(true);
					size++;
					result = true;
					break;
				}
				pointer = pointer.getChild(c);
			}
			
			else{
				pointer.insert(c);
//				System.out.println("add char tree:  " + pointer.getChild(c).getText());
				if(pointer.getChild(c).getText().equals(word)){
//					System.out.println("add result:   " + pointer.getChild(c).getText());
//					System.out.println("\n");
					pointer.getChild(c).setEndsWord(true);
					size++;
					result = true;
					break;
				}
				else 
					pointer = pointer.getChild(c);
			}
			
		}
		return result;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		
		s = s.toLowerCase();
//		System.out.println("To be find: "+s);
		char[] s_a = s.toCharArray();
		TrieNode pointer = root;
		Boolean result = false;
		
		for(char c:s_a){
			if(pointer.getValidNextCharacters().contains(c)){
				if(pointer.getChild(c).endsWord()){
					if(pointer.getChild(c).getText().equals(s)){
//						System.out.println("That's in the tree");
//						System.out.println("\n");
						result = true;
//						System.out.println(result);
						break;
					}
				}
				pointer = pointer.getChild(c);
			}
			
		}
		return result;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode pointer = root;
    	 prefix = prefix.toLowerCase();
    	 char[] words = prefix.toCharArray();
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 List<String> completions = new ArrayList<String>();
    	 boolean flag = false;
    	 
    	 //  find the stem
    	 for(char c:words){
    		 if(pointer.getValidNextCharacters().contains(c)){
    			 if(pointer.getChild(c).getText().equals(prefix)){
    				 flag =true;
    				 pointer = pointer.getChild(c);
    				 System.out.println("The prefix is : " + prefix);
    				 System.out.println("The pointer is : " + pointer.getText());
    				 break;
    			 }
    			 else
    				 pointer = pointer.getChild(c);
    			 }
    		 else 
    			 break;
    	 }
    	 
    	 if(prefix == "")
    		 flag = true;
    	 
    	 //  do the breadth first search
    	 queue.add(pointer);
    	 
    	 //pointer is a word
    		if(pointer.endsWord())
				completions.add(pointer.getText());
				
    	 while(flag && queue.size() != 0 ){
    		 System.out.println(queue.get(0).getValidNextCharacters());
    		 if(queue.get(0).getValidNextCharacters().isEmpty()){
    			 System.out.println("remove the node");
    			 queue.remove(0);
    			 }
    		 
    		 else{
    			for(char c : queue.get(0).getValidNextCharacters()){
    				queue.add(queue.get(0).getChild(c));
    				if(queue.get(0).getChild(c).endsWord()){
    					completions.add(queue.get(0).getChild(c).getText());
    					System.out.println("get the word: "+ queue.get(0).getChild(c).getText());
    				}
    			}
    			queue.remove(0);
    		 }
    		 
    	 }
    	 
    	 //delete the rest words
    	 while(completions.size() > numCompletions)
    	 {
    		 int n = completions.size();
    		 completions.remove(n-1);
    	 }
    	 
    	 for(int i=0;i < completions.size();i++)
    		 System.out.println("In the list : " + completions.get(i));
    	 
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}