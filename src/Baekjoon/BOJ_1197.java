package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_1197 {
	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		
		make();
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(cmd == 0)
				union(a, b);
			else {
				int pa = find(a);
				int pb = find(b);
				if(pa == pb)	sb.append("YES").append("\n");
				else	sb.append("NO").append("\n");
			}
				
		}
		System.out.println(sb);
		
	}
	
	public static void make() {
		for(int i = 1; i <= n; i++)
			parents[i] = i;
	}
	public static int find(int x) {
		if(parents[x] == x)	return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb)	return false;
		if(pa > pb)	parents[pb] = pa;
		else	parents[pa] = pb;
		return true; 
	}
	
	
}
