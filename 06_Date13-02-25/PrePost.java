// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

import java.util.*;

public class PrePost {
    public static Node buildTree(int[] preorder,int[] postorder,int[] currentInd,int l,int r,Map<Integer,Integer> intToind){
        if(currentInd[0]>=preorder.length || l>r) return null;
        Node root = new Node(preorder[currentInd[0]++]);
        if(currentInd[0]==preorder.length){
            return root;
        }
        int nextInd =  intToind.get(preorder[currentInd[0]]);
        if(nextInd<=r){
            root.left = buildTree(preorder, postorder, currentInd, l, nextInd, intToind);
            root.right = buildTree(preorder, postorder, currentInd, nextInd+1, r-1, intToind);
        }
        return root;
    }
    public static List<List<Integer>> printLevel(Node root){
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> mini = new ArrayList<>();
            for(int i=0;i<n;i++){
                Node temp = q.poll();
                mini.add(temp.data);
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
            }
            res.add(mini);
        }
        return res;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] preOrderStr = s.nextLine().split(" ");
        String[] postOrderStr = s.nextLine().split(" ");
        int n = preOrderStr.length;
        int[] preorder = new int[n];
        int[] postorder = new int[n];
        for(int i=0;i<n;i++){
            preorder[i] = Integer.parseInt(preOrderStr[i]);
        }
        for(int i=0;i<n;i++){
            postorder[i] = Integer.parseInt(postOrderStr[i]);
        }
        Map<Integer,Integer> intToInd = new HashMap<>();
        for(int i=0;i<n;i++){
            intToInd.put(postorder[i],i);
        }
        Node root = buildTree(preorder,postorder,new int[]{0},0,n-1,intToInd);
        System.out.println(printLevel(root));
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
