// Write a Java program that determines the class of a given IPv4 address. 
// The classification follows the standard IP address classes:
// 	- Class A: IP addresses where the first octet is in the range 1-127.
// 	- Class B: IP addresses where the first octet is in the range 128-191.
// 	- Class C: IP addresses where the first octet is in the range 192-223.
// 	- Multicast (Class D): IP addresses where the first octet is in the range 224-239.

// Input Format:
// -------------
// A string IP: The first network IP address (e.g.,0-239).

// Output Format:
// --------------
// A boolean value, if the two subnets overlap or not.


// Sample Input:
// -------------
// 192.168.1.0

// Sample Output:
// --------------
// Class C

// Explanation:
// ------------
// The first octet 192 is within the Multicast range.


import java.util.Scanner;

public class subnet6 {
    public static String find(String str){
        int target = Integer.parseInt(str);
        if(target>=1 && target<=127){
            return "Class A";
        }
        else if(target>=128 && target<=191){
            return "Class B";
        }
        else if(target>=192 && target<=223){
            return "Class C";
        }
        else if(target>=224 && target<=239){
            return "Multicast";
        }
        return "Not Found";
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] str = s.nextLine().split("\\.");
        System.out.println(find(str[0]));
    }
}
