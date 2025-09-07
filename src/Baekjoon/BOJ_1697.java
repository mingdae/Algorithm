package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_1697 {
	static int n, k, ans;
	static int[] dist;
	static final int MAX = 100000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ans = 0;
		dist = new int[MAX+1];
		Arrays.fill(dist, -1); // -1로 초기화
		
		if(n >= k)	ans = n - k;
		else {
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(n);
			dist[n] = 0;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				if(cur == k) {
					ans = dist[cur];
					break;
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
			
			
		}
		
		System.out.println(ans);
	}
	
	
}
