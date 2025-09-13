
import java.io.*;
import java.util.*;

public class Main {
	static int N, W;
	static int[] value, weight;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		dp = new int[N+1][W+1];
		value = new int[N+1];
		weight = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int w = 1; w <= W; w++) {
				if(w >= weight[i]) {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - weight[i]] + value[i]);
				}else {
					dp[i][w] = dp[i-1][w];
				}
			}
		}
		
		System.out.println(dp[N][W]);
	}
	
	
}