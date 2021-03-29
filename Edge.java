import java.util.Random;

public class Edge implements Comparable<Edge>{
    private final int v,w;
    private final double weight;

    public Edge(int v,int w,double weight){
        this.v=v;this.w=w;this.weight=weight;
    }

    public double weight(){return weight;}

    public int either(){return v;}

    public int other(int i){
        if (i==v) return w;
        else if (i==w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge that){
        if (this.weight()<that.weight()) return -1;
        else if (this.weight()>that.weight()) return -1;
        else return 0;
    }

    public String toString(){
        return String.format("%d-%d %.2f",v,w,weight);
    }

    public static void main(String[] args){
        Edge edge=new Edge(2,1,10.0);
        int v=edge.either();System.out.println(v);
        int k=edge.other(v);System.out.println(k);
        System.out.println(edge);
    }
}