import java.util.Random;
public class EdgeWeightDigraph{
    private int V;
    private int E=0;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightDigraph(int v){
        this.V=v;
        adj=(Bag<DirectedEdge>[]) new Bag[v];
        for (int i=0;i<v;i++){
            adj[i]=new Bag<DirectedEdge>();
        }
    }

    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(DirectedEdge edge){
        int v=edge.from();
        adj[v].add(edge);
        E++;
    }
    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }
    public String toString(){
        String s=V+"vertices"+"  "+E+"edges\n";
        for (int v=0;v<V;v++){
            s+=v+":";
            for (DirectedEdge edge:this.adj(v)){
                int w=edge.to();
                s+=w+"("+String.format("%.2f",edge.weight())+"),";
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=10;
        EdgeWeightDigraph g=new EdgeWeightDigraph(V);
        for (int i=0;i<2*V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            double weight=r.nextDouble();
            DirectedEdge edge=new DirectedEdge(w,v,weight);
            g.addEdge(edge);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
    }
    
}