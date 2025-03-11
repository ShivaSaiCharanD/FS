// Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
// whether IP-1 and IP-2 belongs to same host range or not.

// Input Format:
// ---------------
// Two space separated strings, IP1 and IP2.
// An integer, CIDR (subnet mask).

// Output Format:
// ---------------
// A boolean result.


// Sample Input-1:
// -----------------
// 192.168.1.10 192.168.1.20
// 24

// Sample Output-1:
// ------------------
// true


// Sample Input-2:
// -----------------
// 192.0.2.1 192.0.3.253
// 24

// Sample Output-2:
// ------------------
// false



import java.util.Scanner;

public class Subnet3 {
     public static int ipToint(String ipadd){
        String[] parts = ipadd.split("\\.");
        return (Integer.parseInt(parts[0])<<24 | Integer.parseInt(parts[1])<<16 | Integer.parseInt(parts[2])<<8 | Integer.parseInt(parts[3]));
    }
    public static String intToip(int ip){
        return String.format("%d.%d.%d.%d",(ip>>24)&0xFF,(ip>>16)&0xFF,(ip>>8)&0xFF,(ip)&0xFF);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String ip1 = s.next();
        String ip2 = s.next();
        int n = s.nextInt();
        int ipadd1 = ipToint(ip1);
        int ipadd2 = ipToint(ip2);
        int subNetMask = 0xFFFFFFFF << (32-n);
        int na1 = subNetMask&ipadd1;
        int na2 = subNetMask&ipadd2;
        int bc1 = ~(subNetMask) | na1;
        int bc2 = ~(subNetMask) | na2;
        if(na1==na2 && bc1==bc2){
            System.out.println(true);
        }
        else{
            System.out.println(false);
        }
    }
}
