Minimum Cost To Connect All Cities

There are n cities and there are roads in between some of the cities. Somehow all the roads are damaged simultaneously. We have to repair the roads to connect the cities again. There is a fixed cost to repair a particular road. Find out the minimum cost to connect all the cities by repairing roads.
7
8
0 1 10
1 2 10
2 3 10
0 3 40
3 4 2
4 5 3
5 6 3
4 6 8

solution-
=================================================================
import java.io.*;
import java.util.*;

public class Main {
  static class Edge implements Comparable<Edge> {
    int v;
    int wt;

    Edge(int nbr, int wt) {
      this.v = nbr;
      this.wt = wt;
    }

    @Override
    public int compareTo(Edge o) {
      return this.wt - o.wt;
    }
  }
static int par[];
  static int size[];
  
  public static int findPar(int u){
    if(par[u]==u)
        return u;
    int p=findPar(par[u]);
    par[u]=p;
    return p;
  }
  
  public static void merge(int p1,int p2){
      if(size[p1]>=size[p2]){
          par[p2]=p1;
          size[p1]+=size[p2];
      }else{
         par[p1]=p2;
         size[p2]+=size[p1];
      }
  }
  public static int krushkal(int[][] edges,int n){
      int cost=0;
      par=new int[n+1];
      size=new int[n+1];
      for(int i=0;i<=n;i++){
          par[i]=i;
          size[i]=1;
      }
      Arrays.sort(edges,(int a[],int[] b)->{
          return a[2]-b[2];
      });
      for(int edge[]:edges){
        int u=edge[0];
        int v=edge[1];
        int w=edge[2];
        int p1=findPar(u);
        int p2=findPar(v);
        if(p1!=p2){
            merge(p1,p2);
            cost+=w;
        }
      }
      return cost;
  } 


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vtces = Integer.parseInt(br.readLine());
    ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    for (int i = 0; i < vtces; i++) {
      graph.add(new ArrayList<>());
    }
    
    int edges = Integer.parseInt(br.readLine());
    int arr[][]=new int[edges][3];
    for (int i = 0; i < edges; i++) {
      String[] parts = br.readLine().split(" ");
      int v1 = Integer.parseInt(parts[0]);
      int v2 = Integer.parseInt(parts[1]);
      int wt = Integer.parseInt(parts[2]);
      graph.get(v1).add(new Edge(v2, wt));
      graph.get(v2).add(new Edge(v1, wt));
      arr[i][0]=v1;
      arr[i][1]=v2;
      arr[i][2]=wt;
    }
    System.out.println(krushkal(arr,vtces));

  }

}
