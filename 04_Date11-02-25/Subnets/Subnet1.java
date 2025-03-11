/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/

import java.util.*;

public class Subnet1{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int add = 0xFFFFFFFF;
        int ip = add<<(32-n);
        String subNetMask = String.format("%d.%d.%d.%d",(ip>>24)&0xFF,(ip>>16)&0xFF,(ip>>8)&0xFF,ip&0xFF);
        System.out.println(subNetMask);
    }
}