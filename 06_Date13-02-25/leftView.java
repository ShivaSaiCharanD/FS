// A software development company is designing a smart home automation 
// system that uses sensor networks to monitor and control different devices 
// in a house. The sensors are organized in a hierarchical structure, where each 
// sensor node has a unique ID and can have up to two child nodes (left and right).

// The company wants to analyze the left-most sensors in the system to determine
// which ones are critical for detecting environmental changes. The hierarchy of 
// the sensors is provided as a level-order input, where missing sensors are 
// represented as -1.

// Your task is to build the sensor network as a binary tree and then determine 
// the left-most sensor IDs at each level.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the left-most sensor IDs at each level


// Sample Input-1:
// ---------------
// 1 2 3 4 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 2, 4]



// Sample Input-2:
// ---------------
// 3 2 4 1 5

// Sample Output-2:
// ----------------
// [3, 2, 1]

import java.util.*;

public class leftView{
    public static void leftSideView(Node root,int depth,List<Integer> res){
        if(root==null){
            return;
        }
        if(res.size()==depth){
            res.add(root.data);
        }
        leftSideView(root.left,depth+1,res);
        leftSideView(root.right,depth+1,res);
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
        leftSideView(tree.root,0,res);
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