import java.util.Iterator;
public class DoubleNode<Item> implements Iterable<Item>{
    private class Node{
        Item item;
        Node former;
        Node next;
    }
    private int size;
    private Node first; private Node last;

    public boolean isEmpty(){return size==0;}
    public int size(){return size;}

    public void pushforward(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        if (oldfirst!=null){oldfirst.former=first;}
        size++;
        if (size()==1){last=first;}
    }

    public void pushbackward(Item item){
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.former=oldlast;
        if (oldlast!=null){oldlast.next=last;}
        //oldlast.next=last;
        size++;
    }
    public Item popforward(){
        Item item=first.item;
        first=first.next;
        first.former=null;
        size--;
        return item;
    }
    public Item popbackward(){
        Item item=last.item;
        last=last.former;
        last.next=null;
        size--;
        return item;
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
    public Node find(Item item){
        Node current=first;
        Node ans=new Node();
        while (current.next!=null){
            Item a=current.item;
            if (a.equals(item)){
                ans=current;
            }
            current=current.next;
            //System.out.println(current.next);
        }
        return ans;
    }
    public void insertforward(Item item1,Item item2){
        Node want=find(item1);
        Node insert=new Node();
        insert.item=item2;
        insert.next=want;
        insert.former=want.former;
        want.former.next=insert;
        want.former=insert;
        size++;
    }
    public void insertbackward(Item item1,Item item2){
        Node want=find(item1);
        Node insert=new Node();
        insert.item=item2;
        insert.next=want.next;
        insert.former=want;
        want.next.former=insert;
        want.next=insert;
        size++;
    }
    public void delete(Item item){
        Node target=find(item);
        if (target.former==null){
            Item a=popforward();
        }
        else{
            if (target.next==null){
                Item b=popbackward();
            }
            else{
                target.former.next=target.next;
                target.next.former=target.former;
                size--;
            }
        }
    }

    public static void main(String[] args){
        DoubleNode<String> s =new DoubleNode<String>();
        for (int i=0;i<10;i++){
            s.pushforward(Integer.toString(i));
        }
        for (int j=10;j<20;j++){
            s.pushbackward(Integer.toString(j));
        }
        //for (String item:s){System.out.println(item);}
        for (int i=0;i<5;i++){
            s.popforward();
        }
        for (int i=0;i<5;i++){
            s.popbackward();
        }
        //for (String item:s){System.out.println(item);}
        s.delete("10");
        //System.out.println(s.find("12"));
        s.insertforward("11","5");
        s.insertbackward("4", "0");
        for (String item:s){System.out.println(item);}
    }
}