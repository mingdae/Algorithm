package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_2252 {
	static int n, m;
	static ArrayList<Integer>[] list;
	static int[] indegree;
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n+1];
		indegree = new int[n+1];
			
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 간선 정보 입력 from -> to , to의 진입차수 + 1
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			indegree[to]++;
		}
		
		// 진입 차수가 0인 노드 큐에 삽입
		for(int i = 1; i <= n ; i++) {
			if(indegree[i] == 0)
				q.add(i);
		}
		
		// Khan's Algorithm
		while(!q.isEmpty()) {
			int cur = q.poll(); // 진입차수가 0인 노드 제거
			sb.append(cur).append(" ");
			
			// 제거된 노드에 연결된 간선 제거
			for(int next : list[cur]) {
				indegree[next]--;
				if(indegree[next] == 0)
					q.add(next);
			}
		}
		
		
		System.out.println(sb);
	}
}
