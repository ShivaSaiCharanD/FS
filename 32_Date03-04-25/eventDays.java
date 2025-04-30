/*
 * // In Hyderabad after a long pandemic gap, the Telangana Youth festival is 
// Organized at HITEX. In HITEX, there are a lot of programs planned. During 
// the festival in order to maintain the rules of Pandemic, they put a 
// constraint that one person can only attend any one of the programs in 
// one day according to planned days.

// Now it’s your aim to implement the "Solution" class in such a way that 
// you need to return the maximum number of programs you can attend according 
// to given constraints.

// Explanation:
// You have a list of programs 'p' and days 'd', where you can attend only 
// one program on one day. Programs [p] = [first day, last day], 
// p is the program's first day and the last day.


// Input Format:
// -------------
// Line-1: An integer N, number of programs.
// Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

// Output Format:
// --------------
// An integer, the maximum number of programs you can attend.


// Sample Input-1:
// ---------------
// 4
// 1 2,2 4,2 3,2 2

// Sample Output-1:
// ----------------
// 4

// Sample Input-2:
// ---------------
// 6
// 1 5,2 3,2 4,2 2,3 4,3 5

// Sample Output-2:
// ----------------
// 5
 */

import java.util.*;

public class eventDays {
    public static int solve(int n, int[][] days) {
        Arrays.sort(days, (a, b) -> Integer.compare(a[1], b[1]));
        
        TreeSet<Integer> availableDays = new TreeSet<>();
        int maxDay = 0;
        for (int[] program : days) {
            maxDay = Math.max(maxDay, program[1]);
        }
        for (int day = 1; day <= maxDay; day++) {
            availableDays.add(day);
        }
        
        int count = 0;
        for (int[] program : days) {
            int start = program[0];
            int end = program[1];
            Integer chosenDay = availableDays.floor(end);
            if (chosenDay != null && chosenDay >= start) {
                count++;
                availableDays.remove(chosenDay);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        String[] str = s.nextLine().split(",");
        int[][] days = new int[n][2];

        int ind = 0;
        for (String i : str) {
            String[] temp = i.split(" ");
            days[ind][0] = Integer.parseInt(temp[0]);
            days[ind][1] = Integer.parseInt(temp[1]);
            ind++;
        } 
        System.out.println(solve(n, days));
    }
}

class segTree {
    int[] t;

    segTree(int n) {
        t = new int[4 * n];
    }

    void build(int arr[], int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = arr[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(arr, v * 2, tl, tm);
            build(arr, v * 2 + 1, tm + 1, tr);
            t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
        }
    }

    int min(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return Integer.MAX_VALUE;
        }
        if (l == tl && r == tr) {
            return t[v];
        }
        int tm = (tl + tr) / 2;
        return Math.min(min(v * 2, tl, tm, l, Math.min(r, tm)),min(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r));
    }

    void update(int v, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            t[v] = val;
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) {
                update(v * 2, tl, tm, pos, val);
            } else {
                update(v * 2 + 1, tm + 1, tr, pos, val);
            }
            t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
        }
    }
}
