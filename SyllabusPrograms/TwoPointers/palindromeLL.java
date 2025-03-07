// Cliff Shaw is working on the singly linked list.
// He is given a list of boxes arranged as singly linked list,
// where each box is printed a positive number on it.

// Your task is to help Mr Cliff to find the given list is equivalent to 
// the reverse of it or not. If yes, print "true", otherwise print "false"

// Input Format:
// -------------
// Line-1: space separated integers, boxes as list.

// Output Format:
// --------------
// Print a boolean a value.


// Sample Input-1:
// ---------------
// 3 6 2 6 3

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 6 2 3 6

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class palindromeLL {
    public static boolean solve(Node head,Node mid){
        if(head==null) return true;
        Node newHead = reverse(mid);
        Node temp = head;
        while(newHead!=null){
            if(newHead.data!=temp.data){
                return false;
            }
            temp = temp.next;
            newHead = newHead.next;
        }
        return true;
    }
    public static void traverse(Node head){
        if(head==null) return;
        Node temp = head;
        while(temp.next!=null){
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }
    public static Node reverse(Node head){
        Node curr = head;
        Node prev = null;
        Node next = null;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] ele = s.nextLine().split(" ");
        LinkedList list = new LinkedList(Integer.parseInt(ele[0]));
        for(int i=1;i<ele.length;i++){
            list.insert(Integer.parseInt(ele[i]));
        }
        Node mid = list.findMid();
        System.out.println(solve(list.head,mid));
    }
}
class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}
class LinkedList{
    Node head = null;
    LinkedList(int data){
        this.head = new Node(data);
    }
    void insert(int data){
        if(this.head==null){
            this.head = new Node(data);
            return;
        }
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = new Node(data);
    }
    Node findMid(){
        Node slow = this.head;
        Node fast = this.head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.next;
    }
}
