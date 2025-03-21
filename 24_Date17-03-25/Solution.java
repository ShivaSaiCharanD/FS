import java.util.*;

public class Solution{
    public static boolean solve(int[] ins){
        int n = ins.length;
        String res = "";
        for(int i=0;i<n;i++){
            String temp = Integer.toBinaryString(ins[i]);
            res+=("0".repeat(8-temp.length())+temp);
        }
        System.out.println(res);
        if(res.length()==8 && res.charAt(0)!='0'){
            return false;
        }
        int count = 0;
        for(int i=0;i<4;i++){
            if(res.charAt(i)=='1'){
                count++;
            }
        }
        if(count==5) return false;
        for(int i=1;i<count;i++){
            if(res.charAt(i*8)!='1' && res.charAt((i*8) + 1)!='0' && i*8<count*8){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] inp = s.nextLine().split(" ");
        int n = inp.length;
        int[] ins = new int[n];
        for(int i=0;i<n;i++){
            ins[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(solve(ins));
    }
}