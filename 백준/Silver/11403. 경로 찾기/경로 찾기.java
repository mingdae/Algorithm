
import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[][] ans;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n];
		ans = new int[n][n];
		for(int i = 0 ; i < n ; i++)
			graph[i] = new ArrayList<>();
		
		for(int from = 0 ; from < n ; from++) {
			st = new StringTokenizer(br.readLine());
			for(int to = 0 ; to < n; to++) {
				int inp = Integer.parseInt(st.nextToken());
				if(inp == 1) {
					graph[from].add(to);
					ans[from][to] = 1;
				}
					
			}
		}
		
		for(int from = 0 ; from < n ; from++) {
			visited = new boolean[n];
			bfs(from);
		}
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	public static void bfs(int from) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(from);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					ans[from][next] = 1;
					q.offer(next);
				}
			}
		}
		
		
	}
}