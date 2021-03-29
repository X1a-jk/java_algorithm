import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//extends SymbolTable<Key, Value>
//二分搜索，基于排序数组
public class RedBlackBST<Key extends Comparable<Key>,Value>  {
    private Node root;
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int size;
        private boolean color;        

        public Node(Key key, Value val, int size, boolean color){
            this.key=key;this.val=val;this.size=size;this.color=color;
        }
    }

    private boolean isEmpty(){return size()==0;}

    private boolean isRed(Node node){
        if (node==null) return false;
        return node.color==RED;
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

    private Node rotateLeft(Node node){
        Node x=node.right;
        node.right=x.left;
        x.left=node;
        x.color=node.color;
        node.color=RED;
        x.size=node.size;
        node.size=size(node.left)+1+size(node.right);
        return x;
    }
    private Node rotateRight(Node node){
        Node x=node.left;
        node.left=x.right;
        x.right=node;
        x.color=node.color;
        node.color=RED;
        x.size=node.size;
        node.size=size(node.left)+1+size(node.right);
        return x;
    }
    private Node paint(Node node){
        node.left.color=BLACK;
        node.right.color=BLACK;
        node.color=RED;
        return node;
    }

    public void put(Key key,Value val){
        root=put(root,key,val);
        root.color=BLACK;
    }

    private Node put(Node node,Key key,Value val){
        if (node==null) return new Node(key,val,1,RED);
        if (isRed(node.left) && isRed(node.right)) node=paint(node);
        int cmp=key.compareTo(node.key);
        if (cmp>0)  node.right=put(node.right,key,val);
        else if (cmp<0)  node.left=put(node.left,key,val);
        else node.val=val;

        //if (isRed(node.left) && isRed(node.right)) node=paint(node);
        if (isRed(node.right) && !isRed(node.left)) node=rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node=rotateRight(node);
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

    void moveflipColors(Node h ){   //  这是用于删除节点的flipColor方法，该方法用于节点的合并，将父节点中的部分给与子节点
        h.color = BLACK;            
        h.left.color = RED;       
        h.right.color = RED;
    }

    private Node moveRedLeft(Node h){
        /**
         * 当前节点的左右子节点都是2-节点，左右节点需要从父节点中借一个节点
         * 如果该节点的右节点的左节点是红色节点，说明兄弟节点不是2-节点，可以从兄弟节点中借一个
         */
        moveflipColors(h);     // 从父节点中借一个
        if(isRed(h.right.left)){    // 判断兄弟节点，如果是非红节点，也从兄弟节点中借一个
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            //moveflipColors(h);  //  在从兄弟节点借了一个以后，我们就需要还一个节点给父节点了，因为一开始从父节点那里借了一个
        }
        return h;
    }
    
    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;   // 如果根节点的左右子节点是2-节点，我们可以将根设为红节点，这样才能进行后面的moveRedLeft操作，因为左子要从根节点借一个
        }
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;  // 借完以后，我们将根节点的颜色复原
    }
    
    private Node deleteMin(Node x){
        if(x.left == null) return null;
        if(!isRed(x.left) && !isRed(x.left.left))    // 判断x的左节点是不是2-节点
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);  //   解除临时组成的4-节点
    }
    
    private Node balance(Node h){
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);
        if (isRed(h.left) && isRed(h.right))    paint(h);
        h.size = size(h.left)+size(h.right)+1;
        return h;
    }

    private Node moveRedRight(Node h){
        moveflipColors(h);
        if(isRed(h.left.left)){         // 在这里对于兄弟节点的判断都是.left，因为红色节点只会出现在左边
            h=rotateRight(h);
            //moveflipColors(h);
        }
        return h;
    }
    public void deleteMax(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }
    
    private Node deleteMax(Node h){
        if(isRed(h.left)){             
        /**
         * 这里比deleteMin多了一步操作，因为右子节点从父节点中获得节点的时候，我们需要将左边节点给于到右边节点，如果我们不移动的话，会破坏树的平衡
         *          5,6
         *      1,2     9       对于所展示的这个红黑树，如果不把5从左边移到右边的话，我们会直接删除9，这样会导致树的不平衡，因为红节点总是在左边的，我们进行删除操作的时候，直接将结点给予，只需要改变颜色即可，不需要移动
         *                      对于红黑树而言，6是黑结点，再删除的时候，是不需要移动的，我们移动的是5这样的红结点
         *      
        */                                         
            h = rotateRight(h);
        }
        if(h.right == null){
            return null;
        }
        if(!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(Key key){
        if(!isRed(root.left)&& !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root,key);
        if (!isEmpty()) root.color = BLACK;
    }
    
    private Node delete(Node h, Key key){
        int cmp=key.compareTo(h.key);
        if(cmp<0){          // 当目标键小于当前键的时候，我们做类似于寻找最小是的操作，向树左边移动，合并父子结点来消除2-结点
            if(h.left == null){
                return null;
            }
            if(!isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left,key);
        }else{                  // 当目标键大于当前键的时候，我们向右移动，并做与deleteMax相同的操作，如果相同的话，我们使用和二叉树的删除一样的操作，获取当前键的右子树的最小健，然后交换，并将目标键删除
            if(isRed(h.left)){
                h = rotateRight(h);
            }
            if(cmp==0 && h.right == null){    // 我们没有找到目标键，我们将其删除
                return null;
            }
            if(!isRed(h.right) && !isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if (cmp==0) {
                h.val = get(h.right,min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right,key);
        }
        return balance(h);
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
        RedBlackBST bst=new RedBlackBST<>();
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
        bst.show();
        //System.out.println(bst.min());
        //System.out.println(bst.max());
        //System.out.println(bst.floor(78));
        //System.out.println(bst.ceiling(78));
        //System.out.println(bst.rank(101));
        //System.out.println(bst.size());
        //System.out.println(bst.select(5));
        bst.deleteMin();
        bst.deleteMax();
        bst.delete(pivot);
        System.out.println(pivot);
        bst.show();
        for (Object key:bst.keys(50,75)) System.out.println(key);
    }
}