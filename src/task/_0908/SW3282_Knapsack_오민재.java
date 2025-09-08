package task._0908;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * 
 * 
 * 
 * 
 */
public class SW3282_Knapsack_오민재 {
	static int T, n, total, ans;
	static int[] weight, value;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			total = Integer.parseInt(st.nextToken());
			ans = 0;
			weight = new int[n+1];
			value = new int[n+1];
			dp = new int[n+1][total+1];
			
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <=n; i++) {
				for(int w = 1; w <= total; w++) {
					if(w >= weight[i]) {
						dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i]);
					}else {
						dp[i][w] = dp[i-1][w];
					}
				}
			}
			ans = dp[n][total];
			
			
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	
}
