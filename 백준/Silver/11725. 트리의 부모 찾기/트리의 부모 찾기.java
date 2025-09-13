
import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] parents;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		graph = new ArrayList[n+1];
		for(int i = 1; i <= n ; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 0 ; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		
		bfs(1);
		for(int i = 2; i <= n ; i++)
			sb.append(parents[i]).append('\n');
		System.out.println(sb);
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		parents[start] = start;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(parents[next] != 0)	continue;
				parents[next] = cur;
				q.offer(next);
			}
		}
		
	}
}