import java.util.Random;

public class RandomSeq{    
    public static void main(){
        int N = 5;//Integer.parseInt(args[0]);
        int lo = 100;//Integer.parseInt(args[1]);
        int hi= 200;//Integer.parseInt(args[2]);
        for (int i=0;i<N;i++)
        {
            int x=lo+hi;
            System.out.printf("%.2f\n", x);
        }
    }
}