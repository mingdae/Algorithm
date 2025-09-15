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
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		minDist = new long[V+1];
		Arrays.fill(minDist, INF);
		edgeList = new ArrayList<>();
		
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		
		if(bellmanFord(1)) { // 사이클 X
			for(int i = 2; i <= V; i++) {
				if(minDist[i] == INF)
					sb.append(-1).append('\n');
				else
					sb.append(minDist[i]).append('\n');
			}
			System.out.println(sb);
		}else {
			System.out.println(-1);
		}
	}
	
	public static boolean bellmanFord(int start) {
		minDist[start] = 0;
		
		// V-1 라운드 진행
		for(int i = 1; i < V; i++) {
			boolean updated = false;
			
			for(Edge e : edgeList) {
				// 미도달 정점 패스
				if(minDist[e.from] == INF)	continue;
				
				// 갱신
				if(minDist[e.to] > minDist[e.from] + e.weight) {
					minDist[e.to] = minDist[e.from] + e.weight;
					updated = true;
				}
			}
			
			// 갱신 없으면 종료
			if(!updated)	break;
		}
		
		// 음수 사이클 판별
		for(Edge e : edgeList) {
			// 미도달 정점 패스
			if(minDist[e.from] == INF)	continue;
			
			if(minDist[e.to] > minDist[e.from] + e.weight) {
				return false;
			}
		}
		
		return true; // 사이클 X
	}
}
	
	