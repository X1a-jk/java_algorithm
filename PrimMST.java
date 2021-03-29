import java.util.Random;;
public class PrimMST{
    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightGraph G){
        marked=new boolean[G.V()];
        edgeTo=new Edge[G.V()];
        distTo=new double[G.V()];
        pq=new IndexMinPQ<>(G.V());
        for (int i=0;i<G.V();i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[0]=0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()){visit(G,pq.delMin());}
    }

    private void visit(EdgeWeightGraph G, int v){
        marked[v]=true;
        for (Edge edge:G.adj(v)){
            int w=edge.other(v);
            if (marked[w]) continue;
            if (edge.weight()<distTo[w]){
                distTo[w]=edge.weight();
                edgeTo[w]=edge;
                if (pq.contains(w)) pq.replace(w,distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges(){
        Queue<Edge> queue=new Queue<>();
        for (int i=0;i<edgeTo.length;i++){
            if (edgeTo[i]==null) continue;
            queue.enqueue(edgeTo[i]);
        }
        return queue;
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
        for (Edge edge:this.edges()){
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

        PrimMST prim=new PrimMST(g);

        System.out.println(prim.showMinTree());
        System.out.println(prim.weight());
    }
}