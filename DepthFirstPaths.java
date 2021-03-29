import java.util.Random;
//import java.util.Stack;

public class DepthFirstPaths{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    

    public DepthFirstPaths(Graph G,int s){
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        this.s=s;
        dps(G,s);
    }

    private void dps(Graph G,int v){
        marked[v]=true;
        for (int w:G.adj(v)){
            if (!marked[w]){
                edgeTo[w]=v;
                dps(G,w);
            }
        }
    }

    public boolean marked(int v){return marked[v];}
    public boolean hasPathTo(int v){return marked[v];}

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> st=new Stack<Integer>();
        for (int x=v;x!=s;x=edgeTo[x]){
            st.push(x);
        }
        st.push(s);
        return st;
    }

    public String showPathTo(int v){
        if (!hasPathTo(v)) return null;
        String str="Path from "+s+" to "+v+": ";
        for (int w:pathTo(v)){
            str+=w+"-";
        }
        return str;
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
        DepthFirstPaths dfp=new DepthFirstPaths(g,2);
        System.out.println(dfp.showPathTo(5));
    }
}