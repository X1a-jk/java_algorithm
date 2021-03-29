import java.util.Random;
public class MSD{
    public static int R=256;
    public static final int M=5;
    public static String[] aux;
    public static int charAt(String s,int d){
        if (d<s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a)
    {
        int N=a.length;
        aux=new String[N];
        sort(a,0,N-1,0);
    }
    public static void sort(String[] a,int lo,int hi,int d){
        if (hi<=lo+M) {
            StringInsertion.sort(a, lo, hi, d);
            return;
        }
        int[] count=new int[R+2];
        for (int i=lo;i<=hi;i++){
            int r=charAt(a[i],d);
            count[r+2]++;
        }
        for (int i=0;i<R+1;i++){
            count[i+1]+=count[i];
        }
        for (int i=lo;i<=hi;i++){
            int r=charAt(a[i],d);
            aux[count[r+1]]=a[i];
            count[r+1]++;
        }
        for (int i=lo;i<=hi;i++){
            a[i]=aux[i-lo];
        }
        for (int r=0;r<R;r++){
            sort(a,lo+count[r],lo+count[r+1]-1,d+1);
        }
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