import java.util.Iterator;

public class Parenthese<Item> implements Iterable<Item>{
    private Item[] a = (Item[]) new Object[1]; //存放元素T
    private int size = 0; //T的个数

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for(int i = 0 ; i < size; i++){
            temp[i]=a[i];
        }
        a = temp;
    }

    public void push(Item t){
        if(size == a.length){
            resize(2 * a.length); //当数据的个数和数组长度相等的时候，扩容到原来的两倍
        }
        a[size ++] = t;
    }

    public Item pop(){
        //T t = a[size - 1];
        //size --;
        Item t = a[--size];
         a[size] = null;
        if(size > 0 && size == a.length/4){
            resize(a.length/2); //元素个数达到数组的1/4时候，比如原来length是8 当pop弹栈时，size减少到2时候进行缩减至1/2也就是4个
        }
        return t;
    }

    public Iterator<Item> iterator() {
        return new ParentheseStack();
    }
    private class ParentheseStack implements  Iterator<Item>{
        private int i=size;
        public boolean hasNext(){return i>0;}
        public Item next(){return a[size--];}
        public void remove(){}
    }
    public static boolean suits(String a, String b){
        if(a=="["){return b=="]";}
        if(a=="("){return b==")";}
        if(a=="{"){return b=="}";}
    }

    public static void main(String[] args) {
        Parenthese<String> parenthese=new Parenthese<>();
        String[] words={"[","(",")","]","{","}","{","[","(,",")","(",")","]","(",")","}"};
        //String[] words={"[","]"};
        for (String item:words){
            if (item=="[" || item=="(" || item=="{"){
                parenthese.push(item);
            }
            else {
                String item2 = parenthese.pop();
                boolean a=suits(item,item2);
                System.out.println(a);
            System.out.println(item);
            }
        }
    }
}


