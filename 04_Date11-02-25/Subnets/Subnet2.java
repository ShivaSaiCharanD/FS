/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, network IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

*/

import java.util.Scanner;

public class Subnet2 {
    public static int ipToint(String ipadd){
        String[] parts = ipadd.split("\\.");
        return (Integer.parseInt(parts[0])<<24 | Integer.parseInt(parts[1])<<16 | Integer.parseInt(parts[2])<<8 | Integer.parseInt(parts[3]));
    }
    public static String intToip(int ip){
        return String.format("%d.%d.%d.%d",(ip>>24)&0xFF,(ip>>16)&0xFF,(ip>>8)&0xFF,(ip)&0xFF);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String ipadd = s.next();
        int ipInt = ipToint(ipadd);
        int len = s.nextInt();
        int subNetMask = 0xFFFFFFFF<<(32-len);
        int netAdd = subNetMask&ipInt;
        int broadAdd = ~(subNetMask) | netAdd;
        System.out.println(intToip(netAdd));
        System.out.println(intToip(broadAdd));
    }
}
