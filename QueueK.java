import java.util.Queue;
import java.util.LinkedList;
public class QueueK{
    public static void Kth(int k, Queue q){
        for (int i=0;i<k;i++){
            System.out.println(q.poll());
        }
        //return a;
    }
    public static void main(String[] args){
        Queue<Integer> q= new LinkedList<Integer>();
        for (int i=0;i<10;i++){
            q.offer(i);
        }
        //for (Integer in:q) System.out.println(in);
        Kth(5,q);
        //System.out.println(ans);
    }   
}