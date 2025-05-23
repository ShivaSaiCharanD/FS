/*
 * Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can cunstruct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6

Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false
 */

 import java.util.*;

public class walls{
    public static boolean solve(int[] walls,int sum){
        int n = walls.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            visited[i] = true;
            if(!backtrack(walls,i,0,sum/4,visited,count)){
                visited[i] = false;
            }
        }
        if(count==4){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int walls[] = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++){
            walls[i] = s.nextInt();
            sum+=walls[i];
        }
        if(sum%4!=0){
            System.out.println(false);
            return;
        }
        System.out.println(System.out.println(walls,sum));
    }
}