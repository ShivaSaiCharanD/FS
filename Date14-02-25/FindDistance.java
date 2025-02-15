// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.


import java.util.*;

public class FindDistance {
    public static Node lca(Node root,Node p,Node q){
        if(root==null || root.data==p.data || root.data==q.data) return root;
        Node left = lca(root.left,p,q);
        Node right = lca(root.right,p,q);
        if(left!=null && right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        return right;
    }
    public static int dis(Node root,int a,int depth){
        if(root==null){
            return -1;
        }
        if(root.data==a){
            return depth;
        }
        int leftV = dis(root.left,a,depth+1);
        int rightV = dis(root.right,a,depth+1);
        return Math.max(leftV,rightV);
    }
    public static int solve(Node root,Node p,Node q){
        if(root==null) return 0;
        Node common = lca(root,p,q);
        return dis(common,p.data,0) + dis(common,q.data,0);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] eles = s.nextLine().split(" ");
        String[] target = s.nextLine().split(" ");
        Node a = new Node(Integer.parseInt(target[0]));
        Node b = new Node(Integer.parseInt(target[1]));
        int n = eles.length;
        int[] levelOrder = new int[n];
        for(int i=0;i<n;i++){
            levelOrder[i] = Integer.parseInt(eles[i]);
        }
        BinaryTree tree = new BinaryTree();
        tree.buildTree(levelOrder);
        int res = solve(tree.root,a,b);
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