import java.util.*;

public class fenwickTree{
    public static void main(String[] args) {
        
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
            BIT[ind] = value;
            ind += ind & -ind;
        }
    }

    int sum(int ind){
        int sum = 0;
        while(ind>0){
            sum+=BIT[ind];
            ind-= ind & -ind;
        }
        return sum;
    }

    int rangeSum(int l,int r){
        return (sum(r) - sum(l));
    }
}