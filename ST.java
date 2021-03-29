import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class ST<Key extends Comparable<Key>,Value> extends SymbolTable<Key, Value>{
    private Key[] ky;
    private Val[] vl;
    private int N=0;

    public void ST(int max){
        ky=(Key[]) new Comparable[max];
        vl=(Value[])new Object[max];
    }

    public void put(Key key,Value val){
        if(val==null) {delete(key); return;}
        //如果键存在，则修改键值
        int pos=rank(key);
        if (pos<N && ky[pos].compareTo(key)==0) {
            vl[pos]=val;
            return;
        }
        //键值不存在，判断数组是否越界并将数组扩容
        if(N==ky.length) resize(2*ky.length);
        for (int i =N; i>pos; i--) {
            ky[i]=ky[i-1];
            vl[i]=vl[i-1];
        }
        ky[pos]=key;
        vl[pos]=val;
        N++; 
    }

    public Value get(Key key){
        if(isEmpty()) return null;
        int pos=rank(key);
        if (pos<N && ky[pos].compareTo(key)==0) 
            return vl[pos];
        else return null;
    }

    public Value delete(Key key){
        int pos=rank(key);
        //没找到则返回空
        if (pos<N && ky[pos].compareTo(key)!=0) {
            return null;
        }
        Value value = vl[pos];
        if(N<ky.length/2) resize(keys.length/2);
        for (int i = pos; i < N - 1; i++) {
            ky[i] = ky[i + 1];
            vl[i] = vl[i + 1];
        }
        N--;
        return value;
    }

    public boolean contains(Key key){

    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public Key min(){
        return ky[0];
    }

    public Key max(){
        return ky[N-1];
    }

    public Key floor(Key key){
        int pos=rank(key);
        if (pos<N && ky[pos].compareTo(key)==0) {
            return ky[pos];
        }
        return ky[pos-1];
    }

    public Key ceiling(Key key){
        int pos=rank(key);
        return ky[pos]
    }

    public int rank(Key key){
        int low=0;
        int high=size-1;
        while (low<=high) {
            int middle=low+(high-low)/2;
            int cmp=key.compareTo(ky[middle]);
            if (cmp<0) high=middle-1;
            else if(cmp>0) low=middle+1;
            else return middle;
        }
        return low;
    }

    public Key select(int k){
        return ky[k];
    }

    public void deleteMin(){
        delete(min());
    }

    public void deleteMax()){
        delete(max());
    }

    public int size(Key lo,Key hi){
        return rank(hi)-rank(lo);
    }

    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key low, Key high) {
        List<Key> keys=new ArrayList<Key>(size);
        for (int i = 0; i <size; i++) {
            keys.add(this.keys[i]);
        }
        return keys;
    }

    public void resize(int capacity) {
        Key[] newKeys=(Key[]) new Comparable[capacity];
        Value[] newValues=(Value[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newKeys[i]=ky[i];
            newValues[i]=vl[i];
        }
        ky=newKeys;
        vl=newValues;
    }

    public static void main(String[] args){
        int N=100;
        Random r = new Random();
        Integer[] a=new Integer [N];
        for(int i=0 ; i<N ;  i++){
            int ran1 = r.nextInt(100);
            a[i]=ran1;
        }
        //Arrays.sort(a);
        Quick3.sort(a);
        //assert Quick3.isSorted(a):"not sorted";
        Quick3.show(a);
    }
}