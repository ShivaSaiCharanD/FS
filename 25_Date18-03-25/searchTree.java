import java.util.*;

public class searchTree{
    public static Node insert(Node root,int data){
        if(root==null){
            root = new Node(data);
            return root;
        }
        if(data>root.data){
            root.right = insert(root.right,data);
        }
        if(data<root.data){
            root.left = insert(root.left,data);
        }
        return root;
    }
    public static void search(Node root,int target,int[] temp){
        if(root==null){
            return;
        }
        if(root.data==target){
            temp[0] = root.data;
            temp[1] = root.data;
            return;
        }
        if(root.data<target){
            temp[0] = root.data;
            search(root.right,target,temp);
        }
        if(root.data>target){
            temp[1] = root.data;
            search(root.left,target,temp);
        }
    }
    public static List<String> solve(int[] eles,int[] queries){
        int n = eles.length;
        Node root = new Node(eles[0]);
        for(int i=1;i<n;i++){
            insert(root,eles[i]);
        }
        List<String> res = new ArrayList<>();
        for(int i:queries){
            int temp[] = new int[]{-1,-1};
            search(root,i,temp);
            res.add(Arrays.toString(temp));
        }
        return res;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] str = s.nextLine().split(" ");
        String[] query = s.nextLine().split(" ");
        int eles[] = new int[str.length];
        for(int i=0;i<str.length;i++){
            eles[i] = Integer.parseInt(str[i]);
        }
        int queries[] = new int[query.length];
        for(int i=0;i<query.length;i++){
            queries[i] = Integer.parseInt(query[i]);
        }
        System.out.println(solve(eles,queries));
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