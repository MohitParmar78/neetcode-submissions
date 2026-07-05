class DSU{
    int[] parent;
    int[] rank;

    DSU(int n){
        parent=new int[n];
        rank=new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }

    public int find(int node){
        if(parent[node]==node){
            return parent[node];
        }

        return parent[node]=find(parent[node]);
    }

    public boolean union(int u,int v){
        int ru=find(u);
        int rv=find(v);

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
        return true;
    }
}

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[][] arr=new int[edges.length][edges[0].length+1];

        for(int i=0;i<edges.length;i++){
            int[] temp={edges[i][0],edges[i][1],edges[i][2],i};
            arr[i]=temp;
        }

        Arrays.sort(arr,(a,b)->Integer.compare(a[2],b[2]));

        DSU dsu=new DSU(n);

        int mst_wt=0;

        for(int[] edge:arr){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];

            if(dsu.union(u,v)){
                mst_wt+=w;
            }
        }

        List<List<Integer>> res=new ArrayList<>();

        for(int i=0;i<2;i++){
            res.add(new ArrayList<>());
        }

        for(int[] edge:arr){
            int ex_wt=kruskal(edge,n,arr,true,false);
            if(ex_wt==-1 || ex_wt>mst_wt){
                res.get(0).add(edge[3]);
            }
            else{
                int in_wt=kruskal(edge,n,arr,false,true);
                if(in_wt!=-1 && in_wt==mst_wt){
                    res.get(1).add(edge[3]);
                }
            }
        }

        return res;

    }

    public int kruskal(int[] edge,int n,int[][] arr,boolean ex,boolean in){
        DSU dsu1=new DSU(n);

        if(ex && !in){
            int ex_wt=0;
            int used=0;
            for(int[] ed:arr){
                if(ed[3]==edge[3]) continue;

                if(dsu1.union(ed[0],ed[1])){
                    ex_wt+=ed[2];
                    used++;
                }
            }
            if(used==n-1){
                return ex_wt;
            }
            return -1;
        }

        else{
            int in_wt=0;
            int used=0;

            if(dsu1.union(edge[0],edge[1])){
                in_wt+=edge[2];
                used++;
            }

            for(int[] ed:arr){
                if(ed[3]==edge[3]) continue;

                if(dsu1.union(ed[0],ed[1])){
                    in_wt+=ed[2];
                    used++;
                }
            }
            if(used==n-1){
                return in_wt;
            }
            return -1; 
        }      
    }
}