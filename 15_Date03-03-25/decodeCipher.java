// In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
// You have two encoded keys, key1 and key2, both of equal length. Each character 
// in key1 is paired with the corresponding character in key2. 

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.  
// • Mutual Mapping: If a character from key1 maps to one in key2, then that 
//   character in key2 maps back to the one in key1.  
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
//   are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every character 
// with the smallest equivalent character from its equivalence group. 
// The result should be the relatively smallest possible decoded message.


// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------
// Print a string, decoded message which is relatively smallest string of cipherText.

// Example 1: 
// input=
// attitude progress apriori
// output=
// aaogoog


// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
// [d, e, s]. By substituting each character in cipherText with the smallest from 
// its group, you decode the message to "aaogoog".


// Constraints:  
// • 1 <= key1.length, key2.length, cipherText.length <= 1000  
// • key1.length == key2.length  
// • key1, key2, and cipherText consist solely of lowercase English letters.


import java.util.*;

public class decodeCipher {
    public static String solve(String key1,String key2,String cipher){
        StringBuilder res = new StringBuilder();
        DisJointSets set = new DisJointSets();
        for(int i=0;i<key1.length();i++){
            set.union(key1.charAt(i)-97,key2.charAt(i)-97);
        }
        for(int i=0;i<cipher.length();i++){
            char temp = (char)(set.find(cipher.charAt(i)-97)+97);
            res.append(temp);
        }
        return res.toString();
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String key1 = s.next();
        String key2 = s.next();
        String cipher = s.next();
        System.out.println(solve(key1,key2,cipher));
    }
}
class DisJointSets{
    int parent[];
    int rank[];
    DisJointSets(){
        parent = new int[26];
        rank = new int[26];
        for(int i=0;i<26;i++){
            parent[i] = i;
            rank[i] = i;
        }
    }
    int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    boolean union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX==rootY) return false;
        if(rank[rootX]>rank[rootY]){
            parent[rootX] = rootY;
        }
        else if(rank[rootX]<rank[rootY]){
            parent[rootY] = rootX;
        }
        else{
            parent[rootY] = rootX;
            rank[rootX]--;
        }
        return true;
    }
}