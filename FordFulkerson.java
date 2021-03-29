import java.util.Random;
public class FordFulkerson{
    private boolean marked[];
    private FlowEdge edgeTo[];
    private double value;

    public FordFulkerson(FlowNetWork G,int s,int t){
        while (hasAugmentingPath(G, s, t)){
            double bottle=Double.POSITIVE_INFINITY;
            for (int v=t;v!=s;v=edgeTo[v].other(v)){
                bottle=Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            for (int v=t;v!=s;v=edgeTo[v].other(v)){
                edgeTo[v].addResidualFlowTo(v, bottle);
                //System.out.println(edgeTo[v]);
            }
            value+=bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetWork G,int s,int t){
        marked=new boolean[G.V()];
        edgeTo=new FlowEdge[G.V()];
        Queue<Integer> queue=new Queue<Integer>();
        queue.enqueue(s);
        marked[s]=true;

        while (!queue.isEmpty()){
            int current=queue.dequeue();
            for (FlowEdge edge:G.adj(current)){
                int point=edge.other(current);
                while (!marked[point] && edge.residualCapacityTo(point)>0){
                    marked[point]=true;
                    edgeTo[point]=edge;
                    queue.enqueue(point);
                }
            }
        }
        return marked[t];
    }

    public double value(){return value;}
    public boolean inCut(int v){return marked[v];}

    public static void main(String[] args){
        //Random r = new Random();
        //int V=10;
        FlowNetWork g=new FlowNetWork(6);
        // for (int i=0;i<4*V;i++){
        //     int w=r.nextInt(V-1);
        //     int v=r.nextInt(V-1);
        //     double capacity=r.nextDouble();
        //     FlowEdge edge=new FlowEdge(w,v,capacity);
        //     g.addEdge(edge);
        // }
        int[] v={0,0,1,1,2,2,3,4};
        int[] w={1,2,3,4,3,4,5,5};
        double[] capacity={2.0,3.0,3.0,1.0,1.0,1.0,2.0,3.0};

        for (int i=0;i<v.length;i++){
            FlowEdge edge=new FlowEdge(v[i],w[i],capacity[i]);
            g.addEdge(edge);
        }        
        
        //for (int w:g.adj(1)) System.out.println(w+"");
        System.out.println(g);

        FordFulkerson ff=new FordFulkerson(g, 0, 5);
        System.out.println(ff.value());
    }
}