// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

import java.util.*;

public class BinaryTree{
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    static int postOrderInd = 0;
    public static Node solve(int n, List<Integer> inorder, List<Integer> postorder,int l,int r){
        if(l>r) return null;
        Node root = new Node(postorder.get(postOrderInd--));
        int ind = inorder.indexOf(root.data);
        root.right = solve(n,inorder,postorder,ind+1,r);
        root.left = solve(n,inorder,postorder,l,ind-1);
        return root;
    }
    public static void printLevels(Node root,int[][] queries){
        List<List<Integer>> totalLevels = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> res = new ArrayList<>();
            for(int i=0;i<n;i++){
                Node temp = q.poll();
                res.add(temp.data);
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
            }
            totalLevels.add(res);
        }
        for(int i[] : queries){
            int l = i[0];
            int r = i[1];
            List<Integer> sol = new ArrayList<>();
            for(int j=l;j<=r;j++){
                sol.addAll(totalLevels.get(j-1));
            }
            System.out.println(sol);   
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();
        for(int i=0;i<n;i++){
            inorder.add(s.nextInt());
        }
        for(int i=0;i<n;i++){
            postorder.add(s.nextInt());
        }
        int q = s.nextInt();
        int queries[][] = new int[q][2];
        for(int i=0;i<q;i++){
            queries[i][0] = s.nextInt();
            queries[i][1] = s.nextInt();
        }
        postOrderInd = n-1;
        Node root = solve(n,inorder,postorder,0,n-1);
        printLevels(root,queries);
        return;
    }
}