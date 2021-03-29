import java.util.Random;

public class FlowEdge implements Comparable<FlowEdge>{
    private final int v,w;
    private double flow;
    private final double capacity;

    public FlowEdge(int v,int w,double capacity){
        this.v=v;this.w=w;this.capacity=capacity;
        this.flow=0.0;
    }

    public double flow(){return flow;}

    public double capacity(){return capacity;}

    public int from(){return v;}

    public int to(){return w;}

    public int other(int i){
        if (i==v) return w;
        else if (i==w) return v;
        else throw new RuntimeException();
    }

    // public int compareTo(DirectedEdge that){
    //     if (this.weight()<that.weight()) return -1;
    //     else if (this.weight()>that.weight()) return -1;
    //     else return 0;
    // }

    public String toString(){
        return String.format("%d->%d",v,w)+" capacity:"+String.format("%.2f",this.capacity())
        +" flow:"+String.format("%.2f",this.flow());
    }

    public double residualCapacityTo(int i){
        if (i==v) return flow;
        else if (i==w) return capacity-flow;
        else throw new RuntimeException();
    }

    public void addResidualFlowTo(int i,double delta){
        if (i==v) flow-=delta;
        else if (i==w) flow+=delta;
        else throw new RuntimeException();
    }

    public static void main(String[] args){
        FlowEdge edge=new FlowEdge(2,1,10.0);
        int v=edge.from();System.out.println(v);
        int k=edge.to();System.out.println(k);
        System.out.println(edge);
    }
}