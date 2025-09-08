package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 트리의 지름 : 임의의 두 점 사이의 거리 중 가장 긴 것
 *  - 트리 : 임의의 한 점에서 다른 한 점까지의 경로가 유일함
 *  - 임의의 한 점에서 가장 먼 노드를 구하고,
 *  	- 그 노드에서 가장 먼 노드가 곧 지름
 */

public class BOJ_1167 {
	static int V;
	static List<Edge>[] graph;
	static boolean[] visited;
	static int[] dist;
	static class Edge{
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	static class Pair{
		int node, dist;
		Pair(int node, int dist){
			this.node = node;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(br.readLine());
		graph = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while(true) {
				int to = Integer.parseInt(st.nextToken());
				if(to == -1)
					break;
				int weight = Integer.parseInt(st.nextToken());
				graph[from].add(new Edge(to, weight));
			}
		}
		
		Pair farNode = bfs(1);
		Pair ansNode = bfs(farNode.node);
		
		System.out.println(ansNode.dist);
			
	}
	
	public static Pair bfs(int start) {
		visited = new boolean[V+1];
		dist = new int[V+1];
		visited[start] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(Edge e : graph[cur]) {
				if(!visited[e.to]) {
					visited[e.to] = true;
					dist[e.to] = dist[cur] + e.weight;
					q.add(e.to);
				}
			}
		}
		int maxDist = 0;
		int maxNode = 0;
		for(int i = 1; i <= V; i++) {
			if(dist[i] > maxDist) {
				maxNode = i;
				maxDist = dist[i];
			}
		}
		
		return new Pair(maxNode, maxDist);
	}
}
	
	