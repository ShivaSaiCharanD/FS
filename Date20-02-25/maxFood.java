// You are a bird living in a vast forest. Every day, you fly across different locations 
// to collect food and store it in various nests. However, you must return to your home 
// nest before sunset to rest safely.  

// Your objective is to collect as much food as possible within a given time limit 
// while following the forest rules:
// 1. Each food location contains only one unit of food.  
// 2. The bird can carry only one unit of food at a time.  
// 3. The bird must deposit food into a nest before collecting more.  
// 4. Distance Calculation: The time taken to fly between two locations using 
// the Euclidean distance formula:  d = sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
// 5. The total time spent is the sum of:  
//    - Travel time between locations.  
//    - Fixed time to deposit food in a nest (each nest has a different deposit 
//    time).  
// 6. The bird must return to the home nest before the total time limit.  

// Your goal is to determine the maximum number of food units that the bird
// can collect and store in different nests before sunset.  

// Input Format:
// -------------
// An integer representing the number of food locations.  
// An integer representing the number of nests.  
// A 2D array containing the coordinates of each food location as pairs (x, y) 
// A 2D array containing the coordinates of each nest as pairs (x, y).  
// A 1D array containing the starting coordinates (home nest) (x, y).  
// A floating-point number representing the total time available (before sunset).  

// Output Format:
// ---------------  
// The function must return an integer, representing the maximum number of 
// food units that can be collected and stored in nests within the given time.  

// Sample Input:
// --------------
// 2
// 2
// 3 3
// 4 6
// 5 5
// 6 1
// 1 4
// 13

// Sample Output:
// ---------------
// 2

// Explanation:
// ---------------
// The bird starts at (1,4).
// Moves to food location (3,3) (distance = sqrt(5)).
// Deposits food at a nest (5,5) (distance = sqrt(8)).
// Moves to food location (4,6) (distance = sqrt(2))
// and collects another unit of food.
// Deposits it at a nest (5,5) (distance = sqrt(2)). 
// Returns to the starting point (1,4) (distance = sqrt(17)).
// Total distance is: sqrt(5) + sqrt(8) + sqrt(2) + sqrt(2)+ sqrt(17) = 12.0160278526
// Since the total distance is within the allowed time (13), 
// the maximum food units collected are 2.

// Sample Input:
// --------------
// 4
// 1
// 3 3
// 5 7
// 7 8
// 8 4
// 7 7
// 1 5
// 22

// Sample Output:
// ---------------
// 3

import java.util.Scanner;

public class maxFood {
    public static double getTime(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static void solve(int locs, int nests, int[][] locPairs, int[][] nestPairs, int[] curr, double totalTime,
            boolean[] visitedLocs, int locInd, int nestInd, double currTime, int[] food, int state, int currFood,
            int[] starting) {
        double toHome = getTime(curr[0], starting[0], curr[1], starting[1]);
        if (currTime + toHome <= totalTime) {
            food[0] = Math.max(food[0], currFood);
        } else {
            return;
        }

        if (state == 0) {
            for (int i = 0; i < locs; i++) {
                if (visitedLocs[i])
                    continue;
                double travelTime = getTime(curr[0], locPairs[i][0], curr[1], locPairs[i][1]);
                if (currTime + travelTime + toHome > totalTime)
                    continue;
                visitedLocs[i] = true;
                solve(locs, nests, locPairs, nestPairs, locPairs[i], totalTime, visitedLocs, 0, 0,
                        currTime + travelTime, food, 1, currFood + 1, starting);
                visitedLocs[i] = false;
            }
        } else {
            for (int i = 0; i < nests; i++) {
                double depositTime = getTime(curr[0], nestPairs[i][0], curr[1], nestPairs[i][1]);
                if (currTime + depositTime + toHome > totalTime)
                    continue;
                solve(locs, nests, locPairs, nestPairs, nestPairs[i], totalTime, visitedLocs, 0, 0,
                        currTime + depositTime, food, 0, currFood, starting);
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int locs = s.nextInt();
        int nests = s.nextInt();
        int[][] locPairs = new int[locs][2];
        int[][] nestPairs = new int[nests][2];

        for (int i = 0; i < locs; i++) {
            locPairs[i] = new int[] { s.nextInt(), s.nextInt() };
        }
        for (int i = 0; i < nests; i++) {
            nestPairs[i] = new int[] { s.nextInt(), s.nextInt() };
        }
        int[] starting = new int[] { s.nextInt(), s.nextInt() };
        double totalTime = s.nextDouble();
        boolean[] visitedLocs = new boolean[locs];
        int[] food = new int[] { 0 };
        solve(locs, nests, locPairs, nestPairs, starting, totalTime, visitedLocs, 0, 0, 0, food, 0, 0, starting);
        System.out.println(food[0]);
    }
}
