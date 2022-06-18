
Optimize Water Distribution
==========================================
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.

Example 1:

Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: 
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

Constraints:

1 <= n <= 10000
wells.length == n
0 <= wells[i] <= 10^5
1 <= pipes.length <= 10000
1 <= pipes[i][0], pipes[i][1] <= n
0 <= pipes[i][2] <= 10^5
pipes[i][0] != pipes[i][1]

==================================================================================================================================
Approach - 
what I will do is I will create an edge fom 0 to each node and then I will merge all node in the pipes array and will 
sort it according to weight. After that I can sure short apply MST on it.

====================================================================================================================================
solution -


import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] st = br.readLine().split(" ");
    int v = Integer.parseInt(st[0]);
    int e = Integer.parseInt(st[1]);

    int[] wells = new int[v];
    String[] words = br.readLine().split(" ");

    for (int i = 0; i < wells.length; i++) {
      wells[i] = Integer.parseInt(words[i]);
    }

    int[][] pipes = new int[e][3];
    for (int i = 0; i < e; i++) {
      String[] st1 = br.readLine().split(" ");
      pipes[i][0] = Integer.parseInt(st1[0]);
      pipes[i][1] = Integer.parseInt(st1[1]);
      pipes[i][2] = Integer.parseInt(st1[2]);

    }

    System.out.println(minCostToSupplyWater(v, wells, pipes));

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
  public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    int p1=pipes.length;
    int edges[][]=new int[p1+n][3];
    for(int i=0;i<pipes.length;i++){
        edges[i][0]=pipes[i][0];
        edges[i][1]=pipes[i][1];
        edges[i][2]=pipes[i][2];
        
    }
    int j=pipes.length;
    for(int i=0;i<wells.length;i++){
        edges[j][0]=0;
        edges[j][1]=i+1;
        edges[j][2]=wells[i];
        j++;
    }
    return krushkal(edges,n);
    
  }
}
