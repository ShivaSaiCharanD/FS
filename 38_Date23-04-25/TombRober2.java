/*
 * You are a stealthy archaeologist exploring a circular ring of ancient tombs 
located deep within a jungle. Each tomb holds a certain number of precious 
artifacts. 
However, these tombs are protected by an ancient magical curse: 
if you disturb two adjacent tombs during the same night, the entire ring 
activates a trap that seals you in forever.

The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
to the last. You must plan your artifact retrieval carefully to maximize the 
number of artifacts collected in a single night without triggering the curse.

Given an integer array  artifacts  representing the number of artifacts in each 
tomb, return the   maximum   number of artifacts you can collect without 
disturbing any two adjacent tombs on the same night.

Example 1:  
Input:
2 4 3
Output:  
4   

Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
as they are adjacent in a circular setup.


Example 2:  
Input:
1 2 3 1
Output:  
4

Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
breaking the ancient rule.  
Total = 1 + 3 = 4 artifacts.


Example 3:  
Input:
1 2 3
Output:  
3 

Constraints:  
-  1 <= artifacts.length <= 100 
-  0 <= artifacts[i] <= 1000 

 */

 import java.util.*;

 public class TombRober2{
     public static int memo(int start,int end,int[] dp,int[] art){
         if(end==start){
             return art[end];
         }
         if(end<start){
             return 0;
         }
         if(dp[end]!=-1){
             return dp[end];
         }
         return dp[end] = Math.max(memo(start,end-1,dp,art),art[end]+memo(start,end-2,dp,art));
     }
     public static int solve(int n,int[] art){
         if(n==1) return art[0];
         if(n==2) return Math.max(art[0],art[1]);
         int dp1[] = new int[n];
         int dp2[] = new int[n];
         Arrays.fill(dp1,-1);
         Arrays.fill(dp2,-1);
         int sum1 = memo(0,n-2,dp1,art);
         int sum2 = memo(1,n-1,dp2,art);
         return Math.max(sum1,sum2);
     }
     public static void main(String[] args){
         Scanner s = new Scanner(System.in);
         String inp[] = s.nextLine().split(" ");
         int n = inp.length;
         int[] art = new int[n];
         for(int i=0;i<n;i++){
             art[i] = Integer.parseInt(inp[i]);
         }
         System.out.println(solve(n,art));
     }
 }