

import java.util.Scanner;

public class BabarBinaryCode {
    public static String solve(int n){
        StringBuilder res = new StringBuilder();
        while(n>0){
            int temp = (n)%2;
            if(temp==0){
                res.append(1);
            }
            else{
                res.append(0);
            }
            n = (n-1)/2;
        }
        return res.reverse().toString();
    }
     public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println(solve(n));
        // System.out.println(Integer.toBinaryString(n+1).substring(1));
    }
}
