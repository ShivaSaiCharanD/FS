// Imagine you're playing an adventure video game with a total of 'n' quests, numbered 
// from 0 to n–1. Some quests have special unlock conditions: certain quests must be 
// completed before others can be attempted. You’re provided with 'm' questDependencies 
// where each element questDependencies[i] = [ai, bi] means that you must finish 
// quest ai before you can start quest bi.

// For instance, the dependency [0, 1] implies that quest 0 must be cleared before 
// quest 1 becomes available. These requirements can also be indirect; if quest 'a' 
// unlocks quest 'b', and quest 'b' unlocks quest 'c', then quest 'a' is considered 
// an indirect prerequisite for quest 'c'.

// In addition, you receive an array called inquiries of size 'k' where each inquiry 
// inquiries[j] = [uj, vj] asks whether quest uj is a necessary precursor to quest vj. 
// Your task is to return a boolean array result where each result[j] answers the 
// jth inquiry.

// Input format:
// -------------
// Line 1: Three space separated integers, representing n, m and k
// Line 2: next m lines of dependencies
// Line 3: next k lines of queries

// Output format:
// --------------
// Resultant boolean array.

// Example 1:
// ----------
// Input=
// 2 1 2
// 1 0
// 0 1
// 1 0
  
// Output= 
// [false, true]  

// Explanation: The dependency [1, 0] indicates that you must complete quest 1 
// before attempting quest 0. Hence, quest 0 is not a prerequisite for quest 1, 
// but quest 1 is for quest 0.

// Example 2:
// ----------
// Input=
// 2 0 2
// 1 0
// 0 1

// Output=
// [false, false]
  
// Explanation: With no dependencies, each quest stands alone.

// Example 3:
// ----------
// Input=
// 3 2 2
// 1 2
// 2 0
// 1 0
// 1 2

// Output=
// [true, true]


// Constraints:
// • 2 <= n <= 100  
// • 0 <= m <= (n * (n - 1) / 2)  
// • Each questDependencies[i] contains exactly 2 elements  
// • 0 <= ai, bi <= n - 1, with ai!= bi  
// • All pairs [ai, bi] are unique  
// • The dependency structure does not contain cycles  
// • 1 <= k <= 10^4 
// • 0 <= uj, vj <= numMissions - 1

import java.util.*;

public class checkPath{
    public static boolean solve(Map<Integer,List<Integer>> adjList,int[] queries){
        if(adjList.get(queries[0]).contains(queries[1])) return true;
        for(int i:adjList.get(queries[0])){
            if(solve(adjList,new int[]{i,queries[1]})==true) return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int k = s.nextInt();
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0;i<n;i++){
            adjList.put(i,new ArrayList<>());
        }
        Map<Integer,Integer> inDegree = new HashMap<>();
        for(int i=0;i<m;i++){
            int a = s.nextInt();
            int b = s.nextInt();
            adjList.get(a).add(b);
        }
        int queries[][] = new int[k][2];
        for(int i=0;i<k;i++){
            queries[i][0] = s.nextInt();
            queries[i][1] = s.nextInt();
        }
        List<Boolean> res = new ArrayList<>();
        for(int i=0;i<k;i++){
            res.add(solve(adjList,queries[i]));
        }
        System.out.println(res);
    }
}