import java.util.Random;
public class StringInsertion{
    public static int R=256;
    public static void sort(String[] a,int lo,int hi,int d){
        for (int i=lo+1;i<=hi;i++){
            for (int j=i;j>lo && less(a[j],a[j-1],d);j--){
                exch(a,j,j-1);
            }
        }
    }
    public static boolean less(String a,String b,int d){
        return a.substring(d).compareTo(b.substring(d))<0;
    }
    public static void exch(String[] a,int i,int j){
        String t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=100;
        int L=7;
        String[] str={"a","b","c","d","e","f","g","h","i","j","k",
        "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] string=new String[V];
        for (int i=0;i<V;i++){
            String a="";
            //int L=r.nextInt(5)+5;
            for (int j=0;j<L;j++){
                int alpha=r.nextInt(26);
                a+=str[alpha];
            }
            string[i]=a;
        }
        sort(string,0,V-1,0);
        for (String word:string) System.out.println(word);
    }
}