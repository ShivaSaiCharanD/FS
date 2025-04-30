// You are working in a genetics laboratory where you are tasked with correcting 
// DNA sequences. Each DNA strand is represented as a sequence of characters 
// 'A', 'C', 'G', and 'T'.
// Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
// must be modified to match a targetDNA sequence exactly.

// You can perform the following mutation operations:
// - Insert a nucleotide (A, C, G, or T) into the DNA strand.
// - Delete a nucleotide from the DNA strand.
// - Replace a nucleotide with another one.

// Each operation counts as one step.

// Your task is to find the minimum number of mutation operations needed to 
// transform the originalDNA into the targetDNA.

// Input format:
// -------------
// 2 space seperated strings, originalDNA and targetDNA

// Output format:
// --------------
// An integer, the minimum number of mutation operations


// Example 1:
// -----------
// Input:
// ACGT AGT

// Output:
// 1

// Explanation:
// Delete 'C': "ACGT" → "AGT"
// Only 1 mutation is needed.

// Example 2:
// ----------
// Input:
// GATTAC GCATGCU

// Output:
// 4

// Explanation:
// - Replace 'A' with 'C': "GATTAC" → "GCTTAC"
// - Replace 'T' with 'A': "GCTTAC" → "GCATAC"
// - Replace 'A' with 'G': "GCATAC" → "GCATGC"
// - Insert 'U' at the end: "GCATGC" → "GCATGCU"

// Thus, 4 mutations are needed.


import java.util.*;

public class compareDNA {
     // public static int solve(String input,String target){
    //     int m = input.length();
    //     int n = target.length();
    //     int dp[][] = new int[m+1][n+1];
    //     for(int i=0;i<=m;i++){
    //         dp[i][0] = i;
    //     }
    //     for(int j=0;j<=n;j++){
    //         dp[0][j] = j;
    //     }
    //     for(int i=1;i<=m;i++){
    //         for(int j=1;j<=n;j++){
    //             if(input.charAt(i-1)==target.charAt(j-1)){
    //                 dp[i][j] = dp[i-1][j-1];
    //             }
    //             else{
    //                 dp[i][j] = 1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
    //             }
    //         }
    //     }
    //     return dp[m][n];
    // }
    public static int memo(String input, String target, int i, int j, int[][] dp, int m, int n){
        if(i==m){
            return n-j;
        }
        if(j==n){
            return m-i;
        }

        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        if(input.charAt(i)==target.charAt(j)){
            return dp[i][j] = memo(input, target, i+1, j+1, dp, m, n);
        }
        else{
            int Replace = memo(input, target, i+1, j+1, dp, m, n);
            int Insert = memo(input, target, i+1, j, dp, m, n);
            int Delete = memo(input, target, i, j+1, dp, m, n);
            return dp[i][j] = 1 + Math.min(Replace,Math.min(Insert,Delete));
        }
    }
    public static int solve(String input,String target){
        int m = input.length();
        int n = target.length();
        int dp[][] = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            Arrays.fill(dp[i], -1);
        }
        return memo(input,target,0,0,dp,m,n);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String input = s.next();
        String target = s.next();
        System.out.println(solve(input,target));
    }
}
