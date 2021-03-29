import java.util.Random;
public class PQsort{
    public static void sort(Comparable[] b){
        Comparable[] a=new Comparable[b.length+1];a[0]=null;
        for (int i=0;i<b.length;i++){
            a[i+1]=b[i];
        }
        int N=b.length;
        for (int i=N/2;i>=1;i--){
            sink(a,i,N);                  
        }      
        while(N>1){
            exch(a,1,N--);
            sink(a,1,N);
        }
        for (int i=0;i<b.length;i++){
            b[i]=a[i+1];
        }
    }
    public static void sink(Comparable[] a,int t,int N){
        while(2*t<=N){
            int j=2*t;
            if (j<N && less(a[j],a[j+1])){j++;}
            if (less(a[j],a[t])) break;
            exch(a,j,t);
            t=j;
        }
    }
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b)<0;
    }
    public static void exch(Comparable[] a, int i,int j){
        Comparable t=a[i];a[i]=a[j];a[j]=t;
    }
    public static void show(Comparable[] a){
        for (int i=0;i<a.length;i++){System.out.println(a[i]+"");}
    }
    public static boolean isSorted(Comparable[] a){
        for (int i=1;i<a.length;i++){
            if (less(a[i],a[i-1])) {return false;}
        }
        return true;
    }
    public static void main(String[] args){
        int N=10;
        Random r = new Random();
        Integer[] a=new Integer [N];
        for(int i=0 ; i<N ;  i++){
            int ran1 = r.nextInt(100);
            a[i]=ran1;
        }
        sort(a);
        assert isSorted(a):"not sorted";
        show(a);
    }
}