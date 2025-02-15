// Mr. Rakesh is interested in working with Data Structures.

// He has constructed a Binary Tree (BT) and asked his friend 
// Anil to check whether the BT is a self-mirror tree or not.

// Can you help Rakesh determine whether the given BT is a self-mirror tree?
// Return true if it is a self-mirror tree; otherwise, return false.

// Note:
// ------
// In the tree, '-1' indicates an empty (null) node.

// Input Format:
// -------------
// A single line of space separated integers, values at the treenode

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 2 1 1 2 3 3 2

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 2 1 1 -1 3 -1 3

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class Symmetric{
    public static boolean symetric(Node root){
        if(root==null) return true;
        return helper(root.left,root.right);
    }
    public static boolean helper(Node left,Node right){
        if(left==null && right==null) return true;
        if(left==null || right==null) return false;
        return left.data==right.data && helper(left.left,right.right) && helper(left.right,right.left);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] eles = s.nextLine().split(" ");
        int n = eles.length;
        int[] levelOrder = new int[n];
        for(int i=0;i<n;i++){
            levelOrder[i] = Integer.parseInt(eles[i]);
        }
        BinaryTree tree = new BinaryTree();
        tree.buildTree(levelOrder);
        System.out.println(symetric(tree.root));
    }
}

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}

class BinaryTree{
    Node root;
    public void buildTree(int[] levelorder){
        if(levelorder.length == 0 || levelorder[0]==-1) return;
        root = new Node(levelorder[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int ind = 1;
        while(!q.isEmpty() && ind<levelorder.length){
            Node temp = q.poll();
            if(ind<levelorder.length && levelorder[ind]!=-1){
                temp.left = new Node(levelorder[ind]);
                q.add(temp.left);
            }
            ind++;
            if(ind<levelorder.length && levelorder[ind]!=-1){
                temp.right = new Node(levelorder[ind]);
                q.add(temp.right);
            }
            ind++;
        }
    }
}