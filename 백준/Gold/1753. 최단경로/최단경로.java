
import java.io.*;
import java.util.*;

public class Main {
	
	
	static class Edge implements Comparable<Edge>{
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E;
	static ArrayList<Edge>[] graph;
	static int[] minDist;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V+1];
		for(int i = 1; i <= V; i++)
			graph[i] = new ArrayList<>();
		minDist = new int[V+1];
		Arrays.fill(minDist, INF);
		
		
		int start = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
		}
		
		dijkstra(start);
		for(int i = 1; i <= V; i++) {
			if(minDist[i] == INF)	sb.append("INF").append('\n');
			else sb.append(minDist[i]).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void dijkstra(int from) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		minDist[from] = 0;
		Edge head  = new Edge(from, 0);
		pq.offer(head);
		
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int to = cur.to;
			int weight = cur.weight;
			
			for(Edge e : graph[to]) {
				int v = e.to;
				int nd = weight + e.weight;
				if(nd < minDist[v]) {
					minDist[v] = nd;
					pq.offer(new Edge(v, nd));
				}
			}
			
		}
	}

}