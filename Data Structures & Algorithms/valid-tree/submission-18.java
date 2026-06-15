class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n-1) return false;

        int[] parent=new int[n];
        int[] rank=new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=0;
        }

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];

            int ru=find(parent,u);
            int rv=find(parent,v);

            if(ru==rv) return false;

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

        return true;
    }

    public int find(int[] parent,int node){
        if(parent[node]==node){
            return parent[node];
        }

        else{
            return parent[node]=find(parent,parent[node]);
        }
    }
}
