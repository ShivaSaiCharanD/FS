// Imagine you're the chief curator at a renowned museum that houses a rare collection 
// of ancient artifacts. These artifacts are arranged in a special display that 
// follows a strict rule: any artifact positioned to the left of another has a lower 
// value, and any artifact on the right has a higher value. 

// Your task is to transform this display into what is known as a "Greater Artifact 
// Display." In this new arrangement, each artifact’s new value will be its original 
// value plus the sum of the values of all artifacts that are more valuable than it.

// Example 1:
// ----------
// input=
// 4 2 6 1 3 5 7
// output=
// 22 27 13 28 25 18 7

// Explanation:
// Input structure 
//        4
//       / \
//      2   6
//     / \ / \
//    1  3 5  7

// Output structure:
//         22
//       /   \
//      27   13
//     / \   / \
//    28 25 18  7

// Reverse updates:
// - Artifact 7: 7
// - Artifact 6: 6 + 7 = 13
// - Artifact 5: 5 + 13 = 18
// - Artifact 4: 4 + 18 = 22
// - Artifact 3: 3 + 22 = 25
// - Artifact 2: 2 + 25 = 27
// - Artifact 1: 1 + 27 = 28


import java.util.*;

public class reverseSum {
    public static void solve(Node root,int[] sum){
        if(root==null){
            return;
        }
        solve(root.right,sum);
        root.data+=sum[0];
        sum[0] = root.data;
        solve(root.left,sum);
    }
    public static void levelOrder(Node root){
        if(root==null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            for(int i=0;i<n;i++){
                Node temp = q.poll();
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
                System.out.print(temp.data+" ");
            }
        }
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
        solve(tree.root,new int[]{0});
        levelOrder(tree.root);
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

