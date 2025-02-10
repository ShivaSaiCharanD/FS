// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7


import java.util.*;

public class Solution{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left=null;
            this.right=null;
        }
    }
    
    class BinaryTree{
        Node root;
        BinaryTree(int val){
            this.root = new Node(val);
        }
        void insert(int val){
            if(root==null){
                root = new Node(val);
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()){
                Node temp = q.poll();
                if(temp.data==-1){
                    continue;
                }
                if(temp.left==null){
                    temp.left = new Node(val);
                    break;
                }
                else{
                    q.add(temp.left);
                }
                if(temp.right==null){
                    temp.right = new Node(val);
                    break;
                }
                else{
                    q.add(temp.right);
                }
            }
        }
        void inOrder(Node root){
            if(root==null){
                return;
            }
            inOrder(root.left);
            if(root.data!=-1){
                System.out.print(root.data+" ");
            }
            inOrder(root.right);
        }
    }
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] arr = s.nextLine().split(" ");
        Solution solution = new Solution();
        BinaryTree tree = solution.new BinaryTree(Integer.parseInt(arr[0]));
        for(int i=1;i<arr.length;i++){
            tree.insert(Integer.parseInt(arr[i]));
        }
        tree.inOrder(tree.root);
    }
}
