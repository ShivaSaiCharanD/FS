// Imagine you're managing a busy warehouse where every product is delivered in 
// pairs to ensure proper stocking. However, due to a mix-up at the shipping dock, 
// two unique product IDs ended up without their matching pair, while all other 
// products arrived as complete pairs. Your task is to identify these two solitary 
// product IDs.

// You're given list of product IDs. In this list, every product ID appears exactly 
// twice except for two IDs that appear only once. Return these two unique product 
// \IDs in any order.

// You must design an algorithm that runs in linear time and uses only constant 
// extra space.


// Example 1:
// ----------
// Input: 
// 101 102 101 103 102 105  
// Output: 
// [103, 105] 
 
// Explanation: The IDs 103 and 105 appear only once, while all other IDs appear 
// twice. [105, 103] is also an acceptable answer.

// Example 2:
// -----------
// Input: 121 136
// Output: [121, 136] 


import java.util.*;

public class oneOccurence {
    public static void solve(int n,int[] arr,List<Integer> res){
        int xor = 0;
        for(int i : arr){
            xor^=i;
        }
        int diff = xor & -xor;
        int a = 0;
        int b = 0;
        for(int i : arr){
            if((i&diff)==0){
                a^=i;
            }
            else{
                b^=i;
            }
        }
        res.add(b);
        res.add(a);
        return;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] str = s.nextLine().split(" ");
        int n = str.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(str[i]);
        }
        List<Integer> res = new ArrayList<>();
        solve(n,arr,res);
        System.out.println(res);
    }
}
