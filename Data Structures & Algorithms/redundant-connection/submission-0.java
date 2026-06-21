class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;

        int[] parent=new int[n+1];
        int[] rank=new int[n+1];

        for(int i=0;i<=n;i++){
            parent[i]=i;
        }

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];

            int ru=find(u,parent);
            int rv=find(v,parent);

            if(ru==rv) return new  int[]{u,v};

            if(rank[ru]<rank[rv]){
                parent[ru]=rv;
            }
            else if(rank[rv]<rank[ru]){
                parent[rv]=ru;
            }
            else{
                parent[ru]=rv;
                rank[rv]++;
            }
        }

        return new int[2];
    }

    public int find(int node,int[] parent){
        if(parent[node]==node) return parent[node];

        return parent[node]=find(parent[node],parent);
    }
}
