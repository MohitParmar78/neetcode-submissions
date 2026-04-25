class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length!=n-1) return false;

        int[] parent=new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
        }

        int[] rank=new int[n];

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];

            int rootu=find(u,parent);
            int rootv=find(v,parent);

            //checking cycle
            if(rootu==rootv) return false;

            //joining with rank(low under high(so tree grows slower))
            if(rank[rootu]>rank[rootv]){
                parent[rootv]=rootu;
            }
            else if(rank[rootu]<rank[rootv]){
                parent[rootu]=rootv;
            }
            else{
                parent[rootv]=rootu;
                rank[rootu]++;
            }
        }

        return true;
    }

    public int find(int node,int[] parent){
        //path compression
        if(parent[node]!=node){
            parent[node]=find(parent[node],parent);
        }
        return parent[node];
    }
}
