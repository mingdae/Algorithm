package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 맛을 최대로 유지하면서, 정해진 칼로리 넘지 않는 햄버거
 * - 같은 재료 여러번 사용 X, 선호도는 맛의 점수의 합
 * 
 * [문제 풀이]
 * - DP 풀이
 * - Knapsack 알고리즘
 * - dp[i][j] : i번째 재료까지 확인했을 때 칼로리 j 로 얻을 수 있는 최대 가치
 * 		- i번째 물건 안담을 경우 = dp[i-1][j]
 * 		- i번째 물건 담을 경우 = dp[i-1][j-w[i]] + v[i])  	
 * 		- 2가지 경우의 수 중 최선을 선택
 * - 목표 : dp[N][W]
 * 
 */
public class SW5215_HamburgerDiet_오민재 {
	static int T, V, W, ans;
	static int[] value, weight;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 재료 개수
			W = Integer.parseInt(st.nextToken()); // 칼로리 제한
			value = new int[V+1];
			weight = new int[V+1];
			dp = new int[V+1][W+1];
			
			for(int i = 1 ; i <= V ; i++) {
				st = new StringTokenizer(br.readLine());
				value[i] = Integer.parseInt(st.nextToken());
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= V; i++) {
				for(int w = 0; w <= W; w++) {
					dp[i][w] = dp[i-1][w]; // i번째 재료 선택 X 
					if(w - weight[i] >= 0) // i번째 재료 선택 가능 여부 확인
						dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i]);
				}
			}
			ans = dp[V][W];
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	
}
