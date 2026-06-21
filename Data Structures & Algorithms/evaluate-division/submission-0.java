class pair{
    String node;
    double weight;

    pair(String node,double weight){
        this.node=node;
        this.weight=weight;
    }
}

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,List<pair>> adj=new HashMap<>();

        for(int i=0;i<equations.size();i++){
            String u=equations.get(i).get(0);
            String v=equations.get(i).get(1);
            double weight=values[i];

            adj.putIfAbsent(u,new ArrayList<>());
            adj.putIfAbsent(v,new ArrayList<>());

            adj.get(u).add(new pair(v,weight));
            adj.get(v).add(new pair(u , 1.0 / weight ));

        }

        double[] res=new double[queries.size()];
        int ind=0;

        for(List<String> query:queries){
            String c=query.get(0);
            String d=query.get(1);

            if(!adj.containsKey(c) || !adj.containsKey(d)){
                res[ind]=-1.0;
                ind++;
            }

            else if(c.equals(d)){
                res[ind]=1.0;
                ind++;
            }

            else{
                Set<String> visited=new HashSet<>();
                res[ind]=dfs(adj,c,d,visited);
                ind++;
            }
        }
        return res;
    }
    
    public double dfs(Map<String,List<pair>> adj,String start,String target,Set<String> visited){
        if(start.equals(target)){
            return 1.0;
        }

        visited.add(start);

        for(pair neigh : adj.get(start)){
            String node=neigh.node;

            if(!visited.contains(node)){
                double res=dfs(adj,node,target,visited);

                if(res!=-1.0){
                    return res*neigh.weight;
                }
            }
        }

        return -1.0;

    }
}