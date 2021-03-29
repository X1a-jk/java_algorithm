import java.util.Random;;
public class Dijkstra{
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public Dijkstra(EdgeWeightDigraph G,int s){
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        pq=new IndexMinPQ<>(G.V());
        for (int i=0;i<G.V();i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[s]=0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()){relax(G,pq.delMin());}
    }

    private void relax(EdgeWeightDigraph G, int v){
        for (DirectedEdge edge:G.adj(v)){
            int w=edge.to();
            if (distTo[w]>distTo[v]+edge.weight()){
                distTo[w]=distTo[v]+edge.weight();
                edgeTo[w]=edge;
            }
            if (pq.contains(w)) pq.replace(w,distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v){return distTo[v];}

    public boolean hasPathTo(int v){return distTo(v)<Double.POSITIVE_INFINITY;}

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> st=new Stack<>();
        for (DirectedEdge edge=edgeTo[v];edge!=null;edge=edgeTo[edge.from()]){
            st.push(edge);
        }
        return st;
    }

    public String showMinTree(int v){
        String s="";
        for (DirectedEdge edge:this.pathTo(v)){
            s+=edge+"\n";
        }
        return s;
    }
    public double weight(int v){
        double weight=0.0;
        for (DirectedEdge edge:this.pathTo(v)){
            weight+=edge.weight();
        }
        return weight;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=6;
        EdgeWeightDigraph g=new EdgeWeightDigraph(V);
        for (int i=0;i<V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            double weight=r.nextDouble();
            DirectedEdge edge=new DirectedEdge(w,v,weight);
            g.addEdge(edge);
        }

        Dijkstra dijkstra=new Dijkstra(g,0);

        System.out.println(dijkstra.hasPathTo(5));
        //System.out.println(dijkstra.weight(5));
    }
}