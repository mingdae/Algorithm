import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 일방통행 => 단방향 그래프, 사이클 X
 *  - 어떤 시작 도시 -> 도착 도시까지 모든 경로 탐색
 *  - 출발 후 최소 몇시간 후에 만날 수 있는가? => 제일 오래걸린 사람 기준
 *  - 최장거리?
 *  - 최장거리를 만족하는 경로의 길이 카운트
 *  
 *  [문제 풀이]
 *  - 최장거리 => 위상정렬 + DP
 *  - 임계경로 => 역방향 BFS
 * 
 */

public class Main {
	
	static class Edge{
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int V, E, start, end;
	static int ansTime, ansCnt;
	static int[] indeg, dist;
	static ArrayList<Edge>[] graph;
	static ArrayList<Edge>[] rgraph;
	static final int MIN_INF = Integer.MIN_VALUE;
	static Queue<Integer> q;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		start = end = ansTime = ansCnt = 0;
		indeg = new int[V+1];
		dist = new int[V+1];
		graph = new ArrayList[V+1];
		rgraph = new ArrayList[V+1];
		q = new ArrayDeque<>();
		
		Arrays.fill(dist, MIN_INF);
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			rgraph[i] = new ArrayList<>();
		}
		
		
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
			rgraph[to].add(new Edge(from, weight));
			
			indeg[to]++;
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		/* 최장거리 : (정방향) 위상정렬 + DP */	
		q.add(start);
		dist[start] = 0;
		for(int i = 1; i <= V ; i++) {
			if(i == start)	continue;
			if(indeg[i] == 0)
				q.add(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(Edge e : graph[cur]) {
				if(dist[e.to] < dist[cur] + e.weight)
					dist[e.to] = dist[cur] + e.weight;
				if(--indeg[e.to] == 0)
					q.add(e.to);
			}
		}
		
		ansTime = dist[end];
		
		
		/* 임계 경로 : (역방향) BFS */
		q.clear();
		boolean[] visited = new boolean[V+1];
		q.add(end);
		visited[end] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(Edge e : rgraph[cur]) {
				int prev = e.to;
				if(dist[prev] == dist[cur] - e.weight) {
					ansCnt++;
					if(!visited[prev]) {
						visited[prev] = true;
						q.add(prev);
					}
				}
			}
		}
		
		
			
		System.out.println(ansTime);
		System.out.println(ansCnt);
	}
}
	
	