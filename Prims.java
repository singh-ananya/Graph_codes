//(node, par,weight)
//prim's algo is same as dijkstra but instead it keeps record of weight rather than weight so far/
//we will mark at removal time
class Prims{
class Prims_pair{
    int node;
    int par;
    int weight;
    Prims_pair(int node,int par,int weight){
        this.node=node;
        this.par=par;
        this.weight=weight;
    }
}
class Edge{
    int v;
    int wt;
    Edge(int v,int wt){
        this.v=v;
        this.wt=wt;
    }
}
public addEdge(int u,int v,int wt,ArrayList<Edge>[] graph){
    graph[u].add(new Edge(v,w));
    graph[v].add(new Edge(u,w));
}
public ArrayList<Edge>[] Prims(ArrayList<Edge>[] graph,int src){
    int V=graph.length;
    boolean vis[]=new boolean[V];
    ArrayList<Edge>[] mst=new ArrayList[V];
    for(int i=0;i<V;i++){
        mst[i]=new ArrayList<>();
    }
    PriorityQueue<Prims_pair> pq=new PriorityQueue<>((Prims_pair a,Prims_pair b)->{
        return a.wt-b.wt;
    });
    pq.add(new Prims_pair(src,-1,0));
    while(pq.size()>0){
        Prims_pair p=pq.remove();
        int node=pq.node;
        int par=pq.par;
        int weight=pq.weight;
        if(vis[node])
            continue;
        vis[node]=true;
        if(par!=-1){
            addEdge(par,node,weight,mst);
        } 
        for(Edge e:graph[node]){
            int v=e.v;
            int wt=e.wt;
            if(vis[e.v]==false){
                pq.add(new Prims_pair(v,node,wt));
            }
        }   
    }
    return mst;
}
}