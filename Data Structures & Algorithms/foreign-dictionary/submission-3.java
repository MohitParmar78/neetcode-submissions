class Solution {
    public String foreignDictionary(String[] words) {
        Set<Character> set=new HashSet<>();

        Map<Character,Set<Character>> adj=new HashMap<>();

        Map<Character,Integer> indegree=new HashMap<>();

        for(String word:words){
            for(char ch:word.toCharArray()){
                if(!set.contains(ch)){
                    adj.put(ch,new HashSet<>());
                    indegree.put(ch,0);
                    set.add(ch);
                }
            }
        }

        for(int i=0;i<words.length-1;i++){
            String str1=words[i];
            String str2=words[i+1];

            boolean br=false;

            for(int j=0;j<Math.min(str1.length(),str2.length());j++){
                char u=str1.charAt(j);
                char v=str2.charAt(j);
                if(u!=v){
                    if(!adj.get(u).contains(v)){
                        adj.get(u).add(v);
                        indegree.put(v,indegree.get(v)+1);
                    }
                    br=true;
                }
                if(br) break;
            }

            if(!br && str1.length()>str2.length()){
                return "";
            }
        }

        Queue<Character> q=new LinkedList<>();

        for(char ch:indegree.keySet()){
            if(indegree.get(ch)==0){
                q.offer(ch);
            }
        }

        List<Character> lst=new ArrayList<>();

        while(!q.isEmpty()){
            char temp=q.poll();

            lst.add(temp);

            for(char neigh:adj.get(temp)){
                indegree.put(neigh,indegree.get(neigh)-1);
                if(indegree.get(neigh)==0){
                    q.offer(neigh);
                }
            }
        }

        if(lst.size()!=set.size()){
            return "";
        }

        StringBuilder sb=new StringBuilder();

        for(int i=0;i<lst.size();i++){
            sb.append(lst.get(i));
        }

        return sb.toString();
    }
}
