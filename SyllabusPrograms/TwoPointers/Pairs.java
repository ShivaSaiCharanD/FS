// Two brothers want to play a game, 
// The rules of the game are: one player gives two sorted lists of 
// numerical elements and a number (sum). 
// The opponent has to find the closest pair of elements 
// to the given sum.
// -> pair consists of elements from each list

// Please help those brothers to develop a program, that takes 
// two sorted lists as input and return a pair as output.

// Input Format:
// -------------
// size of list_1
// list_1 values
// size of list_2
// list_2 values
// closest number

// Output Format:
// --------------
// comma-separated pair

// Sample Input-1:
// ---------------
// 4
// 1 4 5 7
// 4
// 10 20 30 40
// 32
// Sample Output-1
// ---------------
// 1, 30

// Sample Input-2
// ---------------
// 3
// 2 4 6
// 4
// 5 7 11 13
// 15
// sample output-2
// ---------------
// 2, 13


import java.util.*;

public class Pairs {
     public static void solve(int[] arr1,int[] arr2,int tar,int[] res){
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = arr2.length-1;
        while(i<arr1.length && j>=0){
            int sum = arr1[i]+arr2[j];
            if(min>Math.abs(sum-tar)){
                res[0] = arr1[i];
                res[1] = arr2[j];
                min = Math.abs(sum-tar);
            }
            if(sum>tar){
                j--;
            }
            else{
                i++;
            }
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n1 = s.nextInt();
        int arr1[] = new int[n1];
        for(int i=0;i<n1;i++){
            arr1[i] = s.nextInt();
        }
        int n2 = s.nextInt();
        int arr2[] = new int[n2];
        for(int i=0;i<n2;i++){
            arr2[i] = s.nextInt();
        }
        int tar = s.nextInt();
        int res[] = new int[2];
        solve(arr1,arr2,tar,res);
        System.out.println(res[0]+", "+res[1]);
    }
}