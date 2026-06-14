class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length!=n-1) return false;

        List<List<Integer>> adj=new ArrayList<>();

        boolean[] visited=new boolean[n];

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        helper(adj,0,-1,visited);

        if(is_cycle) return false;

        for(int i=0;i<n;i++){
            if(!visited[i]){
                return false;
            }
        }

        return true;
}    

    boolean is_cycle=false;

    public void helper(List<List<Integer>> adj,int index,int parent,boolean[] visited){
        if(is_cycle) return;


        visited[index]=true;

        for(int n:adj.get(index)){
            if(visited[n] && n!=parent){
            is_cycle=true;
            return;
            }

            else if(n!=parent && !visited[n]){
            helper(adj,n,index,visited);
            }

        }

        }
    }
