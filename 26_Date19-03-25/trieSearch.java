/*
 * Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
content is a continuous string of characters, and you have a list of keywords 
commonly used in phishing scams. Your mission is to scan the email text and flag 
every segment that exactly matches one of these keywords. In other words, identify 
all index pairs [i, j] such that the substring from position i to j in the email 
text is one of the suspicious keywords. Return these pairs sorted by their starting 
index, and if two pairs share the same starting index, sort them by their ending index.

Input Format
------------
Line-1: string STR(without any space)
Line-2: space separated strings, suspicious keywords[]

Output Format
-------------
Print the pairs[i, j] in sorted order.


Example 1:
----------
Input:  
cybersecuritybreachalert
breach alert cyber

Output: 
0 4
13 18
19 23

Example 2:
----------
Input:  
phishphishingphish
phish phishing

Output:
0 4
5 9
5 12
13 17


Explanation: Notice that keywords can overlap—for instance, the word "phish" appears 
as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

Constraints:

- 1 <= emailText.length <= 100  
- 1 <= suspiciousWords.length <= 20  
- 1 <= suspiciousWords[i].length <= 50  
- emailText and each suspicious word consist of lowercase English letters.  
- All suspicious words are unique.

 */
import java.util.*;

public class trieSearch{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String tars[] = s.nextLine().split(" ");
        Trie trie = new Trie();
        for(String i:tars){
            trie.insert(i);
        }
        List<int[]> res = new ArrayList<>();
        int n = str.length();
        for(int i=0;i<n;i++){
            TrieNode node = trie.root;
            for(int j=i;j<n;j++){
                int ind = str.charAt(j)-'a';
                if(node.children[ind]==null){
                    break;
                }
                node = node.children[ind];
                if(node.isEnd){
                    res.add(new int[]{i,j});
                }
            }
        }
        for(int[] i:res){
            System.out.println(i[0]+" "+i[1]);
        }
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
        for(char i:word.toCharArray()){
            int ind = i-'a';
            if(node.children[ind]==null){
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
        }
        node.isEnd = true;
    }
    public boolean search(String word){
        TrieNode node = root;
        for(char i:word.toCharArray()){
            int ind = i-'a';
            if(node.children[ind]==null){
                return false;
            }
            node = node.children[ind];
        }
        return node.isEnd;
    }
}