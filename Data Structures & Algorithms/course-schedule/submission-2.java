class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj=new ArrayList<>();

        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }

        int[] indegree=new int[numCourses];

        for(int i=0;i<prerequisites.length;i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        /* for(int i=0;i<numCourses;i++){
            for(int j:adj.get(i)){
                indegree[j]++;
            }
        }*/

        Queue<Integer> q=new LinkedList<>();

        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }

        List<Integer> lst=new ArrayList<>();

        while(!q.isEmpty()){
            int n=q.poll();

            lst.add(n);

            for(int j:adj.get(n)){
                indegree[j]--;

                if(indegree[j]==0){
                    q.add(j);
                }
            }
        }

        if(lst.size()==numCourses) return true;

        return false;
    }
}
