// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true


import java.util.*;

public class consistentRelations{
    public static boolean solve(String[] relations,int n){
        DisJointSet set = new DisJointSet();
        for(String i : relations){
            if(i.charAt(1)=='='){
                set.union(i.charAt(0)-97,i.charAt(3)-97);
            }
        }
        for(String i : relations){
            if(i.charAt(1)=='!'){
                int rootX = set.find(i.charAt(0)-97);
                int rootY = set.find(i.charAt(3)-97);
                if(rootX==rootY) return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] relations = s.nextLine().split(" ");
        int n = relations.length;
        System.out.println(solve(relations,n));
    }
}
class DisJointSet{
    int parent[];
    int rank[];
    int n = 26;
    DisJointSet(){
        parent = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            rank[i] = i;
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
        if(rank[rootX]>rank[rootY]){
            parent[rootX] = rootY;
        }
        else{
            parent[rootY] = rootX;
        }
        return true;
    }
}