import java.util.Random;
public class Graph{
    private int V;
    private int E=0;
    private Bag<Integer>[] adj;

    public Graph(int v){
        this.V=v;
        adj=(Bag<Integer>[]) new Bag[v];
        for (int i=0;i<v;i++){
            adj[i]=new Bag<Integer>();
        }
    }

    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public String toString(){
        String s=V+"vertices"+"  "+E+"edges\n";
        for (int v=0;v<V;v++){
            s+=v+":";
            for (int w:this.adj(v)){
                s+=w+",";
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=10;
        Graph g=new Graph(V);
        for (int i=0;i<2*V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            g.addEdge(v,w);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
    }
    
}