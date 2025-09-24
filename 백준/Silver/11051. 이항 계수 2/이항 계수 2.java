import java.io.*;
import java.util.*;

public class Main {

	static int n, k;
	static int[][] dp;
	static final int MOD = 10007;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
			
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[n+1][n+1];
		for(int i = 0 ; i <= n; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			for(int j = 1; j < i; j++) {
				dp[i][j] = (dp[i-1][j] % MOD + dp[i-1][j-1] % MOD) % MOD;
			}
		}
		System.out.println(dp[n][k]);
	}
}

	
	