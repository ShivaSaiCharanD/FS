// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.
  
// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.
 
 
// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5
 
// Sample Output-1:
// ----------------
// 8
 
// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4
 
// Sample Output-2:
// ----------------
// -1


import java.util.*;

public class minCost{
    public static int solve(List<int[]> arr,int n,int m){
        int sum = 0;
        DisJointSet set = new DisJointSet(n);
        for(int i=0;i<m;i++){
            if(set.union(arr.get(i)[0],arr.get(i)[1])){
                sum+=arr.get(i)[2];
            }
        }
        return set.count==1 ? sum:-1;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        List<int[]> arr = new ArrayList<>();
        for(int i=0;i<m;i++){
            arr.add(new int[]{s.nextInt(),s.nextInt(),s.nextInt()});
        }
        Collections.sort(arr,(a,b)->(a[2]-b[2]));
        System.out.println(solve(arr,n,m));
    }
}

class DisJointSet{
    int parent[];
    int count;
    DisJointSet(int n){
        parent = new int[n+1];
        count = n;
        for(int i=0;i<n+1;i++){
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
        if(rootX>rootY){
            parent[rootX] = rootY;
            count--;
        }
        else{
            parent[rootY] = rootX;
            count--;
        }
        return true;
    }
}