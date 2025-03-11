// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11

import java.util.*;

public class InLevel {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> levelorder = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inorder.add(s.nextInt());
        }
        for (int i = 0; i < n; i++) {
            levelorder.add(s.nextInt());
        }        
        
        Node root = buildTree(inorder, levelorder);
        int[] result = depth(root);
        System.out.println(result[0] + " " + result[1]);
    }

    public static Node buildTree(List<Integer> inorder, List<Integer> levelorder) {
        if (inorder.size() == 0 || levelorder.size() == 0) return null;
        
        int n = inorder.size();
        Map<Integer, Integer> intToInd = new HashMap<>();
        for (int i = 0; i < n; i++) {
            intToInd.put(inorder.get(i), i);
        }

        Node root = new Node(levelorder.get(0));
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0, n - 1));
        int index = 1;

        while (!q.isEmpty() && index < n) {
            Pair current = q.poll();
            Node currNode = current.node;
            int left = current.left;
            int right = current.right;
            int currentInInd = intToInd.get(currNode.val);

            if (index < levelorder.size()) {
                int nextVal = levelorder.get(index);
                int nextInorderIndex = intToInd.get(nextVal);
                if (nextInorderIndex < currentInInd && nextInorderIndex >= left) {
                    currNode.left = new Node(nextVal);
                    q.offer(new Pair(currNode.left, left, currentInInd - 1));
                    index++;
                }
            }
            if (index < levelorder.size()) {
                int nextVal = levelorder.get(index);
                int nextInorderIndex = intToInd.get(nextVal);
                if (nextInorderIndex > currentInInd && nextInorderIndex <= right) {
                    currNode.right = new Node(nextVal);
                    q.offer(new Pair(currNode.right, currentInInd + 1, right));
                    index++;
                }
            }
        }
        return root;
    }

    public static int[] depth(Node root) {
        if (root == null) return new int[]{0, 0};

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        int sum = 0;

        while (!q.isEmpty()) {
            int n = q.size();
            sum = 0; // Reset sum for the current level
            for (int i = 0; i < n; i++) {
                Node temp = q.poll();
                sum += temp.val;
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            level++;
        }
        return new int[]{level, sum};
    }
}

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Pair {
    Node node;
    int left, right;
    
    Pair(Node node, int left, int right) {
        this.node = node;
        this.left = left;
        this.right = right;
    }
}
