class DSU {
    public static class Edge{
        int v;
        int w;

        Edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }

    int[] par;
    int[] size;

    public int findPar(int u){
        if(par[u]==u) return u;

        int p=findPar(par[u]);
        
        par[u]=p;

        return p;
    }

    public void merge(int p1, int p2){
        if(size[p1]>=size[p2]){
            par[p2]=p1;
            size[p1]+=size[p2];
        } else {
            par[p1]=p2;
            size[p2]+=size[p1];
        }
    }


    public ArrayList<Integer>[] createSpanningTree(int V, int[][] edges){
        ArrayList<Integer>[] graph=new ArrayList[V];

        for(int i=0; i<V; i++){
            graph[i]=new ArrayList<>();
        }

        par=new int[V];
        size=new int[V];

        for(int i=0; i<V; i++){
            par[i]=i;
            size[i]=1;
        }

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];

            int p1=findPar(u);
            int p2=findPar(v);

            if(p1!=p2){
                merge(p1,p2);
                addEdge(graph,u,v);
            }
        }

        return graph;

    }

    public void addEdge(ArrayList<Integer>[] graph, int u, int v){
        graph[u].add(v);
        graph[v].add(u);
    }

    public void addEdge_kruskal(ArrayList<Edge>[] mst, int u, int v, int w){
        mst[u].add(new Edge(v,w));
        mst[v].add(new Edge(u,w));
    }

    // kruskal Algorithm =============================================================================== 

    public ArrayList<Edge>[] kruskal(int[][] edges, int V){
        ArrayList<Edge>[] mst=new ArrayList[V];

        for(int i=0; i<V; i++){
            mst[i]=new ArrayList<>();
        }

        Arrays.sort(edges,(int[] a, int[] b)->{
            return a[2] - b[2];
        });

        par=new int[V];
        size=new int[V];

        int total_weight=0;

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];

            int p1=findPar(u);
            int p2=findPar(v);

            if(p1!=p2){
                merge(p1,p2);
                total_weight+=w;
                addEdge_kruskal(mst,u,v,w);
            }
        }

        System.out.println(total_weight);
        return mst;
    }

    public static void main(String[] args){

    }
}