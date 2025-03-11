// AlphaCipher is a string formed from another string by rearranging its letters

// You are given a string S.
// Your task is to check, can any one of the AlphaCipher is a palindrome or not.

// Input Format:
// -------------
// A string S

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// carrace

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// code

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class palindrome{
    public static boolean solve(String str){
        int mask = 0;
        for(int i=0;i<str.length();i++){
            mask = (1<<(str.charAt(i)-'a'))^mask;
        }
        return (mask&(mask-1)) == 0;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String str = s.next();
        System.out.println(solve(str));
    }
}