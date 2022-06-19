class Dijkestra{
    public class Edge{
        int v;
        int w;
        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }
    public class Pair{
        int node;
        int wsf;
        Pair(int node,int wsf){
            this.node=node;
            this.wsf=wsf;
        }
    }
    public int dijkestra(ArrayList<Edge>[] graph,int src){
        int dis[]=new int[graph.length];
        int V=graph.length;
        boolean vis[]=new boolean[V];
        PriorityQueue<pair> pq=new PriorityQueue<>((Pair a, Piar b)->{
            return a.wsf-b.wsf;
        });
        pq.add(new pair(src,0));
        dis[src]=0;
        while(pq.size()>0){
            Pair t=pq.remove();
            int node=t.node;
            int wsf=t.wsf;
            if(vis[node])
                continue;
            dis[node]=wsf;
            vis[node]=true;// we will mark visited on removal time
            for(Edge e:graph[node]){
                int v=e.v;
                int w=e.w;
                if(!vis[v]){
                    pq.add(new Pair(v,wsf+w));
                }
            }    
        }
        return dis;
    }
    //without visited array
    public int dijkestra_better(ArrayList<Edge>[] graph,int src){
        int dis[]=new int[graph.length];
        int V=graph.length;
        Arrays.fill(dis,(int)(1e8));
        PriorityQueue<pair> pq=new PriorityQueue<>((Pair a, Piar b)->{
            return a.wsf-b.wsf;
        });
        pq.add(new pair(src,0));
        dis[src]=0;
        while(pq.size()>0){
            Pair t=pq.remove();
            int node=t.node;
            int wsf=t.wsf;
            if(dis[node]<wsf)
                continue;
            for(Edge e:graph[node]){
                int v=e.v;
                int w=e.w;
                if(dis[v]>wsf+w){
                    dis[v]=wsf+w;
                    pq.add(new Pair(v,wsf+w));
                }
            }    
        }
        return dis;
    }
    
}