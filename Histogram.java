import java.util.*;
public class Histogram{
    public static int[] his(int []a,int M){
        int [] array=new int [M];
        for (int i=0;i<M;i++){
            int count=0;
            for (int j=0;j<a.length;j++){
                if (a[j]==i){count++;}
            array[i]=count;
            }
        }
        return array;
    }
    public static void main(String[] args){
        int M=10;
        int []a={1,2,4,3,2,1,3,4,6,7,6,5,4,7,8,9};
        System.out.println(Arrays.toString(his(a,M)));
    }
}