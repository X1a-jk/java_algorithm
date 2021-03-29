import java.util.Random;
import RedBlackBST;
public class BSTsample{
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

        System.out.println(bst.get(pivot));
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
        //for (Object key:bst.keys(50,75)) System.out.println(key);
    }
}