// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]

import java.util.*;

public class rightSideView{
    public static void rightView(Node root,int depth,List<Integer> res){
        if(root==null){
            return;
        }
        if(res.size()==depth){
            res.add(root.data);
        }
        rightView(root.right,depth+1,res);
        rightView(root.left,depth+1,res);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] elements = s.nextLine().split(" ");
        int[] levelOrder = new int[elements.length];
        for(int i=0;i<elements.length;i++){
            levelOrder[i] = Integer.parseInt(elements[i]);
        }
        List<Integer> res = new ArrayList<>();
        BinaryTree tree = new BinaryTree();
        tree.buildTree(levelOrder);
        rightView(tree.root,0,res);
        System.out.println(res);
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
