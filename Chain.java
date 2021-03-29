import java.util.Iterator;
import java.util.Scanner;
public class Chain<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){return first==null;}
    public int size(){return N;}
    public void push(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }
    public Item pop(){
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }
    public void delete(int k){ //index=k
        if (k>=N){
            System.out.println("index out of range");
        }    
        else {
            if (k==0){
                first=first.next;
                N--;
            }
            else{
                Node previous=first;
                Node current=previous.next;
                Node suivant=current.next;
                for (int i=0;i<k-1;i++){
                    previous=current;
                    current=previous.next;
                    suivant=current.next;
                    //previous.next=suivant;
                }
                previous.next=suivant;
                current=null;
                N--;
            }
        }
    }
    public boolean find(Item k){
        Node current=first;
        while (current.next!=null){
            Item a=current.item;
            if (a.equals(k)){   //.equals
                return true;
            }
            current=current.next;
        }
        return false;
    }
    public void removeAfter(Item s){
        Node current=first;
        while (current.next!=null){
            Item a=current.item;
            if (a.equals(s)){   //.equals
                current.next=null;
                break;
            }
            current=current.next;
        }
        N--;
    }
    public void insertAfter(Item x,Item y){
        Node current=first;
        while (current.next!=null){
            Item a=current.item;
            if (a.equals(x)){   //.equals
                Node additional=new Node();
                additional.item=y;
                additional.next=current.next;
                current.next=additional;
                break;
            }
            current=current.next;
        }
        N++;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements  Iterator<Item>{
        private Node current=first;
        public boolean hasNext(){return current!=null;}
        public void remove(){}
        public Item next(){
            Item item =current.item;
            current=current.next;
            return item;
        }
    }
    public static void main(String[] args){
        Chain<String> s =new Chain<String>();
        for (int i=0;i<10;i++){
            s.push(Integer.toString(i));
        }
        //System.out.println(s);
        // for (int i=0;i<10;i++){
        //     System.out.println(s.pop());
        // }
        //s.delete(10);
        System.out.println(s.find("5"));
        s.removeAfter("5");
        s.insertAfter("7", "0");
        for (String item:s){System.out.println(item + '1');}
        
    }
}