// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

import java.util.*;

public class verticalTransfer{
    public static void solve(Node root,Map<Integer,List<Integer>> map,int curr){
        if(root==null){
            return;
        }
        Queue<Pair> q = new LinkedList<>();
        int col = 0;
        q.add(new Pair(root,col));
        while(!q.isEmpty()){
            Pair temp = q.poll();
            Node currRoot = temp.currNode;
            int currVal = temp.col;
            if(map.get(currVal)==null){
                map.put(currVal,new ArrayList<>());
            }
            map.get(currVal).add(currRoot.data);
            if(currRoot.left!=null){
                q.add(new Pair(currRoot.left,currVal-1));
            }
            if(currRoot.right!=null){
                q.add(new Pair(currRoot.right,currVal+1));
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
        BinaryTree tree = new BinaryTree();
        tree.buildTree(levelOrder);
        Map<Integer,List<Integer>> map = new TreeMap<>();
        solve(tree.root,map,0);
        System.out.println(map.values());
    }
}

class Pair{
    Node currNode;
    int col;
    Pair(Node root,int col){
        this.currNode = root;
        this.col = col;
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