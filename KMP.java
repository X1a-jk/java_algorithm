import java.util.Random;;

public class KMP{
    private String pat;
    private int[][] dfa;
    public KMP(String pat){
        this.pat=pat;
        int M=pat.length();
        int R=256;
        dfa=new int[R][M];
        dfa(pat);
    }

    private void dfa(String pat){
        int M=pat.length();
        int X=0;
        int R=256;
        dfa[pat.charAt(0)][0]=1;
        for (int i=1;i<M;i++){
            for (int c=0;c<R;c++){
                dfa[c][i]=dfa[c][X];
            }
            dfa[pat.charAt(i)][i]=i+1;
            X=dfa[pat.charAt(i)][X];
        }
    }

    public int search(String txt){
        int N=txt.length();int i=0;int j=0;
        int M=pat.length();
        for (i=0,j=0;i<N && j<M;i++){
            char t=txt.charAt(i);
            j=dfa[t][j];
        }
        if (j==M) return i-M;
        else return N;        
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=100;
        //int L=7;
        String[] str={"a","b","c","d","e","f","g","h","i","j","k",
        "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String string="";
        int j=50+r.nextInt(20);
        String pat="";
        for (int i=0;i<V;i++){
            String ss=str[r.nextInt(26)];
            string+=ss;
            if (i>j &&i<j+5){pat+=ss;}
        }
        KMP kmp=new KMP(pat);       
        
        System.out.println(string);
        System.out.println(pat);
        System.out.println(kmp.search(string));
        System.out.println(j+1);
    }
}