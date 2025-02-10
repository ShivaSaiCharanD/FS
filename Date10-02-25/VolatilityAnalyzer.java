// You are given an integer array nums and two integers l and r. Your task is to 
// analyze the volatility of a sequence of values. The volatility of a sequence is 
// defined as the difference between the maximum and minimum values in that sequence.

// You need to determine the sequence with the highest volatility among all 
// sequences of length between l and r (inclusive).

// Return the highest volatility. If no such sequence exists, return -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers, n, l, r
// Line-2: n space separated integers, nums[].

// Output Format:
// -------------
// An integer, the highest volatility.


// Sample Input-1:
// ---------------
// 5 2 3
// 8 3 1 6 2

// Sample Output-1:
// ----------------
// 7

// Explanation:
// ------------
// The possible sequences of length between l = 2 and r = 3 are:

// [8, 3] with a volatility of 8−3=5
// [3, 1] with a volatility of 3−1=2
// [1, 6] with a volatility of 6−1=5
// [8, 3, 1] with a volatility of 8−1=7
// The sequence [8, 3, 1] has the highest volatility of 7.

// Sample Input-2:
// ---------------
// 4 2 4
// 5 5 5 5

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// All possible sequences have no volatility as the maximum and minimum values 
// are the same, resulting in a difference of 0.
 
import java.util.*;

public class VolatilityAnalyzer{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int l = s.nextInt();
        int r = s.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = s.nextInt();
        }
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        int i=0;
        int j=0;
        // int low = Integer.MAX_VALUE;
        // int high = -1;
        int max = Integer.MIN_VALUE;
        while(j<n){
            maxQ.add(arr[j]);
            minQ.add(arr[j]);
            // low = Math.min(low,arr[j]);
            // high = Math.max(high, arr[j]);
            if(j-i+1>r){
                if(!maxQ.isEmpty() && arr[i]==maxQ.peek()){
                    maxQ.poll();
                }
                if(!minQ.isEmpty() && arr[i]==minQ.peek()){
                    minQ.poll();
                }
                // high = Math.max(high,arr[i]);
                // low = Math.min(low,arr[i]);
                i++;
            }
            if(((j-i+1)==r && !maxQ.isEmpty() && !minQ.isEmpty())){
                max = Math.max(max,(maxQ.peek()-minQ.peek()));
            }
            // if((j-i+1)==r){
            //     max = Math.max(max,high-low);
            // }
            j++;
        }
        if(max==Integer.MIN_VALUE){
            max=-1;
        }
        System.out.println(max);
    }
}