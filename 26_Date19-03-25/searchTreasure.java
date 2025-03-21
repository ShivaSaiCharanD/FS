/*
 * Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
ancient runes, where each cell holds a single character. Legend has a 
powerful incantation—represented as a string—is hidden within these runes. 
To unlock the treasure, you must verify if the incantation exists on the map.

The incantation is formed by linking runes that are directly next to each other 
either horizontally or vertically. Each rune on the map can only be used once in
the incantation.

Your Task:  
Given an m x n grid representing the treasure map and a string representing the 
incantation, return true if the incantation can be traced on the map; 
otherwise, return false.


Example 1:
----------
Input:  
3 4
ABCD
SFCS
ADEE
ABCCED

Output:
ABCCED can be traced

Explanation (check hint)
Treasure Map Grid:  
[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCCED" exists in map


Example 2:
----------
Input:
3 4
ABCE
SFCS
ADEE
ABCB

Output: 
ABCB cannot be traced

Explanation:
Treasure Map Grid:  

[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCB" does not exist in map


Constraints:

- m == the number of rows in the grid  
- n == the number of columns in the grid  
- 1 <= m, n <= 6  
- 1 <= incantation length <= 15  
- The grid and incantation consist only of uppercase and lowercase English letters.

 */

import java.util.*;

public class searchTreasure {
    public static boolean backtrack(char[][] grid,int i,int j,String str,String curr,int ind){
        if(curr.equals(str)){
            return true;
        }
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || ind>str.length()){
            return false;
        }
        if(str.charAt(ind)!=grid[i][j]){
            return false;
        }
        return backtrack(grid,i+1,j,str,curr+str.charAt(ind),ind+1) || backtrack(grid,i-1,j,str,curr+str.charAt(ind),ind+1) || backtrack(grid,i,j+1,str,curr+str.charAt(ind),ind+1) || backtrack(grid,i,j-1,str,curr+str.charAt(ind),ind+1);
    }
    public static boolean solve(int n,int m,char[][] grid,String str){
        boolean flag = false;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                    if(grid[i][j]==str.charAt(0)){
                        flag = backtrack(grid,i,j,str,"",0);
                        if(flag){
                            return true;
                        }
                    }
                }
            }
        return flag;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        char[][] grid = new char[n][m];
        for(int i=0;i<n;i++){
            grid[i] = s.next().toCharArray();
        }
        String str = s.next();
        boolean flag = solve(n,m,grid,str);
        if(flag){
            System.out.println(str+" can be traced");
        }
        else{
            System.out.println(str+" cannot be traced");   
        }
    }
}
