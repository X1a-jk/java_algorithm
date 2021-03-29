import java.util.Random;
public class MaxPQ<Key extends Comparable<Key>>{
    private Key[] pq;
    private int N=0;

    public MaxPQ(int max){
        pq=(Key[]) new Comparable[max+1];
    }
    public boolean isEmpty(){return N==0;}
    public int size(){return N;}

    public void insert(Key v){
        N++;
        pq[N]=v;
        swim(N);
    }
    public Key delMax(){
        Key Max=pq[1];
        exch(1,N);N--;
        pq[N+1]=null;
        sink(1);       
        
        return Max;
    }
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j){
        Key t=pq[i];pq[i]=pq[j];pq[j]=t;
    }
    public void swim(int i){
        while (i>1){
            if (less(i/2,i)){exch(i/2,i);}
            i=i/2;
        }
    }
    public void sink(int j){
        while (j*2<=N){
            int k=2*j;
            if (k<N && less(k,k+1)){k++;}
            if (less(k,j)){break;}
            exch(k,j);
            j=k;
        }
    }
    public void show(){
        for (int i=1;i<=N;i++){System.out.println(pq[i]+"");}
    }
    public static void main(String[] args){
        int N=10;
        Random r = new Random();
        MaxPQ a=new MaxPQ<>(N);
        for(int i=0 ; i<N ;  i++){
            int ran1 = r.nextInt(100);
            a.insert(ran1);
        }
        
        a.show();
        System.out.println("Max:"+a.delMax());
        a.show();
    }
}