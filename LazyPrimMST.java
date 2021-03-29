import java.util.Random;
public class LazyPrimMST{
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightGraph G){
        marked=new boolean[G.V()];
        mst=new Queue<Edge>();
        pq=new MinPQ<Edge>(G.E());
        int s=0;
        visit(G,s);
        while (!pq.isEmpty()){
            Edge edge=pq.delMin();
            int v=edge.either();int w=edge.other(v);
            if (marked[v] && marked[w]) continue;
            else if (marked[v] && !marked[w]) {mst.enqueue(edge);visit(G,w);}
            else {mst.enqueue(edge);visit(G,v);}
        }
    }

    private void visit(EdgeWeightGraph G,int v){
        marked[v]=true;
        for (Edge edge:G.adj(v)){
            int w=edge.other(v);
            if (!marked[w]) pq.insert(edge);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
    public String showMinTree(){
        String s="";
        for (Edge edge:this.edges()){
            s+=edge+"\n";
        }
        return s;
    }
    public double weight(){
        double weight=0.0;
        for (Edge edge:mst){
            weight+=edge.weight();
        }
        return weight;
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

        LazyPrimMST prim=new LazyPrimMST(g);

        System.out.println(prim.showMinTree());
    }
}