class Solution {
    public void islandsAndTreasure(int[][] grid) {
        boolean[][] visited=new boolean[grid.length][grid[0].length];

        Queue<int []> q=new LinkedList<>();

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==0){
                    q.offer(new int[]{i,j});
                    visited[i][j]=true;
                }
            }
        }

        int[][] dirt={{1,0},{-1,0},{0,1},{0,-1}};

        while(!q.isEmpty()){
            int[] temp=q.poll();

            int r=temp[0];
            int c=temp[1];

            int val=grid[r][c];

            for(int[] dir:dirt){
                int nr=r+dir[0];
                int nc=c+dir[1];

                if(nr>=0 && nc>=0 && nr<grid.length && nc<grid[0].length && !visited[nr][nc] && grid[nr][nc]!=-1){
                    grid[nr][nc]=val+1;
                    q.offer(new int[]{nr,nc});
                    visited[nr][nc]=true;
                }
            }
        }

    }
}
