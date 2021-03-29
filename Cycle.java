import java.util.Random;
public class Cycle{
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G){
        marked=new boolean[G.V()];
        for (int s=0;s<G.V();s++){
            if (!marked[s]){
                dfs(G,s,s);
            }
        }
    }

    private void dfs(Graph G,int v,int u){
        marked[v]=true;
        for (int w:G.adj(v)){
            if (!marked[w]){
                dfs(G,w,v);
            }
            else if (w!=u) hasCycle=true;
        }
    }

    public boolean hasCycle(){return hasCycle;}

    public static void main(String[] args){
        Random r = new Random();
        int V=10;
        Graph g=new Graph(V);
        for (int i=0;i<2*V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            if (v==w) continue;
            g.addEdge(v,w);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
        Cycle cy=new Cycle(g);
        System.out.println(cy.hasCycle());
    }

}