/*
 * Given a classic mobile phone, and the key pad of the phone looks like below.
	1		2		3
		   abc	   def
		 
	4		5		6
   ghi     jkl     mno
  
	7		8		9
  pqrs     tuv    wxyz
	
	*		0		#


You are given a string S contains digits between [2-9] only, 
For example: S = "2", then the possible words are "a", "b", "c".

Now your task is to find all possible words that the string S could represent.
and print them in a lexicographical order. 

Input Format:
-------------
A string S, consist of digits [2-9]

Output Format:
--------------
Print the list of words in lexicographical order.


Sample Input-1:
---------------
2

Sample Output-1:
----------------
[a, b, c]


Sample Input-2:
---------------
24

Sample Output-2:
----------------
[ag, ah, ai, bg, bh, bi, cg, ch, ci]

 */

 import java.util.*;

 public class keypad{
      public static void solve(String n,Map<String,char[]> keypad,List<String> res, int ind,String curr){
         if(curr.length()==n.length()){
             res.add(curr);
             return;
         }
         char[] chars = keypad.get(n.charAt(ind)+"");
         for(char i : chars){
             solve(n,keypad,res,ind+1,curr+i);
         }
     }
     public static void main(String[] args){
         Scanner s = new Scanner(System.in);
         String n = "" + s.nextInt();
         Map<String,char []> keypad = new HashMap<>();
         keypad.put("2",new char[]{'a','b','c'});
         keypad.put("3",new char[]{'d','e','f'});
         keypad.put("4",new char[]{'g','h','i'});
         keypad.put("5",new char[]{'j','k','l'});
         keypad.put("6",new char[]{'m','n','o'});
         keypad.put("7",new char[]{'p','q','r','s'});
         keypad.put("8",new char[]{'t','u','v'});
         keypad.put("9",new char[]{'w','x','y','z'});
         List<String> res = new ArrayList<>();
         solve(n,keypad,res,0,"");
         System.out.println(res);
     }
 }
 