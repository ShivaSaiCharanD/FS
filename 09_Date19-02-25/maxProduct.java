// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

import java.util.*;

public class maxProduct{
    // public static int calSum(Node root){
    //     if(root==null){
    //         return 0;
    //     }
    //     return root.data+calSum(root.left)+calSum(root.right);
    // }
    // public static void solve(Node root,int[] max,int sum){
    //     if(root==null){
    //         return;
    //     }
    //     int left = calSum(root.left);
    //     int right = calSum(root.right);
    //     max[0] = Math.max(Math.max((left+root.data+sum)*right,(right+root.data+sum)*left),max[0]);
    //     solve(root.left,max,root.data+sum+right);
    //     solve(root.right,max,root.data+sum+left);
    // }
    public static Set<Integer> set = new HashSet<>();
    public static int calSum(Node root){
        if(root==null){
            return 0;
        }
        int left = calSum(root.left);
        set.add(left);
        int right = calSum(root.right);
        set.add(right);
        return root.data+left+right;
    }
    public static int solve(Node root){
        int sum = calSum(root);
        int max = -1;
        for(int i:set){
            max = Math.max(max,(sum-i)*i);
        }
        return max%1000000007;
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
        // int max[] = new int[]{-1};
        // solve(tree.root,max,0);
        // System.out.println(max[0]);
        System.out.println(solve(tree.root));
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