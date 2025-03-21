/*
 * 
 * Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

Below is the mapping of digits to letters (as found on a traditional telephone keypad):

| Digit | Letters       |
|-------|---------------|
| 2     | a, b, c       |
| 3     | d, e, f       |
| 4     | g, h, i       |
| 5     | j, k, l       |
| 6     | m, n, o       |
| 7     | p, q, r, s    |
| 8     | t, u, v       |
| 9     | w, x, y, z    |

Note: The digit 1 does not correspond to any letters.

Example 1:
Input: 23  
Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

Example 2:
Input: 2 
Output: [a, b, c]


Constraints:

- 0 <= digits.length <= 4  
- Each digit in the input is between '2' and '9'.

 */

import java.util.*;

public class keyPad {
     public static void solve(String n,Map<String,char[]> keypad,List<String> res, int ind,String curr){
        if(curr.length()==n.length()){
            res.add(curr);
            return;
        }
        char[] lets = keypad.get(n.charAt(ind)+"");
        for(int i=0;i<lets.length;i++){
            solve(n,keypad,res,ind+1,curr+lets[i]);
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
