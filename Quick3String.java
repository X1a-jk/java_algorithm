import java.io.StringWriter;
import java.util.Random;
public class Quick3String{
    public static int R=256;
    public static final int M=5;
    //public static String[] aux;
    public static int charAt(String s,int d){
        if (d<s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a)
    {
        int N=a.length;
        //aux=new String[N];
        sort(a,0,N-1,0);
    }
    public static void sort(String[] a,int lo,int hi,int d){
        if (hi<=lo+M) {
            StringInsertion.sort(a, lo, hi, d);
            return;
        }
        int lt=lo;int gt=hi;int i=lo+1;
        Random r=new Random();
        int x=lo+r.nextInt(hi-lo+1);StringInsertion.exch(a,lo,x);
        int v=charAt(a[lo],d);
        while (i<=gt){
            int current=charAt(a[i], d);
            if (current<v) {StringInsertion.exch(a, lt, i);lt++;i++;}
            else if (current>v) {StringInsertion.exch(a, i, gt);gt--;}
            else i++;
        }
        sort(a,lo,lt-1,d);
        if (v>0) sort(a,lt,gt,d+1);
        sort(a,gt+1,hi,d);
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=100;
        //int L=7;
        String[] str={"a","b","c","d","e","f","g","h","i","j","k",
        "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] string=new String[V];
        for (int i=0;i<V;i++){
            String a="";
            int L=r.nextInt(10)+1;
            for (int j=0;j<L;j++){
                int alpha=r.nextInt(26);
                a+=str[alpha];
            }
            string[i]=a;
        }
        sort(string);
        for (String word:string) System.out.println(word);
    }
}