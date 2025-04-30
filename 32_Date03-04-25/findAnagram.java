// A detective is investigating a case involving a secret message hidden within a 
// longer note. The detective knows that the culprit rearranged the letters of a 
// short code-word into multiple secret locations within a larger note.

// Given two strings, note (the longer text) and codeWord (the short secret code), 
// your task is to help the detective find all starting positions within the note 
// where any rearrangement or shuffled of codeWord is located.

// Input Format:
// -------------
// Single line space separated strings, two words.

// Output Format:
// --------------
// Print the list of integers, indices.


// Sample Input-1:
// ---------------
// bacdgabcda abcd
 
// Sample Output-1:
// ----------------
// [0, 5, 6]

// Explanation:
// - At index 0: "bacd" is an anagram of "abcd"
// - At index 5: "abcd" matches exactly
// - At index 6: "bcda" is an anagram of "abcd"
// Thus, the positions [0, 5, 6] are returned.

// Sample Input-2:
// ---------------
// bacacbacdcab cab

// Sample Output-2:
// ----------------
// [0, 3, 4, 5, 9]


import java.util.*;

public class findAnagram{
    public static List<Integer> solve(String str,String code){
        List<Integer> res = new ArrayList<>();
        Map<Character,Integer> freqOut = new HashMap<>();
        Map<Character,Integer> freqIn = new HashMap<>();
        for(char i:code.toCharArray()){
            freqOut.put(i,freqOut.getOrDefault(i,0)+1);
        }
        int k = code.length();
        for(int i=0;i<k;i++){
            freqIn.put(str.charAt(i),freqIn.getOrDefault(str.charAt(i),0)+1);
        }
        if(freqIn.equals(freqOut)) res.add(0);
        for(int i=k;i<str.length();i++){
            freqIn.put(str.charAt(i-k),freqIn.get(str.charAt(i-k))-1);
            if(freqIn.get(str.charAt(i-k))==0){
                freqIn.remove(str.charAt(i-k));
            }
            freqIn.put(str.charAt(i),freqIn.getOrDefault(str.charAt(i),0)+1);
            if(freqIn.equals(freqOut)){
                res.add(i-k+1);
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String str = s.next();
        String code = s.next();
        System.out.println(solve(str,code));
    }
}