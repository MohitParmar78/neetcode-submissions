class pair{
    int node;
    int cost;

    pair(int node,int cost){
        this.node=node;
        this.cost=cost;
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<pair>> adj=new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] flight:flights){
            adj.get((flight[0])).add(new pair(flight[1],flight[2]));
        }

        Queue<pair> q=new LinkedList<>();

        int[] dist=new int[n];
        Arrays.fill(dist,(int)1e8);

        q.offer(new pair(src,0));
        dist[src]=0;

        for(int i=0;i<=k;i++){
            int size=q.size();

            for(int j=0;j<size;j++){
                pair temp=q.poll();

                int node=temp.node;
                int old_cost=temp.cost;

                for(pair neigh:adj.get(node)){
                    int new_cost=old_cost+neigh.cost;
                    if(new_cost<dist[neigh.node]){
                        dist[neigh.node]=new_cost;
                        q.offer(new pair(neigh.node,new_cost));
                    }
                }
            }
        }
        if(dist[dst]!=(int)1e8){
            return dist[dst];
        }
        return -1;
    }
}
