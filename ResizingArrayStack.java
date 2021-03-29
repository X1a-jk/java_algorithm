import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T> {
    private T[] a = (T[]) new Object[1]; //存放元素T
    private int size = 0; //T的个数

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int max){
        T[] temp = (T[]) new Object[max];
        for(int i = 0 ; i < size; i++){
            temp[i]=a[i];
        }
        a = temp;
    }

    public void push(T t){
        if(size == a.length){
            resize(2 * a.length); //当数据的个数和数组长度相等的时候，扩容到原来的两倍
        }
        a[size ++] = t;
    }

    public T pop(){
        //T t = a[size - 1];
        //size --;
        T t = a[--size];
         a[size] = null;
        if(size > 0 && size == a.length/4){
            resize(a.length/2); //元素个数达到数组的1/4时候，比如原来length是8 当pop弹栈时，size减少到2时候进行缩减至1/2也就是4个
        }
        return t;
    }

    //@Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements  Iterator<T>{
        private int i = size;
        public boolean hasNext(){
            return i > 0;
        }
        public  T next(){
            size --;
            return a[size];
        }
        public void remove(){}
    }
    public static void main(String[] args) {
        ResizingArrayStack<Integer> integers = new ResizingArrayStack<Integer>();
        for(int i = 0 ; i < 10 ; i++ ){
            integers.push(i);
        }
       integers.pop();
       while (integers.iterator().hasNext()){
           int a = integers.iterator().next();
           System.out.print(a);
       }
    }
}