public class SuffixArray{
    private String[] suffixes;
    private final int N;

    public SuffixArray(String s){
        N=s.length();
        suffixes=new String[N];
        for (int i=0;i<N;i++){
            suffixes[i]=s.substring(i);
        }
        Quick3String.sort(suffixes);
    }

    public int length(){return N;}
    public String select(int i){
        return suffixes[i];
    }
    public int index(int i){return N-suffixes[i].length();}

    public int lcp(String s1,String s2){
        int len=Math.min(s1.length(), s2.length());
        for (int i=0;i<len;i++){
            if (s1.charAt(i)!=s2.charAt(i)) return i;
        }
        return len;
    }
    public int lcp(int i){
        return lcp(suffixes[i],suffixes[i-1]);
    }

    public int rank(String key){
        int lo=0;int hi=N-1;
        while (lo<=hi){
            int mid=(lo+hi)/2;
            int cmp=key.compareTo(suffixes[mid]);
            if (cmp<0) hi=mid-1;
            else if (cmp>0) lo=mid+1;
            else return mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        String s="shfhoihh gycu hilio";
        SuffixArray sa=new SuffixArray(s);
        for (int i=0;i<sa.length();i++){
            System.out.println(sa.suffixes[i]);
        }
    }
}