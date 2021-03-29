import java.util.Random;
public class EdgeWeightGraph{
    private int V;
    private int E=0;
    private Bag<Edge>[] adj;

    public EdgeWeightGraph(int v){
        this.V=v;
        adj=(Bag<Edge>[]) new Bag[v];
        for (int i=0;i<v;i++){
            adj[i]=new Bag<Edge>();
        }
    }

    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(Edge edge){
        int v=edge.either();int w=edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public String toString(){
        String s=V+"vertices"+"  "+E+"edges\n";
        for (int v=0;v<V;v++){
            s+=v+":";
            for (Edge edge:this.adj(v)){
                int w=edge.other(v);
                s+=w+"("+String.format("%.2f",edge.weight())+"),";
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=10;
        EdgeWeightGraph g=new EdgeWeightGraph(V);
        for (int i=0;i<2*V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            double weight=r.nextDouble();
            Edge edge=new Edge(w,v,weight);
            g.addEdge(edge);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
    }
    
}