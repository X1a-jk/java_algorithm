import java.util.Random;
public class FlowNetWork{
    private int V;
    private int E=0;
    private Bag<FlowEdge>[] adj;

    public FlowNetWork(int v){
        this.V=v;
        adj=(Bag<FlowEdge>[]) new Bag[v];
        for (int i=0;i<v;i++){
            adj[i]=new Bag<FlowEdge>();
        }
    }

    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(FlowEdge edge){
        int v=edge.from();
        adj[v].add(edge);
        E++;
    }
    public Iterable<FlowEdge> adj(int v){
        return adj[v];
    }
    public String toString(){
        String s=V+" vertices"+"  "+E+" edges\n";
        for (int v=0;v<V;v++){
            s+=v+":";
            for (FlowEdge edge:this.adj(v)){
                int w=edge.to();
                s+=w+", flow:"+String.format("%.2f",edge.flow())+", "
                +"capacity:"+String.format("%.2f",edge.capacity())+", ";
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=10;
        FlowNetWork g=new FlowNetWork(V);
        for (int i=0;i<2*V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            double capacity=r.nextDouble();
            FlowEdge edge=new FlowEdge(w,v,capacity);
            g.addEdge(edge);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
    }
    
}