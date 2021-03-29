import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class LinearProbingHashST<Key,Value>{
    private int N;
    private int M=16;
    private Key[] keys;
    private Value[] vals;
    
    public LinearProbingHashST(){
        keys=(Key[]) new Object[M];
        vals=(Value[]) new Object[M];
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key,Value val){
        int hashkey=hash(key);
        if (N>=M/2) resize(2*M);
        int i;
        for (i=hashkey;keys[i]!=null;i=(i+1) % M){
            if (key.equals(keys[i])) {vals[i]=val;return;}
        }
        keys[i]=key;vals[i]=val;
        N++;
    }

    public Value get(Key key){
        int hashkey=hash(key);
        int i;
        for (i=hashkey;keys[i]!=null;i=(i+1) % M){
            if (key.equals(keys[i])) return vals[i];
        }
        return null;
    }

    private void resize(int k){
        //LinearProbingHashST st=new LinearProbingHashST<>()
        Key[] newkeys=(Key[]) new Object[k];
        Value[] newvals=(Value[]) new Object[k];
        for (int i=0;i<M;i++){
            newkeys[i]=keys[i];newvals[i]=vals[i];
        }
        keys=newkeys;vals=newvals;
        M=k;
    }

    public boolean isEmpty(){return N==0;}

    public void delete(Key key){
        int hashkey=hash(key);
        int i;
        for (i=hashkey;keys[i]!=null;i=(i+1)%M){
            if (keys[i].equals(key)){
                keys[i]=null;vals[i]=null;
                for (int j=i+1;keys[j]!=null;j=(j+1)%M){
                    Key k=keys[j];Value v=vals[j];keys[j]=null;vals[j]=null;
                    put(k,v);
                }
                N--;
                if (N<=M/8 && N>0) resize(M/2);
            }
        }
    }

    public Iterable<Key> keylst() {
        List<Key> keylst=new ArrayList<Key>(N);
        for (int i = 0; i <M; i++) {
            if (keys[i]!=null) keylst.add(keys[i]);            
        }
        return keylst;
    }

    public void show(){
        for (int i=0;i<M;i++){
            if (this.keys[i]!=null) System.out.println(keys[i]+":"+vals[i]);  
            //System.out.println(keys[i]+":"+vals[i]);            
        }
    }


    public static void main(String[] args){
        int N=100;
        LinearProbingHashST bs=new LinearProbingHashST<>();
        Random r = new Random();
        //Integer[] a=new Integer [N];
        String[] str={"a","b","c","d","e","f","g","h","i","j","k",
        "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int pivot=0;
        for(int i=0 ; i<N ;  i++){
            int ran1 = r.nextInt(100);
            pivot=ran1;
            int ran2 = r.nextInt(26);
            String strran=str[ran2];
            bs.put(ran1, strran);
        }
        bs.show();
        //System.out.println(bs.get(pivot));
        System.out.println(pivot);
        bs.delete(pivot);
        bs.show();
        
    }
}