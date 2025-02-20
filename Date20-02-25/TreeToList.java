// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.


// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6


// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6

import java.util.*;

public class TreeToList{
    public static Node solve(Node root){
        if(root==null || root.left==null){
            return root;
        }
        Node left = solve(root.left);
        Node right = solve(root.right);
        if(left!=null && right==null){
            root.right = root.left;
            root.left = null;
            return root;
        }
        if(left==null){
            return root;
        }
        root.right = left;
        root.left = null;
        Node rightMost = rightMost(root);
        rightMost.right = right;
        return root;
    }
    public static Node rightMost(Node root){
        if(root.right==null){
            return root;
        }
        return rightMost(root.right);
    }
    public static void print(Node root){
        Node temp = root;
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.right;
        }
    }
    public static void main(String[] args){
       Scanner s = new Scanner(System.in);
        String[] elements = s.nextLine().split(" ");
        int[] levelOrder = new int[elements.length];
        for(int i=0;i<elements.length;i++){
            levelOrder[i] = Integer.parseInt(elements[i]);
        }
        BinaryTree tree = new BinaryTree();
        tree.buildTree(levelOrder); 
        tree.root = solve(tree.root);
        print(tree.root);
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

