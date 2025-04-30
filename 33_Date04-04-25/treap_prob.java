/*
 * In a magical kingdom, each wizard carries a certain number of enchanted crystals. 
A pair of wizards is said to have a "dominant wizard" if the first wizard, who 
\arrived earlier at the royal gathering, possesses more than twice as many 
crystals as the second wizard, who arrived later.

Given an list of crystals, representing the number of enchanted crystals carried 
by each wizard in their order of arrival at the gathering, calculate the number 
of "dominant wizard" pairs.

A pair of wizards (x, y) is considered dominant if:

- 0 ≤ x < y < crystals.length and
- crystals[x] > 2 × crystals[y]

Example 1:
Input: 
1 3 2 3 1
Output: 2

Explanation: The dominant wizard pairs are:
- Wizard 1 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
- Wizard 3 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1

Example 2:
Input:
2 4 3 5 1
Output: 3

Explanation: The dominant wizard pairs are:
- Wizard 1 (4 crystals) and Wizard 4 (1 crystal), since 4 > 2 × 1
- Wizard 2 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
- Wizard 3 (5 crystals) and Wizard 4 (1 crystal), since 5 > 2 × 1

Constraints:
- 1 ≤ crystals.length ≤ 5 × 10^4
- -2^31 ≤ crystals[i] ≤ 2^31 - 1
 */

import java.util.*;

public class treap_prob {
    public static int reversePairs(int[] nums) {
        Treap t = new Treap();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += t.countGreaterThan(2L * nums[i]);
            t.insert((long) nums[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String n[] = s.next().split(" ");
        int nums[] = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            nums[i] = Integer.parseInt(n[i]);
        }
        System.out.println(reversePairs(nums));
    }
}

class TreapNode {
    long key;
    int priority;
    int size;
    TreapNode left;
    TreapNode right;

    TreapNode(long key) {
        this.key = key;
        this.priority = new Random().nextInt();
        this.size = 1;
    }
}

class Treap {
    TreapNode root;

    int getSize(TreapNode node) {
        return (node == null) ? 0 : node.size;
    }

    void updateSize(TreapNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    TreapNode rotateRight(TreapNode y) {
        TreapNode x = y.left;
        TreapNode z = x.right;
        x.right = y;
        y.left = z;
        updateSize(y);
        updateSize(x);
        return x;
    }

    TreapNode rotateLeft(TreapNode x) {
        TreapNode y = x.right;
        TreapNode z = y.left;
        y.left = x;
        x.right = z;
        updateSize(x);
        updateSize(y);
        return y;
    }

    TreapNode insert(TreapNode node, long key) {
        if (node == null) {
            return new TreapNode(key);
        }
        if (key <= node.key) {
            node.left = insert(node.left, key);
            if (node.left.priority < node.priority) {
                node = rotateRight(node);
            }
        } else {
            node.right = insert(node.right, key);
            if (node.right.priority < node.priority) {
                node = rotateLeft(node);
            }
        }
        updateSize(node);
        return node;
    }

    void insert(long key) {
        root = insert(root, key);
    }

    int countLessThan(TreapNode node, long val) {
        if (node == null) {
            return 0;
        }
        if (val <= node.key) {
            return countLessThan(node.left, val);
        } else {
            return 1 + countLessThan(node.right, val) + getSize(node.left);
        }
    }

    int countGreaterThan(TreapNode node, long val) {
        if (node == null) {
            return 0;
        }
        if (val < node.key) {
            return 1 + getSize(node.right) + countGreaterThan(node.left, val);
        } else {
            return countGreaterThan(node.right, val);
        }
    }

    int countGreaterThan(long val) {
        return countGreaterThan(root, val);
    }

    int countLessThan(long val) {
        return countLessThan(root, val);
    }
}