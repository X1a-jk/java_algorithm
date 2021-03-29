import java.util.Random;

public class BreadthFirstPaths{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private Queue<Integer> queue;

    public BreadthFirstPaths(DiGraph G,int s){
        this.s=s;
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        queue=new Queue<Integer>();        
        bfs(G,s);        
    }

    public boolean marked(int v){return marked[v];}
    public boolean hasPathTo(int v){return marked[v];}

    private void bfs(DiGraph G,int s){
        queue.enqueue(s);        
        marked[s]=true;
        while(!queue.isEmpty()){
            int current=queue.dequeue();
            if (G.adj(current)==null) continue;
            for (int w:G.adj(current)){
                if (!marked(w)){
                    queue.enqueue(w);
                    edgeTo[w]=current;
                    marked[w]=true;
                }                
            }
        }
    }

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
        DiGraph g=new DiGraph(V);
        for (int i=0;i<2*V;i++){
            int w=r.nextInt(V-1);
            int v=r.nextInt(V-1);
            g.addEdge(v,w);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
        BreadthFirstPaths dfp=new BreadthFirstPaths(g,2);
        System.out.println(dfp.showPathTo(5));
    }

}