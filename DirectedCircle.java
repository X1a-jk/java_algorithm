import java.util.Random;
//import java.util.Stack;

public class DirectedCircle{
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversepost;
    private boolean[] marked;
    private int[] edgeTo;
    //private final int s;
    private Stack<Integer> st;
    private boolean[] onstack;

    public DirectedCircle(DiGraph G){
        pre=new Queue<Integer>();
        post=new Queue<Integer>();
        reversepost=new Stack<Integer>();
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        onstack=new boolean[G.V()];
        for (int v=0;v<G.V();v++){
            if (!marked(v)) dps(G,v);
        }
    }

    private void dps(DiGraph G,int v){
        pre.enqueue(v);
        marked[v]=true;
        onstack[v]=true;
        for (int w:G.adj(v)){
            if (this.hasCycle()) return;
            else if (!marked[w]){
                edgeTo[w]=v;
                dps(G,w);
            }
            else if (onstack[w]){
                st=new Stack<Integer>();
                for (int s=v;s!=w;s=edgeTo[s]){
                    st.push(s);
                }
                st.push(w);st.push(v);
            }
        }
        onstack[v]=false;
        post.enqueue(v);
        reversepost.push(v);
    }

    public boolean hasCycle(){return st!=null;}
    public boolean marked(int v){return marked[v];}
    public boolean hasPathTo(int v){return marked[v];}

    public Iterable<Integer> cycle(){return st;}
    public Iterable<Integer> pre(){return pre;}
    public Iterable<Integer> post(){return post;}
    public Iterable<Integer> reversepost(){return reversepost;}

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
            if (w==v) continue;
            g.addEdge(v,w);
        }
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);
        DirectedCircle dc=new DirectedCircle(g);
        if (dc.hasCycle()) {
            System.out.print("cycle:");
            for (int w:dc.cycle()) {                
                System.out.print(w+"-");
            }
            System.out.print("\n");
            System.out.print("pre:");
            for (int w:dc.pre()) {                
                System.out.print(w+"-");
            }
            System.out.print("\n");
            System.out.print("post:");
            for (int w:dc.post()) {                
                System.out.print(w+"-");
            }
            System.out.print("\n");
            System.out.print("reversepost:");
            for (int w:dc.reversepost()) {                
                System.out.print(w+"-");
            }
        }
    }
}