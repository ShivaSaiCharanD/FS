// Imagine you're the chief engineer aboard a futuristic spaceship. The ship is 
// powered by N series of fuel cells arranged in a row, where each fuel cell holds
// a specific amount of energy measured in megajoules. Your job is to manage these 
// fuel cells by performing two main operations:

// Option 1: Calculate the total energy available in a consecutive block of fuel 
//           cells between indices start and end (inclusive).
// Option 2: Update the energy level with given 'newEnergy' of a specific 'index' 
//           fuel cell when it's refilled.

// Input Format:
// -------------
// Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the number of operations.
// Line-2: N space separated integers.
// next Q lines: Three integers option, start/index and end/newEnergy.

// Output Format:
// --------------
// An integer result, for every totalEnergy.


// Example 1:
// -----------
// Input:
// 8 5
// 1 2 13 4 25 16 17 8
// 1 2 6		
// 1 0 7		
// 2 2 18		
// 2 4 17		
// 1 2 7		

// Output:
// 75
// 86
// 80


// Example 2:
// ----------
// Input:
// 16 1
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
// 1 0 15

// Output:
// 136


// Constraints

// - 1 <= N <= 3*10^4  
// - -100 <= fuelCells[i] <= 100  
// - 0 <= index < fuelCells.length  
// - -100 <= newEnergy <= 100  
// - 0 <= start <= end < fuelCells.length  
// - At most 3*10^4 calls will be made to recharge and totalEnergy.

// */

import java.util.*;

public class charge{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        fenwick fenT = new fenwick(n);
        for(int i=1;i<=n;i++){
            fenT.update(i,s.nextInt());
        }
        s.nextLine();
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<k;i++){
            int[] query = new int[]{s.nextInt(),s.nextInt(),s.nextInt()};
            if(query[0]==1){
                res.add(fenT.rangeSum(query[1]+1,query[2]+1));
            }
            else{
                fenT.update(query[1]+1,query[2]-fenT.rangeSum(query[1]+1,query[1]+1));
            }
        }
        System.out.println(res);
    }
}

class fenwick{
    int[] BIT;
    int n;
    fenwick(int n){
        BIT = new int[n+1];
        this.n = n;
    }

    void update(int ind,int value){
        while(ind<=n){
            BIT[ind] += value;
            ind += (ind & -ind);
        }
    }

    int sum(int ind){
        int sum = 0;
        while(ind>0){
            sum+=BIT[ind];
            ind-= (ind & -ind);
        }
        return sum;
    }

    int rangeSum(int l,int r){
        return (sum(r) - sum(l-1));
    }
}