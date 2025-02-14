// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)

import java.util.*;

public class InPre_SpiralOrder{
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
    static int preOrderInd = 0;
    public static Node solve(int n,List<Integer> inorder,List<Integer> preorder,int l,int r){
        if(l>r) return null;
        Node root = new Node(preorder.get(preOrderInd++));
        int ind = inorder.indexOf(root.data);
        root.left = solve(n,inorder,preorder,l,ind-1);
        root.right = solve(n,inorder,preorder,ind+1,r);
        return root;
    }
    public static void printLevels(Node root, int l,int r){
        int level = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> pos = new ArrayList<>();
            for(int i=0;i<n;i++){
                Node temp = q.poll();
                if(level<= r && level>=l){
                    // System.out.print(temp.data+" ");
                    pos.add(temp.data);
                }
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
            }
            if(level%2==0){
                Collections.reverse(pos);
            }
            for(int i:pos){
                System.out.print(i+" ");
            }
            level++;
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> preorder = new ArrayList<>();
        for(int i=0;i<n;i++){
            inorder.add(s.nextInt());
        }
        for(int i=0;i<n;i++){
            preorder.add(s.nextInt());
        }
        int l = s.nextInt();
        int r = s.nextInt();
        Node root = solve(n,inorder,preorder,0,n-1);
        printLevels(root,l,r);
        return;
    }
}