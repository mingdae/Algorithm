package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_1325 {
	static int V, E, count;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V+1];
		for(int i = 1 ; i <= V; i++)
			graph[i] = new ArrayList<>();
		cnt = new int[V+1];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			graph[from].add(to);
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i <= V; i++) {
			visited = new boolean[V+1];
			count = 0;
			dfs(i);
			cnt[i] = count;
			max = Math.max(max, cnt[i]);
		}
		
		for(int i = 1; i <= V; i++) {
			if(cnt[i] == max)
				sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int start) {
		visited[start] = true;

		for(int next : graph[start]) {
			if(!visited[next]) {
				count++;
				dfs(next);
			}
				
		}
	}
}
