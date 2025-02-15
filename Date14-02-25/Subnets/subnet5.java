// In networking, a subnet is a portion of a network with a defined range of IP addresses. 
// Two subnets overlap if they share some common IP addresses. Given two network 
// IP addresses with their respective CIDR notations, write a program that determines 
// whether these subnets overlap.

// Your program should take as input:

// IP1: The first subnet’s network address.
// CIDR1: The CIDR notation (prefix length) for the first subnet.
// IP2: The second subnet’s network address.
// CIDR2: The CIDR notation (prefix length) for the second subnet.
// The program should return true if the subnets overlap and false otherwise.

// Input Format:
// -------------
// A string IP1: The first network IP address (e.g., "192.168.1.0").
// An integer CIDR1: The subnet mask prefix (e.g., 24 for /24).
// A string IP2: The second network IP address (e.g., "192.168.1.128").
// An integer CIDR2: The subnet mask prefix for the second subnet.

// Output Format:
// --------------
// A boolean value, if the two subnets overlap or not.

// Sample Input:
// -------------
// 192.168.1.0
// 24
// 192.168.1.128
// 25

// Sample Output:
// --------------
// true

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0
// 192.168.1.64
// 192.168.1.128
// 192.168.1.192

// Sample Input:
// -------------
// 10.0.0.0
// 16
// 10.1.0.0
// 16

// Sample Output:
// --------------
// false

import java.util.Scanner;

public class subnet5 {
    public static int intToip(String ip) {
        String[] parts = ip.split("\\.");
        return Integer.parseInt(parts[0]) << 24 | Integer.parseInt(parts[1]) << 16 | Integer.parseInt(parts[2]) << 8 | Integer.parseInt(parts[3]);
    }

    public static boolean overlap(String ip1, int preLen1, String ip2, int preLen2) {
        int subNetMask1 = 0xFFFFFFFF << (32 - preLen1);
        int subNetMask2 = 0xFFFFFFFF << (32 - preLen2);
        int ipadd1 = intToip(ip1);
        int ipadd2 = intToip(ip2);
        int na1 = subNetMask1 & ipadd1;
        int na2 = subNetMask2 & ipadd2;
        int ba1 = ~(subNetMask1) | na1;
        int ba2 = ~(subNetMask2) | na2;
        if (ba1 < na2 || ba2 < na1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String ip1 = s.next();
        int preLen1 = s.nextInt();
        String ip2 = s.next();
        int preLen2 = s.nextInt();
        System.out.println(overlap(ip1, preLen1, ip2, preLen2));
    }
}
