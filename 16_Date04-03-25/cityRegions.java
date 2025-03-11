// There are N cities, and M routes[], each route is a path between two cities.
// routes[i] = [city1, city2], there is a travel route between city1 and city2.
// Each city is numbered from 0 to N-1.
 
// There are one or more Regions formed among N cities. 
// A Region is formed in such way that you can travel between any two cities 
// in the region that are connected directly and indirectly.
 
// Your task is to findout the number of regions formed between N cities. 
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and M, number of cities and routes
// Next M lines: Two space separated integers city1, city2.
 
// Output Format:
// --------------
// Print an integer, number of regions formed.
 
 
// Sample Input-1:
// ---------------
// 5 4
// 0 1
// 0 2
// 1 2
// 3 4

// Sample Output-1:
// ----------------
// 2
 
 
// Sample Input-2:
// ---------------
// 5 6
// 0 1
// 0 2
// 2 3
// 1 2
// 1 4
// 2 4
 
// Sample Output-2:
// ----------------
// 1
 
// Note: Look HINT for explanation.

import java.util.*;

public class cityRegions{
    public static int solve(int[][] arr,int n,int m){
        Set<Integer> res = new HashSet<Integer>();
        DisJointSet set = new DisJointSet(n);
        for(int i=0;i<m;i++){
            set.union(arr[i][0],arr[i][1]);
        }
        for(int i=0;i<m;i++){
            res.add(set.find(arr[i][0]));
        }
        return res.size();
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int[][] arr = new int[m][2];
        for(int i=0;i<m;i++){
            arr[i][0] = s.nextInt();
            arr[i][1] = s.nextInt();
        }
        System.out.println(solve(arr,n,m));
    }
}

class DisJointSet{
    int[] parent;
    int[] rank;
    DisJointSet(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }
    int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    boolean union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX==rootY) return false;
        if(rank[rootX]<rank[rootY]){
            parent[rootX] = rootY;
        }
        else if(rank[rootX]>rank[rootY]){
            parent[rootY] = rootX;
        }
        else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}