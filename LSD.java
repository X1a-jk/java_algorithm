import java.util.Random;
public class LSD{
    public static void sort(String[] a,int w){
        int N=a.length;
        int R=256;
        String[] aux=new String[N];

        for (int i=w-1;i>=0;i--){
            int[] count=new int[R+1];
            for (int j=0;j<N;j++){
                char r=a[j].charAt(i);
                count[r+1]++;
            }
            for (int k=0;k<R;k++){
                count[k+1]+=count[k];
            }
            for (int m=0;m<N;m++){
                char r=a[m].charAt(i);
                aux[count[r]]=a[m];
                count[r]++;
            } 
            for (int p=0;p<N;p++){
                a[p]=aux[p];
            }
        }
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
            for (int j=0;j<L;j++){
                int alpha=r.nextInt(26);
                a+=str[alpha];
            }
            string[i]=a;
        }
        sort(string,L);
        String st="qwert";System.out.println(st.charAt(2));
        //for (String word:string) System.out.println(word);
    }
}