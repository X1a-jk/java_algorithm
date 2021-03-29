import java.util.Random;
public class Insertion{
    public static void sort(Comparable[] a){
        int N=a.length;
        //Comparable[] b=new Comparable[N];
        for (int i=1;i<N;i++){
            Comparable t=a[i];int j=i;
            // for (int j=i-1;j>=0;j--){
            //     if (less(a[j],a[j-1])){
            //         //Comparable t=a[j];a[j]=a[i];a[j+1]=t;
            //         exch(a,j-1,j);
            //     }                
            // }
            while (j>0){
                j--;
                if (less(t,a[j])){a[j+1]=a[j];a[j]=t;continue;} 
                //a[j]=t;
                else {break;}               
            }            
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
        //show(a);
        sort(a);
        assert isSorted(a):"not sorted";
        show(a);
    }
}