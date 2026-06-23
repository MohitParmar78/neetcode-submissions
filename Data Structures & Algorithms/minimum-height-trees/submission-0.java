class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> adj=new ArrayList<>();

        int[] degree=new int[n];

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);

            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> q=new LinkedList<>();

        for(int i=0;i<n;i++){
            if(degree[i]==1){
                q.offer(i);
            }
        }

        int remaining=n;

        while(remaining>2){
            int size=q.size();

            for(int i=0;i<size;i++){
                int temp=q.poll();

                for(int neigh:adj.get(temp)){
                    degree[neigh]--;
                    if(degree[neigh]==1){
                        q.offer(neigh);
                    }
                }
            }
            remaining-=size;
        }

        List<Integer> res=new ArrayList<>();

        if(q.size()==0){
            res.add(0);
            return res;
        }

        while(!q.isEmpty()){
            res.add(q.poll());
        }

        return res;
    }
}