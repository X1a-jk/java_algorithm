import java.util.Random;;

public class BoyerMoore{
    private String pat;
    private int[] right;
    public BoyerMoore(String pat){
        this.pat=pat;
        int M=pat.length();
        int R=256;
        right=new int[R];
        for (int c=0;c<R;c++){
            right[c]=-1;
        }
        for (int j=0;j<M;j++){
            right[pat.charAt(j)]=j;
        }
    }


    public int search(String txt){
        int N=txt.length();
        int M=pat.length();
        int skip;
        for (int i=0;i<=N-M;i+=skip){
            skip=0;
            for (int j=M-1;j>=0;j--){
                if (pat.charAt(j)!=txt.charAt(i+j)){
                    skip=j-right[txt.charAt(i+j)];
                    if (skip<1) skip=1;
                    break;
                }      
            }
            if (skip==0) return i;
        }
        return N;
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
        BoyerMoore bm=new BoyerMoore(pat);       
        
        System.out.println(string);
        System.out.println(pat);
        System.out.println(bm.search(string));
        System.out.println(j+1);
    }
}