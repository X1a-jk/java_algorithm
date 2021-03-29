import java.util.Random;
public class Selection{
    public static void sort(Comparable[] a){
        int N=a.length;
        for (int i=0;i<N;i++){
            int min=i;
            for (int j=i+1;j<N;j++){
                if (less(a[j],a[min])) min=j;
            }
            exch(a,i,min);
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
        Random r = new Random();
        Integer[] a=new Integer [10];
        for(int i=0 ; i<10 ;  i++){
            int ran1 = r.nextInt(100);
            a[i]=ran1;
        }
        sort(a);
        assert isSorted(a);
        show(a);
    }
}