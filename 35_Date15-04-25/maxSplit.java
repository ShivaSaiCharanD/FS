/*
 * Captain Raynor is on a mission to decode an alien transmission.
The transmission contains a single long string S, and it is believed to be 
made up of multiple unique signal chunks, sent one after another with no spaces.

Your task is to help Captain Raynor split the transmission into the maximum 
number of non-empty, unique signal chunks such that when all chunks are 
concatenated in order, they exactly recreate the original transmission S.

Your goal is to maximize the number of unique chunks the message can be split into.

Input Format:
Line-1: A string S representing the alien transmission.

Output Format:
Print a single integer – the maximum number of distinct chunks the string can be split into.

Sample Input-1:
---------------
banana

Sample Output-1:
----------------
4

Explanation: 
------------
One valid way to split the string is: "b", "a", "n", "ana".
This keeps all chunks unique.
Another way like "b", "a", "n", "an", "a" is invalid because "a" appears twice.


Sample Input-2:
---------------
mississippi

Sample Output-2:
----------------
7

Explanation: 
------------
One valid way to split it is: "m", "i", "s", "si", "ssi", "p", "pi".
All chunks are distinct and together recreate the original transmission.

NOTE: Only contiguous chunks (i.e., substrings) are allowed. Subsequence-based 
      splitting is not permitted.

 */

 import java.util.*;

 public class maxSplit{
     public static int solve(String str){
         Set<String> set = new HashSet<>();
         int n = str.length();
         return backtrack(str,set,new int[]{0},0,n);
     }
     public static int backtrack(String str,Set<String> set,int[] max,int ind,int len){
         if(ind==len){
             max[0] = Math.max(max[0],set.size());
             return max[0];
         }
         for(int i = ind+1;i<=len;i++){
             String substr = str.substring(ind,i);
             if(set.contains(substr)) continue ;
             set.add(str.substring(ind,i));
             backtrack(str,set,max,i,len);
             set.remove(str.substring(ind,i));
         }
         return max[0];
     }
     public static void main(String[] args){
         Scanner s = new Scanner(System.in);
         String str = s.next();
         System.out.println(solve(str));
     }
 }