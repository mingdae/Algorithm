package task;

import java.io.*;
import java.util.*;

/*[문제 해석]
 * - 선반 높이 B
 * - 점원 수 N, 키 H1 H2 ... Hn
 * 
 * [목표]
 * - 높이가 B이상인 탑 중에서 가장 낮은 탑
 * - 부분집합의 최소합 문제
 * 
 * [메인 로직]
 * - 부분집합 구하면서 높이 누적
 * 		subset(int idx, int sum)
 * 		// idx : idx 번째 점원 검사
 *		// sum : 현재까지의 탑 높이
 * 
 * - 가지치기 
 * 	- B이상이 되면 이전의 값들과 비교 (Math.min(ans, sum))
 * 	- 이전의 값보다 작으면 최소값 갱신, 크면 재귀 종료
 *  - idx 가 n이 되면 재귀 종료
 */ 
public class SW1486_TopShelf_오민재 {

	static int N, B, T, ans;
	static int[] height;
	
 	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;  tc <= T ; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			height = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			dfs(0, 0);
			
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		
		System.out.println(sb);
	}
 	
 	public static void dfs(int idx, int sum) {
 		// 기저 조건 1. 현재까지 탑의 높이가 B보다 높을 때
 		if(sum >= B) {
 			ans = Math.min(ans,sum - B);
 			return;
 		}
 		// 기저 조건 2. 모든 점원의 경우의 수를 다 탐색했을 떄(sum < B)
 		if(idx == N) {
 			return;
 		}
 		
 		// 분기 1. 현재 점원을 추가
 		dfs(idx+1, sum + height[idx]);
 		
 		// 분기 2. 현재 점원 생략
 		dfs(idx+1, sum);
 	}

}
