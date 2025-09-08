package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 이분 그래프
 *  - 서로 인접하지 않도록 2개의 정점 집합으로 분할할 수 있을 때
 *  - 인접한 정점 색칠 
 *  	=> 인접한 정점끼리 서로 다른 색으로 색칠 할 수 있으면 이분그래프
 * 
 * <주의 사항>
 * 1. "그래프" 기 때문에 연결되지 않은 정점이 있을 수 있음
 * 		-> 색칠되지 않은 모든 정점에 대해 bfs 필요
 * 2. 양방향 그래프 고려
 * 3. BFS 진행하면서 인접한 정점 색칠, 이미 색칠되어있는데 같은 색이면 false
 */

public class BOJ_1707 {
	static int T, V, E;
	static boolean canDivide;
	static int[] color;
	static ArrayList<Integer>[] graph;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new ArrayList[V+1];
			for(int i = 1; i <= V; i++)
				graph[i] = new ArrayList<>();
			color = new int[V+1];
			canDivide = true;
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
				graph[to].add(from);
			}
			
			for(int i = 1; i <= V; i++) {
				if(!canDivide)
					break;
				
				if(color[i] == 0) { // 방문하지 않은 모든 정점에 대해 BFS
					bfs(i);
				}
			}

			if(canDivide)	System.out.println("YES");
			else	System.out.println("NO");
		}

	}
	public static void bfs(int start) {
		color[start] = 1;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty() && canDivide) {
			int cur = q.poll();
			for(int next : graph[cur]) {
				if(color[next]==0) {
					color[next] = color[cur]*-1; // 인접 정점 다른 색으로 색칠
					q.add(next);
				}
				if(color[next] == color[cur]) { // 인접 정점이 같은 색이면 false
					canDivide = false;
					break;
				}
			}
		}
	}
}
	
	