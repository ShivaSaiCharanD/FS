// Given an integer array nums of length n and an integer k, return the k-th smallest subarray
// sum. A subarray is defined as a non-empty contiguous sequence of elements in an array.
// A subarray sum is the sum of all elements in the subarray.
// Example 1:
// Input: nums = [2,1,3], k = 4
// Output: 3
// Explanation: The subarrays of [2,1,3] are:
//  [2] with sum 2
//  [1] with sum 1
//  [3] with sum 3
//  [2,1] with sum 3
//  [1,3] with sum 4
//  [2,1,3] with sum 6
// Ordering the sums from smallest to largest gives 1, 2, 3, 3, 4, 6. The 4th smallest is 3.
// Example 2:
// Input: nums = [3,3,5,5], k = 7
// Output: 10
// Explanation: The subarrays of [3,3,5,5] are:
//  [3] with sum 3
//  [3] with sum 3
//  [5] with sum 5
//  [5] with sum 5
//  [3,3] with sum 6
//  [3,5] with sum 8
//  [5,5] with sum 10
//  [3,3,5], with sum 11
//  [3,5,5] with sum 13
//  [3,3,5,5] with sum 16
// Ordering the sums from smallest to largest gives 3, 3, 5, 5, 6, 8, 10, 11, 13, 16. The 7th smallest is
// 10.

import java.util.*;

class KthSmallestSubarraySum {
    public static int solve(int[] nums, int k) {
        int left = Integer.MAX_VALUE, right = 0;
        for (int num : nums) {
            left = Math.min(left, num);
            right += num;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countSum(nums, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public static int countSum(int[] nums, int s) {
        int curr = 0, j = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];
            while (curr > s) {
                curr -= nums[j++];
            }
            count += i - j + 1;
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int array[] = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = s.nextInt();
        }
        int k = s.nextInt();
        System.out.println(solve(array, k));
    }
}
