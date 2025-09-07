package task;

import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 비상연락망과 연락을 시작하는 당번
 * - 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람
 * - 방향 그래프
 * - 이미 방문한 번호는 방문 X
 * 
 * 
 * [문제 풀이]
 *  - BFS
 *  - 가장 나중에 연락을 받음 -> start 로부터 거리가 가장 먼 node
 *  - 거리 저장 dist 배열로 거리 계산 + 방문 처리 동시에
 *  - dist[i] 가 가장 클 때, i값이 ans
 *  - 1~100까지 순회 -> 자연스럽게 같은 dist[i] 값 중에서는 최대값으로 갱신
 *  
 *  
 */


public class SWEA_1238 {
	static int n, start, ans;
	static final int T = 10;
	static ArrayList<Integer>[] number;
	static int[] dist;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			ans = 0;
			
			number = new ArrayList[101];
			dist = new int[101];
			
			for(int i = 1; i <= 100; i++) {
				number[i] = new ArrayList<>();
			}
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < n / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				number[from].add(to);
			}
			
			bfs(start);
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		
		System.out.println(sb);

	}
	public static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(node);
		dist[node] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : number[cur]) {
				if(dist[next] == 0) { // 방문한 적 없음
					q.offer(next);
					dist[next] = dist[cur] + 1; // 거리 1 증가
				}
			}
		}
		
		int maxVal = 0;
		for(int i = 1 ; i < 101; i++) {
			if(dist[i] >= maxVal) {
				maxVal = dist[i];
				ans = i;
			}
		}
	}

}
