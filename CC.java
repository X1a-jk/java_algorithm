import java.util.Random;

public class CC{
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G){
        marked=new boolean[G.V()];
        id=new int[G.V()];

        for (int s=0;s<G.V();s++){
            if (!marked[s]){
                
                count++;
                dfs(G,s);
            }
        }
    }

    private void dfs(Graph G,int v){
        marked[v]=true;
        id[v]=count;
        for (int w:G.adj(v)){
            if (!marked(w)){
                dfs(G,w);
            }
        }
    }

    public boolean marked(int v){return marked[v];}
    public boolean hasPathTo(int v){return marked[v];}

    public boolean isConnected(int v,int w){
        return id[v]==id[w];
    }

    public int id(int v){return id[v];}

    public int count(){return count;}

    public Bag<Integer> show(int ID){
        Bag<Integer> bag=new Bag<Integer>();
        for (int v=0;v<id.length;v++){
            if (id[v]==ID) bag.add(v);
        }
        return bag;
    }

    public static void main(String[] args){
        Random r = new Random();
        int V=10;
        Graph g=new Graph(V);
        for (int i=0;i<V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            g.addEdge(v,w);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
        CC cc=new CC(g);
        System.out.println(cc.count());
        Bag<Integer> bg=cc.show(2);
        for (int w:bg) System.out.println(w+"");
    }



}