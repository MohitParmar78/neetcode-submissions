class Solution {

    public String encode(List<String> strs) {
        StringBuilder sb=new StringBuilder();

        for(String str:strs){
            String size=String.valueOf(str.length());
            sb.append(size+"#"+str);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> res=new ArrayList<>();

        int i=0;
        while(i<str.length()){
            int j=i;
            while(str.charAt(j)!='#'){
                j++;
            }
            int length=Integer.parseInt(str.substring(i,j));
            String stri=str.substring(j+1,j+length+1);

            res.add(stri);
            i=j+length+1;
        }
        return res;
    }
}
