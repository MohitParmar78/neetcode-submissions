class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length!=n-1) return false;

        List<List<Integer>> adj=new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        boolean[] visited=new boolean[n];

        helper(visited,0,-1,adj);

        if(is_cycle) return false;

        for(int i=0;i<n;i++){
            if(!visited[i]){
                return false;
            }
        }

        return true;
    }

    boolean is_cycle=false;

    public void helper(boolean[] visited,int node,int parent,List<List<Integer>> adj){
        if(is_cycle) return;

        visited[node]=true;

        for(int neighbor:adj.get(node)){
            if(visited[neighbor] && neighbor!=parent){
                is_cycle=true;
                return;
            }

            else if(!visited[neighbor]){
                helper(visited,neighbor,node,adj);
            }
        }
    }
}
