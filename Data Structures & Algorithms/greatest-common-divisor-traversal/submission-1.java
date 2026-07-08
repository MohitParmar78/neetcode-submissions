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
        if(parent[node]!=node){
            return parent[node]=find(parent[node]);
        }
        return parent[node];
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
    public boolean canTraverseAllPairs(int[] nums) {
        int n=nums.length;

        if(n==1) return true;

        for(int num:nums){
            if(num==1) return false;
        }

        Map<Integer,Integer> map=new HashMap<>();

        DSU dsu=new DSU(n);

        for(int i=0;i<n;i++){
            int num=nums[i];

            for(int j=2;j*j<=num;j++){
                if(num%j==0){
                    if(map.containsKey(j)){
                        dsu.union(i,map.get(j));
                    }
                    else{
                        map.put(j,i);
                    }
                }

                while(num%j==0){
                    num/=j;
                }
            }

            if(num>1){
                if(map.containsKey(num)){
                    dsu.union(i,map.get(num));
                }
                else{
                    map.put(num,i);
                }
            }
        }

        int root=dsu.find(0);

        for(int i=1;i<n;i++){
            if(dsu.find(i)!=root){
                return false;
            }
        }
        return true;
    }
}