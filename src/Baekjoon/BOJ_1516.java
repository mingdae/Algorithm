package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 게임 플레이에 들어가는 시간은 상황에 따라 다름
 *  - 모든 건물을 짓는데 걸리는 최소 시간 이용
 *  - 먼저 지어야하는 건물 있고(위상 정렬), 여러개 동시에 지을 수 있음
 *  
 *  [문제 풀이]
 *  - 위상정렬 + DP
 *  - 건물의 종류 N
 *  - 최소 시간 : 선행의 작업들이 모두 끝났을때 가장 늦게 끝난 것 기준으로 자기 자신을 더한 값
 *  - u->v : dp[v] = max(dp[v], dp[u]+t[v])
 */

public class BOJ_1516 {
	static int n;
	static ArrayList<Integer>[] graph;
	static int[] time, indegree, dp;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		time = new int[n+1];
		graph = new ArrayList[n+1];
		indegree = new int[n+1];
		dp = new int[n+1];
		q = new ArrayDeque<>();
		
		for(int i = 1; i <= n ; i++)
			graph[i] = new ArrayList<>();
		
		for(int to = 1 ; to <= n ; to++) {
			st = new StringTokenizer(br.readLine());
			time[to] = Integer.parseInt(st.nextToken());
			// dp 테이블 초기화
			dp[to] = time[to];
			// 1. 진입차수 계산
			while(true) {
				int from = Integer.parseInt(st.nextToken());
				if(from == -1)
					break;
				graph[from].add(to);
				indegree[to]++;
			}
			
			// 2. 진입차수가 0인 노드 Queue에 삽입
			if(indegree[to] == 0) {
				q.add(to);
			}
		}
		
		// 3. 위상 정렬
		while(!q.isEmpty()) {
			// 3-1. 진입차수가 0인 노드 꺼내기
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				// 3-2. dp 테이블 갱신
				if(dp[next] < dp[cur] + time[next])
					dp[next] = dp[cur] + time[next];
				
				// 3-3. 진입차수 감소, 0 되면 Queue 삽입
				if(--indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			System.out.println(dp[i]);
		}

	}
}
	
	