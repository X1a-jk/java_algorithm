import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
    private int N=0;
    private Node first;
    private Node last;
    private class Node{
        Item item;
        Node next;
    }

    public void enqueue(Item item){
        if (N==0){
            // Node oldfirst=first;
            first=new Node();
            first.item=item;
            last=first;
            N++;
        }
        else{
            Node oldlast=last;
            last=new Node();
            last.item=item;
            oldlast.next=last; 
            N++;
        }
    }
    public Item dequeue(){
        if (isEmpty()) return null;
        Item item=first.item;
        first=first.next;
        N--;
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
        Queue<Integer> s =new Queue<Integer>();
        for (int i=0;i<10;i++){
            s.enqueue(i);
        }
        
        for (int item:s){System.out.println(item + "");}
        //System.out.println(s.dequeue());
    }
}