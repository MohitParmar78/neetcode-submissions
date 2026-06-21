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

    public int find(int i){
        if(parent[i]==i){
            return parent[i];
        }

        return parent[i]=find(parent[i]);
    }

    public void union(int u,int v){
        int ru=find(u);
        int rv=find(v);

        if(ru==rv) return;

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
    }

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String,Integer> email_to_id=new HashMap<>();
        HashMap<String,String> email_to_name=new HashMap<>();

        int id=0;

        for(int i=0;i<accounts.size();i++){
            String name=accounts.get(i).get(0);

            for(int j=1;j<accounts.get(i).size();j++){
                String email=accounts.get(i).get(j);

                if(!email_to_id.containsKey(email)){
                    email_to_id.put(email,id++);
                }

                email_to_name.put(email,name);
            }
        }

        DSU dsu=new DSU(id);

        for(List<String> account:accounts){
            String first_email=account.get(1);
            int first_id=email_to_id.get(first_email);

            for(int i=2;i<account.size();i++){
                String snd_email=account.get(i);

                dsu.union(first_id,email_to_id.get(snd_email));
            }
        }

        HashMap<Integer,List<String>> groups=new HashMap<>();

        for(String email:email_to_id.keySet()){
            int root=dsu.find(email_to_id.get(email));

            groups.computeIfAbsent(root,k->new ArrayList<>()).add(email);
        }

        List<List<String>> res=new ArrayList<>();


        for(List<String> group:groups.values()){
            Collections.sort(group);

            String root_name=email_to_name.get(group.get(0));

            List<String> merged=new ArrayList<>();

            merged.add(root_name);
            merged.addAll(group);
            res.add(merged);
        }

        return res;
    }
}