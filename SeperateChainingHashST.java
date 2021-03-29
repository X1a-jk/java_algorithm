import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SeperateChainingHashST<Key,Value>{
    private int N;
    private int M;
    private BinarySearchST bst;

    public SeperateChainingHashST(){SeperateChainingHashST(97);}

    public SeperateChainingHashST(int M){
        this.M=M;
        bst=(BinarySearchST<Key,Value>[]) new BinarySearchST[M];
        for (int i=0;i<M;i++){
            bst[i]=new BinarySearchST();
        }
    }

    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key,Value val){
        int hash=hash(key);
        bst[hash].put(key, val);
    }

    public Value get(Key key){
        return (Value) bst[hash(key)].get(key);
    }

    public Iterable<Key> keys(){
        return Iterable<Key> keys(0,M);
    }

    public Iterable<Key> keys(int low, int high) {
        List<Key> keys=new ArrayList<Key>(M);
        for (int i = 0; i <M; i++) {
            lst=bst[i];
            keys.add(lst.keys());
        }
        return keys;
    }
    public void show(){
        for (int i=0;i<M;i++){
            System.out.println(keys[i]+":"+values[i]);
        }
    }

    public static void main(String[] args){
        int N=97;
        SeperateChainingHashST schst=new SeperateChainingHashST<>(N);
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
            schst.put(ran1, strran);
        }
        schst.show();
        System.out.println(schst.get(pivot));        
    }
}