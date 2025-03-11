// Imagine you are navigating a maze where each path you take has a section with a 
// code. The maze is structured as a series of interconnected rooms, 
// represented as a tree structure. Each room in the maze has a code (integral value)
// associated with it, and you are trying to check if a given sequence of code 
// matches a valid path from the entrance to an exit. 

// You are provide with the maze structure, where you have to build the maze and then,
// you are provided with a series of space seperated digits, representing a journey 
// starting from the entrance and passing through the rooms along the way. 
// The task is to verify whether the path corresponding to this array of codes 
// exists in the maze.

// Example 1:
// Input:
// 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
// 0 1 0 1                         //path to verify

// Output: true

// Explanation:
//                0
//              /   \
//             1     0
//            / \    /
//           0   1  0
//            \  / \
//             1 0  0

// The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
// Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


// Example 2:
// Input:
// 1 2 3
// 1 2 3

// output: false

// Explanation:
// The proposed path 1 → 2 → 3 does not exist in the maze, 
// so it cannot be a valid path.

import java.util.*;

public class validPath{
    public static boolean solve(Node root, String target, StringBuilder str) {
        if (root == null) return false;
        int len = str.length();
        str.append(root.data).append(" "); 
        if (root.left == null && root.right == null) {
            if (str.toString().trim().equals(target)) {
                return true;
            }
        }
        boolean left = solve(root.left, target, str);
        if(left){
            return true;
        }
        boolean right = solve(root.right, target, str);
        str.setLength(len);
        return right;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] elements = s.nextLine().split(" ");
        int[] levelOrder = new int[elements.length];
        for(int i=0;i<elements.length;i++){
            levelOrder[i] = Integer.parseInt(elements[i]);
        }
        String target = s.next();
        BinaryTree tree = new BinaryTree();
        tree.buildTree(levelOrder);
        StringBuilder str = new StringBuilder();
        System.out.println(solve(tree.root,target,str));
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
