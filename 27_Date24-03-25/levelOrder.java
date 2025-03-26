// Imagine you're playing a fantasy video game where secret level codes unlock new 
// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when all
// of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. 
// If there is more than one valid code of the same length, choose the one that 
// comes first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)
 
// Output Format
// --------------
// string 


// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every 
// prefix—"m", "ma", "mag", "magi", "magic", "magici", and "magicia"—is present in 
// the list. Although "magicw" appears too, it fails since its prefix "magica" is missing.


// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
// comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""

import java.util.*;

public class levelOrder{
    public static String solve(String[] words){
        String res = "";
        Trie trie = new Trie();
        Arrays.sort(words);
        for(String i : words){
            trie.insert(i);
            if(trie.search(i)){
                if(i.length()>res.length()){
                    res = i;
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] words = s.nextLine().split(" ");
        System.out.print(solve(words));
    }
}

class TrieNode{
    TrieNode[] children;
    boolean isEnd;
    TrieNode(){
        children = new TrieNode[26];
        isEnd = false;
    }
}

class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    public void insert(String word){
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            int ind = ch-'a';
            if(node.children[ind]==null){
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
        }
        node.isEnd = true;
    }
    public boolean search(String word){
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            int ind = ch - 'a';
            if(!node.children[ind].isEnd){
                return false;
            }
            node = node.children[ind];
        }
        return true;
    }
}