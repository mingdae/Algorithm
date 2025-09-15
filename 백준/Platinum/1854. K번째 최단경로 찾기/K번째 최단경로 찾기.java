
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
	
	static int V, E, K;
	static ArrayList<Edge>[] graph;
	static PriorityQueue<Integer>[] minDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V+1];
		minDist = new PriorityQueue[V+1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			minDist[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
			
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
		}
		
		dijkstra(1);
		for(int i = 1; i <= V; i++) {
			if(minDist[i].size() < K)	sb.append(-1).append('\n');
			else sb.append(minDist[i].peek()).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		minDist[start].offer(0);
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int v = cur.to;
			int w = cur.weight;
			for(Edge e : graph[cur.to]) {
				int nweight = w + e.weight;
				if(minDist[e.to].size() < K) {
					minDist[e.to].offer(nweight);
					pq.offer(new Edge(e.to, nweight));
				}else if(minDist[e.to].peek() > nweight){
					minDist[e.to].poll();
					minDist[e.to].offer(nweight);
					pq.offer(new Edge(e.to, nweight));
				}
				
			}
			
		}
	}

}