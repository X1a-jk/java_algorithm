public class GraphSample{

    public static int degree(Graph G,int V){
        int degree=0;
        for (int w:G.adj(V)) degree++;
        return degree;
    }
    public static int maxDegree(Graph G){
        int max=0;
        for (int V=0;V<G.V();V++){
            if (degree(G,V)>max) max=degree(G,V);
        }
        return max;
    }
    public static int SelfLoops(Graph G){
        int count=0;
        for (int V=0;V<G.V();V++){
            for (int w:G.adj(V)){
                if (w==V) count++;
            } 
        }
        return count;
    }
    public String toString(){
        String s=V+"vertices"+E+"edges\n";
        for (int v=0;v<V;v++){
            s+=v+":";
            for (int w:this.adj(v)){
                s+=w+"";
            }
            s+="\n";
        }
    }
}