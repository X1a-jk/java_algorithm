import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//extends SymbolTable<Key, Value>  //二叉查找树

//二分搜索，基于排序数组
public class BST<Key extends Comparable<Key>,Value>  {
    private Node root;
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int size;
        public Node(Key key, Value val, int size){
            this.key=key;this.val=val;this.size=size;
        }
    }

    public int size(){return size(root);}

    private int size(Node node){
        if (node==null) return 0;
        else return node.size;
    }

    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node node,Key key){
        if (node==null) return null;
        int cmp=key.compareTo(node.key);
        if (cmp>0) return get(node.right,key);
        else if (cmp<0) return get(node.left,key);
        else return node.val;
    }

    public void put(Key key,Value val){
        root=put(root,key,val);
    }
    private Node put(Node node,Key key,Value val){
        if (node==null) return new Node(key,val,1);
        int cmp=key.compareTo(node.key);
        if (cmp>0)  node.right=put(node.right,key,val);
        else if (cmp<0)  node.left=put(node.left,key,val);
        else node.val=val;
        node.size=size(node.left)+size(node.right)+1;
        return node; 
    }

    public void show(){show(root);}

    private void show(Node node){
        if (node==null) return;
        show(node.left);
        System.out.println(node.key+"");
        show(node.right);
    }

    public Key min(){return min(root).key;}
    private Node min(Node node){
        if (node.left==null){return node;}
        return min(node.left);
    }
    public Key max(){return max(root).key;}
    private Node max(Node node){
        if (node.right==null){return node;}
        return max(node.right);
    }

    public Key floor(Key key){
        Node x= floor(root,key);
        if (x==null) return null;
        else return x.key;
    }
    private Node floor(Node node,Key key){
        if (node==null) return null;
        int cmp=key.compareTo(node.key);
        if (cmp==0) return node;
        if (cmp<0) return floor(node.left,key);
        Node t=floor(node.right,key);
        if (t!=null) return t;
        else return node;
    }
    

    public Key ceiling(Key key){
        Node x= ceiling(root,key);
        if (x==null) return null;
        else return x.key;
    }
    private Node ceiling(Node node,Key key){
        if (node==null) return null;
        int cmp=key.compareTo(node.key);
        if (cmp==0) return node;
        if (cmp>0) return ceiling(node.right,key);
        Node t=ceiling(node.left, key);
        if (t!=null) return t;
        return node;
    }

    public int rank(Key key){return rank(root,key);}
    private int rank(Node node,Key key){
        if (node==null) return 0;
        int cmp=key.compareTo(node.key);
        if (cmp==0) return size(node.left);
        else if (cmp<0) return rank(node.left,key);
        else return size(node.left)+1+rank(node.right,key);
    }

    public Key select(int k){
        return select(root,k).key;
    }
    private Node select(Node node,int k){
        if (node==null) return null;
        int t=size(node.left);
        if (k==t) return node;
        else if (k<t) return select(node.left,k);
        else return select(node.right,k-t-1);
    }

    public void deleteMin(){
        root=deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left==null) return node.right;
        node.left=deleteMin(node.left);
        node.size=size(node.left)+1+size(node.right);
        return node;        
    }

    public void deleteMax(){
        root=deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right==null) return node.left;
        node.right=deleteMin(node.right);
        node.size=size(node.left)+1+size(node.right);
        return node;        
    }

    public void delete(Key key){
        root=delete(root,key);
    }
    private Node delete(Node node,Key key){
        if (node==null) return null;
        int cmp=key.compareTo(node.key);
        if (cmp<0) node.left=delete(node.left,key);
        else if (cmp>0) node.right=delete(node.right,key);
        else{
            if (node.left==null) return node.right;
            if (node.right==null) return node.left;
            Random r = new Random();
            int ran = r.nextInt(1);
            if (ran==0){
                Node t=node;
                node=min(node.right);
                node.right=deleteMin(t.right);
                node.left=t.left;
            }
            else {
                Node t=node;
                node=max(node.left);
                node.left=deleteMax(t.left);
                node.right=t.right;
            }            
        }
        node.size=size(node.left)+1+size(node.right);
        return node;
    }

    public Iterable<Key> keys(){return keys(min(),max());}
    private Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue=new LinkedList<Key>();
        keys(root,queue,lo,hi);
        return queue;
    }
    private void keys(Node node,Queue<Key> queue,Key lo,Key hi){
        if (node==null) return;
        int cmplo=lo.compareTo(node.key);
        int cmphi=hi.compareTo(node.key);
        if (cmplo<0) keys(node.left,queue,lo,hi);
        if (cmphi>=0 && cmplo<=0) queue.offer(node.key);
        if (cmphi>0) keys(node.right,queue,lo,hi);
    }

    public static void main(String[] args){
        int N=100;
        BST bst=new BST<>();
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
            bst.put(ran1, strran);
        }

        //System.out.println(bst.get(pivot));
        //bst.show();
        //System.out.println(bst.min());
        //System.out.println(bst.max());
        //System.out.println(bst.floor(78));
        //System.out.println(bst.ceiling(78));
        //System.out.println(bst.rank(101));
        //System.out.println(bst.size());
        //System.out.println(bst.select(5));
        //bst.deleteMin();
        //bst.deleteMax();
        //bst.delete(pivot);
        //System.out.println(pivot);
        //bst.show();
        for (Object key:bst.keys(50,75)) System.out.println(key);
    }
}