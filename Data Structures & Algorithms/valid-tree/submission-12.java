class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length!=n-1) return false;

        List<List<Integer>> adj=new ArrayList<>();

        int[] visited=new int[n];

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        for(int i=0;i<n;i++){
            if(visited[i]!=2){
                helper(adj,i,i,visited);
            }

            if(is_cycle) return false;
        }

        return true;
    }

    boolean is_cycle=false;

    public void helper(List<List<Integer>> adj,int index,int parent,int[] visited){
        if(is_cycle) return;

        if(visited[index]==2){
            return;
        }

        else{
        visited[index]=1;

        for(int n:adj.get(index)){
            if(visited[n]==1 && n!=parent){
            is_cycle=true;
            return;
            }

            else if(n!=parent && visited[n]!=1){
            helper(adj,n,index,visited);
            }
        }

        visited[index]=2;
        }
    }
}
