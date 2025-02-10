// In a film festival, there is a lineup of movies, each with a rating. The festival 
// organizer wants to find the maximum total rating of 'k' sequence of movies while 
// following these rules:
//     1. The sequence must be exactly k movies long.
//     2. Each movie in the sequence must have a distinct rating.
//     3. None of the movies in the sequence should have a restricted rating, as 
//        these are reserved for special screenings.

// Given an array movieRatings representing the sequence of movie ratings, an integer k 
// representing the length of the sequence, and a set restrictedRatings (of size m) of 
// special ratings, find the maximum total rating of any valid sequence. 
// If no valid sequence exists, return -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers, n, k, m
// Line-2: n space separated integers, movieRatings[].
// Line-3: m space separated integers, restrictedRatings[].

// Output Format:
// -------------
// An integer, the maximum total rating of any valid sequence


// Sample Input-1:
// ---------------
// 7 3 2
// 1 5 4 2 9 9 9
// 2 9

// Sample Output-1:
// ----------------
// 10

// Explanation: 
// ------------
// The sequences of movie ratings with length 3 are:
// - [1, 5, 4] which meets the requirements and has a total rating of 10.
// - [5, 4, 2] which does not meet the requirements because 2 is in the 
// restricted set.
// - [4, 2, 9] which does not meet the requirements because 2 and 9 are in 
// the restricted set.
// - [2, 9, 9] which does not meet the requirements because 2 and 9 are in 
// the restricted set and 9 is repeated.
// - [9, 9, 9] which does not meet the requirements because 9 is in 
// the restricted set and repeated.

// We return 10 because it is the maximum total rating of all the sequences 
// that meet the conditions.


// Sample Input-2:
// ---------------
// 3 3 1
// 4 4 4
// 4

// Sample Output-2:
// ----------------
// -1

// Explanation: The sequences of movie ratings with length 3 are:
// [4, 4, 4] which does not meet the requirements because 4 is repeated and in the restricted set.
// We return -1 because no sequences meet the conditions.

// Constraints:
// ------------
// 0 <= m <= n <=1000
// k <= n
// 0 ≤ restrictedRatings.length ≤ 1000

import java.util.*;

public class MovieMarathon{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int rr = s.nextInt();
        int arr[] = new int[n];
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            arr[i]=s.nextInt();
        }
        for(int i=0;i<rr;i++){
            set.add(s.nextInt());
        }
        int max = 0;
        int i = 0;
        int j = 0;
        int rating = 0;
        Set<Integer> unq = new HashSet<>();
        while(j<n){
            if(set.contains(arr[j]) || unq.contains(arr[j])){
                // i=j;
                // j=j+1;
                // unq.clear();
                // continue;
                while (i < j) {
                    unq.remove(arr[i]);
                    rating -= arr[i];
                    i++;
                }
                i = j + 1;
                continue;

            }
            rating+=arr[j];
            unq.add(arr[j]);
            if(j-i+1==k){
                max = Math.max(rating,max);
                rating-=arr[i];
                unq.remove(arr[i]);
                i++;
            }
            j++;
        }
        if(max==0){
            max=-1;
        }
        System.out.println(max);
    }
}