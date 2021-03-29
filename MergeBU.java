import java.util.Random;

public class MergeBU{
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        int N=a.length;
        aux=new Comparable[N];
        for (int sz=1;sz<N;sz=sz+sz){
            for (int lo=0;lo<N-sz;lo+=2*sz){
                merge(a,lo,lo+sz-1,Math.min(N-1,lo+2*sz-1));
            }
        }
    }
    public static void merge(Comparable[] a,int lo,int mid,int hi){
        int i=lo;int j=mid+1;
        for (int k=lo;k<=hi;k++){
            aux[k]=a[k];
        }
        for (int k=lo;k<=hi;k++){
            if (i>mid){a[k]=aux[j];j++;}
            else if (j>hi){a[k]=aux[i];i++;}
            else if (less(aux[i],aux[j])){a[k]=aux[i];i++;}
            else {a[k]=aux[j];j++;}
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
        int N=100;
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