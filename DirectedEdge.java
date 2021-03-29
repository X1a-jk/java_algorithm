import java.util.Random;

public class DirectedEdge implements Comparable<DirectedEdge>{
    private final int v,w;
    private final double weight;

    public DirectedEdge(int v,int w,double weight){
        this.v=v;this.w=w;this.weight=weight;
    }

    public double weight(){return weight;}

    public int from(){return v;}

    public int to(){return w;}

    public int compareTo(DirectedEdge that){
        if (this.weight()<that.weight()) return -1;
        else if (this.weight()>that.weight()) return -1;
        else return 0;
    }

    public String toString(){
        return String.format("%d->%d %.2f",v,w,weight);
    }

    public static void main(String[] args){
        DirectedEdge edge=new DirectedEdge(2,1,10.0);
        int v=edge.from();System.out.println(v);
        int k=edge.to();System.out.println(k);
        System.out.println(edge);
    }
}