package Baekjoon;

import java.io.*;
import java.util.*;


public class BOJ_12851 {
	static int n, k, minTime, cnt;
	static int[] dist;
	static final int MAX = 100000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		minTime = MAX + 1; // 최소 시간(최단 거리)
		cnt = 1; // 방법 가짓수
		
		dist = new int[MAX+1];
		Arrays.fill(dist, -1); // -1로 초기화
		
		if(n >= k) { 
			minTime = n - k;
		}
		else {
			Queue<Integer> q = new ArrayDeque<>();
			int[] finds = new int[MAX + 1];
			int[] count = new int[MAX + 1];
			int idx = 0;
			q.offer(n);
			dist[n] = 0;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				if(cur == k) {
					finds[idx++] = dist[cur];
					count[dist[cur]]++;
				}
				
				int[] nexts = {cur-1, cur*2, cur+1};
				for(int next : nexts) {
					if(next < 0 || next > MAX)	continue;
					
					if(dist[next] == -1) {
						q.offer(next);
						dist[next] = dist[cur] + 1;
					}
				}
			}
			minTime = finds[0];
			cnt = count[minTime];
		}
		
		System.out.println(minTime);
		System.out.println(cnt);
	}
	
	
}
