// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.

import java.util.*;

public class spyNetwork{
    public static Node buildTree(String str,int[] ind){
        if(ind[0]==str.length()){
            return null;
        }
        int num = 0;
        int sign=1;
        if(ind[0]<str.length() && str.charAt(ind[0])=='-'){
            sign=-1;
            ind[0]++;
        }
        while(ind[0]<str.length() && Character.isDigit(str.charAt(ind[0]))){
            num = num*10 + Integer.parseInt(str.charAt(ind[0])+"");
            ind[0]++;
        }
        Node root = new Node(num*sign);
        if(ind[0]<str.length() && str.charAt(ind[0])=='('){
            ind[0]++;
            root.left = buildTree(str,ind);
        }
        if(ind[0]<str.length() && str.charAt(ind[0])=='('){
            ind[0]++;
            root.right = buildTree(str,ind);
        }
        ind[0]++;
        return root;
    }
    public static void printTree(Node root){
        if(root==null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            for(int i=0;i<n;i++){
                Node temp = q.poll();
                System.out.print(temp.data+" ");
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String str = s.next();
        int ind[] = new int[]{0};
        Node root = buildTree(str,ind);
        printTree(root);
    }
}
class Node{
    Node left;
    Node right;
    int data;
    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}