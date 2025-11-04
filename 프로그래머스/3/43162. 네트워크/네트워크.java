import java.util.*;

class Solution {
    public static ArrayList<Integer>[] graph;
    public static int ans;
    public static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        graph = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n];
        
        for(int i = 0 ; i < n; i++){
            for(int j = i; j < n ; j++){
                if(computers[i][j] == 1){
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        for(int i = 0 ; i < n; i++){
            if(!visited[i]){
                ans++;
                dfs(i);
            }    
        }
        return ans;
        
    }
    
    public static void dfs(int node){
        visited[node] = true;
        
        for(int next : graph[node]){
            if(!visited[next]){
                dfs(next);
            }
        }
    }
}