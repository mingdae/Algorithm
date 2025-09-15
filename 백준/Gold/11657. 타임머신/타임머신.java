import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	static int V, E;
	static ArrayList<Edge> edgeList;
	static long[] minDist;
	static final long INF = Long.MAX_VALUE / 4;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new ArrayList<>();
		minDist = new long[V+1];
		Arrays.fill(minDist, INF);
		
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		boolean noCycle = bellmanFord(1);
		
		if(!noCycle)	System.out.println(-1);
		else {
			for(int i = 2; i <= V; i++) {
				if(minDist[i] == INF)	sb.append(-1);
				else sb.append(minDist[i]);
				
				if(i != V)	sb.append('\n');
			}
			System.out.println(sb);
		}
	}
	
	public static boolean bellmanFord(int start) {
		minDist[start] = 0;
		
		// V-1 라운드 진행
		for(int i = 1; i < V; i++) {
			boolean updated = false;
			// 모든 간선 순회
			for(Edge e : edgeList) {
				// 미도달 정점에서 출발하는 간선 스킵
				if(minDist[e.from] == INF)	continue;
				
				 long nextDist = minDist[e.from] + e.weight;
				 if(minDist[e.to] > nextDist) {
					 minDist[e.to] = nextDist;
					 updated = true;
				 }
			}
			
			// update 안되면 바로 종료
			if(!updated) break;
		}
		
		// 음수 사이클 검사 ( 시작점에서 도달 가능한 간선만)
		for(Edge e : edgeList) {
			if(minDist[e.from] == INF)	continue;
			// 한 번 더 완화되면 음수 사이클 존재
			if(minDist[e.from] + e.weight < minDist[e.to])
				return false;
		}
		
		return true;
	}
}
