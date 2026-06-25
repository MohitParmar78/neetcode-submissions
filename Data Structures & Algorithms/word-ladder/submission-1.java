class pair{
    String node;
    int level;

    pair(String node,int level){
        this.node=node;
        this.level=level;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set=new HashSet<>();

        for(String str:wordList){
            set.add(str);
        }

        if(!set.contains(endWord)) return 0;

        Queue<pair> q=new LinkedList<>();
        q.offer(new pair(beginWord,0));
        set.remove(beginWord);

        while(!q.isEmpty()){
            pair temp=q.poll();

            String str=temp.node;
            int level=temp.level;

            if(str.equals(endWord)){
                return level+1;
            }

            for(pair neigh:find_n(str,set,level)){
                if(set.contains(neigh.node)){
                    q.offer(neigh);
                    set.remove(neigh.node);
                }
            }
        }

        return 0;

    }

    public List<pair> find_n(String str,Set<String> set,int level){
        List<pair> lst=new ArrayList<>();

        for(int i=0;i<str.length();i++){
            char[] arr=str.toCharArray();
            for(char ch='a';ch<='z';ch++){
                arr[i]=ch;
                String n=new String(arr);
                if(set.contains(n)){
                    lst.add(new pair(n,level+1));
                }
            }
        }
        return lst;
    }
}
