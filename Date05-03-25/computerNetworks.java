// There are N computers in a network, all the computers are connected as tree 
// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.


// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.


// Sample Input-1:
// ---------------
// 6
// 1 2
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// Sample Output-1:
// ---------------
// 5 6


// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4

// Sample Output-2:
// ---------------
// 2 4

import java.util.*;

public class computerNetworks{
    public static void solve(int n,int[][] arr,int[] res){
        DisJointSet set = new DisJointSet(arr);
        for(int i=0;i<n;i++){
            set.union(arr[i][0],arr[i][1]);
        }
        System.out.println(set.red[0]+" "+set.red[1]);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[][] = new int[n][n];
        for(int i=0;i<n;i++){
            arr[i][0] = s.nextInt();
            arr[i][1] = s.nextInt();
        }
        int res[] = new int[]{-1,-1};
        solve(n,arr,res);
    }
}
class DisJointSet{
     Map<Integer,Integer>parent;
     int red[];
    DisJointSet(int[][] arr){
        red = new int[2];
        parent = new HashMap<>();
        for(int i[]:arr){
            parent.put(i[0],i[0]);
            parent.put(i[1],i[1]);
        }
    }
    int find(int x){
        if(parent.get(x)!=x){
            parent.put(x,find(parent.get(x)));
        }
        return parent.get(x);
    }
    boolean union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX==rootY){
            red[0] = x;
            red[1] = y;
            return false;
        };
        if(rootY>rootX){
            parent.put(rootY,parent.get(rootX));
        }
        else{
            parent.put(rootX,parent.get(rootY));
        }
        return true;
    }
}