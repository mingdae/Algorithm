package lecture;

import java.io.*;
import java.util.*;


public class DijkstraTest {
	static class Node{
		int to, weight;
		Node next;
		public Node(int to, int weight, Node next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[V];
		
		// 그래프 인접리스트 생성
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		int[] minDistance = new int[V]; // start 정점에서 자신으로의 최소비용
		boolean[] visited = new boolean[V];
		
		// Step 0. 전처리 작업
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(minDistance, INF);
		minDistance[start] = 0;
		
		for(int i = 0; i < V; i++) {
			
			//Step 1. 미방문정점중에서 출발점에서 가장 가까운 정점 찾기
			int min = INF;
			int stopOver = -1;
			for(int j = 0 ; j < V; j++) {
				if(visited[j])	continue;
				if(min > minDistance[j]) {
					min = minDistance[j];
					stopOver = j;
				}
			}
			
			if(stopOver == -1)	break;
			visited[stopOver] = true;
			if(stopOver == end)	break; // 도착점이면 끝내기
			
			// Step 2. Step 1에서 선택된 정점을 경유지로 하여
			//			아직 미방문정점으로의 경로 비용 계산 후 최소비용으로 업데이트
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				if(!visited[temp.to] && minDistance[temp.to] > min+temp.weight) {
					minDistance[temp.to] = min + temp.weight;
				}
			}
		}
		
		
		System.out.println(minDistance[end] != INF ? minDistance[end] : -1);
		
		
	}
}
