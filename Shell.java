import java.util.Random;
public class Shell{
    public static void sort(Comparable[] a){
        int N=a.length;
        int h=1;
        while (h<N/3){
            h=3*h+1;
        }
        while (h>=1){
            for (int i=h;i<N;i++){
                Comparable t=a[i];int j=i;
                while (j>=h){
                    j=j-h;
                    if (less(t,a[j])){a[j+h]=a[j];a[j]=t;continue;} 
                    //a[j]=t;
                    else {break;} 
                }   
            }
            h=h/3;
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
        Integer[] a=new Integer [100];
        for(int i=0 ; i<100 ;  i++){
            int ran1 = r.nextInt(100);
            a[i]=ran1;
        }
        sort(a);
        assert isSorted(a);
        show(a);
    }
}