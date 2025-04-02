// Babylonian working on numbers, got a task to do. 
// The task is, given two integers S, and X,
// and S is the sum of (N pow X), where N > 0, 
// 0 < S < 1000 and 0< X < =10.

// Please help Babylonian to find the number of ways to get S.

// Input Format:
// -------------
// Two integers S and X.

// Output Format:
// --------------
// Print an integer, the number of ways.

// Sample Input-1:
// ---------------
// 10 2

// Sample Output-1:
// ----------------
// 1

// Explanation:
// --------------
// given x=2,
// pow(1,2) + pow(3,2) = 1 + 9 = 10


// Sample Input-2:
// ---------------
// 100 2

// Sample Output-2:
// ----------------
// 3

// Explanation:
// --------------
// given x=2,
// pow(1,2) + pow(3,2) + pow(4,2) + pow(5,2) + pow(7,2) = 1 + 9 + 16 + 25 + 49 =100
// pow(6,2) + pow(8,2) = 36 + 64 = 100
// pow(10,2) = 100

// Sample Input-3:
// ---------------
// 8 2

// Sample Output-3:
// ----------------
// 0


// Sample Input-4:
// ---------------
// 8 3

// Sample Output-4:
// ----------------
// 1

// Explanation: pow(2,3) = 8

import java.util.*;

public class powerSumWays{
    // public static void solve(int n,int x,int[] res,int currSum,int max,int ind){
    //     if(currSum>n || ind>max){
    //         return;
    //     }
    //     if(currSum==n){
    //         res[0]++;
    //         return;
    //     }
    //     for(int i=ind;i<max;i++){
    //         solve(n,x,res,currSum+(int)Math.pow(i,x),max,i+1);
    //     }
    // }
    public static void solve(int n,int x,int[] res,int max,int currSum,int currNum){
        if(currSum>n || currNum>max){
            return;
        }

        if(currSum==n){
            res[0]++;
            return;
        }

        solve(n,x,res,max,currSum+(int)Math.pow(currNum,x),currNum+1);
        solve(n,x,res,max,currSum,currNum+1);

    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int x = s.nextInt();
        int[] res = new int[]{0};
        int max = (int)Math.sqrt(n)+1;
        solve(n,x,res,max,0,1);
        System.out.println(res[0]);
    }
}