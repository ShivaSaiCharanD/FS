// Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
// cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// The puzzle board has some patterns formed with boxes in it, 
// the patterns may be repeated. The patterns are formed with boxes (1's) only, 
// that are connected horizontally and vertically but not diagonally.

// Pranav wants to find out the number of unique patterns in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of unique patterns in 
// the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the grid-land.
// Next M lines: contains N space-separated integers [0, 1].

// Output Format:
// --------------
// Print an integer, the number of unique patterns in the puzzle board.


// Sample Input-1:
// ---------------
// 5 5
// 0 1 0 1 1
// 1 1 1 0 1
// 0 1 0 1 0
// 1 0 1 1 1
// 1 1 0 1 0

// Sample Output-1:
// ----------------
// 3

// Explanation-1:
// ------------
// The unique patterns are as follows:
//   1			1 1	    1 
// 1 1 1		  1 ,	1 1
//   1	   ,	
   
   
// Sample Input-2:
// ---------------
// 6 6
// 1 1 0 0 1 1
// 1 0 1 1 0 1
// 0 1 0 1 0 0
// 1 1 0 0 0 1
// 0 0 1 0 1 1
// 1 1 0 1 0 0

// Sample Output-2:
// ----------------
// 5

// Explanation-2:
// ------------
// The unique patterns are as follows:
// 1 1		1 1		    1		1 1	,	1
// 1   ,     1 ,	    1 1 ,		



import java.util.*;

public class unqPatterns {
    public static int solve(int[][] arr,int n,int m){
        Set<String> set = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==1){
                    set.add(bfs(arr,i,j));
                }
            }
        }
        return set.size();
    }
    public static String bfs(int[][] arr,int i,int j){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i,j,0,0});
        arr[i][j] = 0;
        StringBuilder res = new StringBuilder();
        int[][] directions = new int[][]{{1,0},{0,1},{0,-1},{-1,0}};
        while(!q.isEmpty()){
            int temp[] = q.poll();
            int r = temp[0];
            int c = temp[1];
            int relr = temp[2];
            int relc = temp[3];
            for(int[] dir:directions){
                int nr = r+dir[0];
                int nc = c+dir[1];
                res.append("("+relr+","+relc+")");
                if(nr>=0 && nr<arr.length && nc>=0 && nc<arr[0].length && arr[nr][nc]==1){
                    arr[nr][nc] = 0;
                    q.add(new int[]{nr,nc,relr+dir[0],relc+dir[1]});
                }
            }
        }
        return res.toString();
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int arr[][] = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = s.nextInt();
            }
        }
        System.out.println(solve(arr,n,m));
    }
}
