import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
    private int N=0;
    private Node first;
    private class Node{
        Item item;
        Node next;
    }

    public void push(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }
    public Item pop(){
        if (isEmpty()) return null;
        Item item=first.item;
        first=first.next;
        return item;
    }

    public int size(){return N;}

    public boolean isEmpty(){return N==0;}

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current=first;
        public boolean hasNext(){return current!=null;}
        public void remove(){};
        public Item next(){
            Item item=current.item;
            current=current.next;
            return item;
        }
    }

    public static void main(String[] args){
        Stack<String> s =new Stack<String>();
        for (int i=0;i<10;i++){
            s.push(Integer.toString(i));
        }
        //System.out.println(s);
        // for (int i=0;i<10;i++){
        //     System.out.println(s.pop());
        // }
        //s.delete(10);
        
        //for (String item:s){System.out.println(item + "");}
        System.out.println(s.pop());
    }
}