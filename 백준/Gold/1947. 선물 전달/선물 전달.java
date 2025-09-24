import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static long[] dp;
	static final long MOD = 1_000_000_000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		N = Integer.parseInt(br.readLine());
		dp = new long[N+1];
		if(N == 1)	System.out.println(0);
		else {
			dp[2] = 1;
			for(int n = 3 ; n <= N; n++) {
				dp[n] = (((n-1)%MOD) * (((dp[n-1]%MOD) + (dp[n-2]%MOD))%MOD)) % MOD;
			}
			System.out.println(dp[N]);
		}
		
	}
}

	
	