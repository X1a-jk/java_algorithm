import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
public class Quick3{
    public static void sort(Comparable[] a){
        int V=a.length;int lo=0;int hi=V-1;
        quicksort(a,lo,hi);
    }
    // public static void quickSort(Comparable[] a,int lo,int hi){
    //     if (lo>=hi) return;
    //     int j=quicksort(a, lo, hi);
    //     quickSort(a, lo, j-1);
    //     quickSort(a, j+1, hi);
    // }
    public static void quicksort(Comparable[] a,int lo,int hi){
        if (lo>=hi) return;
        int i=lo;int j=hi;int k=lo+1;
        Random r = new Random();
        int x=r.nextInt(hi-lo)+lo;
        //exch(a,lo,x);
        Comparable pivot=a[x];
        exch(a,lo,x);
        while (k<=j){
            int cmp=a[k].compareTo(pivot);
            if (cmp<0){exch(a,i,k);i++;k++;}
            else if (cmp>0){exch(a,j,k);j--;}
            else k++;
        }
        quicksort(a,lo,i-1);
        quicksort(a,j+1,hi);        
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
        //Arrays.sort(a);
        sort(a);
        assert isSorted(a):"not sorted";
        show(a);
    }
}