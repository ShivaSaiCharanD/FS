// Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
// spectacular dinner consisting of numDishes unique dishes, labeled from 
// 0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
// only be prepared after completing others. You’re given a list called dependecies, 
// where each element dependencies[i] = [Xi, Yi] means that you must finish 
// preparing dish Yi before starting dish Xi.

// For instance, the pair [2, 1] implies that to create dish 2, 
// dish 1 must be prepared first.

// Return the ordering of dishes that a chef should take to finish all dishes.
// 	- the result set should follow the given order of conditions.
// 	- If it is impossible to complete all dishes, return an empty set.


// Input Format:
// -------------
// Line-1: An integer numDishes, number of Dishes.
// Line-2: An integer m, number of dependencies.
// Next m lines: Two space separated integers, Xi and Yi.

// Output Format:
// --------------
// Return a list of integers, the ordering of dishes that a chef should finish.

// Example 1:
// ------------
// Input=
// 4
// 3
// 1 2
// 3 0
// 0 1
// Output=
// [2, 1, 0, 3]


// Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
// dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


// Example 2:
// ----------
// Input=
// 2
// 2
// 1 0
// 0 1
// Output=
// []

// Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
// on dish 1. This circular dependency makes it impossible to prepare all dishes.

// Constraints:

// - 1 <= numDishes <= 2000  
// - 0 <= m <= 5000  
// - dependencies[i].length == 2  
// - 0 <= Xi, Yi < numDishes  
// - All the dependency pairs are unique.


import java.util.*;

public class Solution{
    public static void dfs(int i,boolean visited[], Map<Integer,List<Integer>> adjList,Stack<Integer> st){
        if(visited[i]){
            return;
        }
        visited[i] = true;
        for(int j:adjList.get(i)){
            if(!visited[j]){
                dfs(j,visited,adjList,st);
            }
        }
        st.add(i);
    }
    // public static List<Integer> solve(int n,Map<Integer,List<Integer>> adjList){
    //     List<Integer> res = new ArrayList<>();
    //     Stack<Integer> st = new Stack<>();
    //     boolean visited[] = new boolean[n];
    //     for(int i=0;i<n;i++){
    //         if(!visited[i]){
    //             dfs(i,visited,adjList,st);
    //         }
    //     }
    //     while(!st.empty()){
    //         res.add(st.pop());
    //     }
    //     return res;
    // }
    public static List<Integer> solve(int n,Map<Integer,List<Integer>> adjList,Map<Integer,Integer> inDegree){
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q  = new LinkedList<>();
        boolean visited[] = new boolean[n];
        for(int i=0;i<n;i++){
            if(!inDegree.containsKey(i)){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int top = q.poll();
            visited[top] = true;
            res.add(top);
            for(int i:adjList.get(top)){
                if(!visited[i]){
                    inDegree.put(i,inDegree.get(i)-1);
                    if(inDegree.get(i)==0){
                        q.add(i);
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0;i<n;i++){
            adjList.put(i,new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int a = s.nextInt();
            int b = s.nextInt();
            adjList.get(b).add(a);
        }
        System.out.println(solve(n,adjList));
    }
}
