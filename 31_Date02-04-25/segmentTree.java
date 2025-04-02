// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6
// 1 0 7 
// 2 2 18
// 2 4 36
// 1 2 7


// Sample Output:  
// --------------
// 25
// 28
// 36


import java.util.*;

public class segmentTree{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = s.nextInt();
        }
        segTree tree = new segTree(n);
        tree.build(arr,1,0,n-1);
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<k;i++){
            int[] query = new int[]{s.nextInt(),s.nextInt(),s.nextInt()};
            if(query[0]==1){
                res.add(tree.max(1,0,n-1,query[1],query[2]));
            }
            else{
                tree.update(1,0,n-1,query[1],query[2]);
            }
        }
        System.out.println(res);
    }
}

class segTree{
    int[] t;
    segTree(int n){
        t = new int[4*n];
    }
    void build(int arr[],int v,int tl,int tr){
        if(tl==tr){
            t[v] = arr[tl];
        }
        else{
            int tm = (tl+tr)/2;
            build(arr,v*2,tl,tm);
            build(arr,v*2+1,tm+1,tr);
            t[v] = Math.max(t[v*2], t[v*2+1]);
        }
    }
    int max(int v,int tl,int tr,int l,int r){
        if(l>r){
            return 0;
        }
        if(l==tl && r==tr){
            return t[v];
        }
        int tm = (tl+tr)/2;
        return Math.max(max(v*2,tl,tm,l,Math.min(r,tm)),max(v*2+1,tm+1,tr,Math.max(l,tm+1),r));
    }
    void update(int v,int tl,int tr,int pos,int val){
        if(tl==tr){
            t[v] = val;
        }
        else{
            int tm = (tl+tr)/2;
            if(pos<=tm){
                update(v*2,tl,tm,pos,val);
            }
            else{
                update(v*2+1,tm+1,tr,pos,val);
            }
            t[v] = Math.max(t[v*2],t[v*2+1]);
        }
    }
}