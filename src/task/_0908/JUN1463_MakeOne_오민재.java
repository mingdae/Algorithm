package task._0908;
import java.io.*;
import java.util.*;

public class JUN1463_MakeOne_오민재 {
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		for(int i = 2; i <= n; i++) {
			dp[i] = dp[i-1] + 1;
			if(i % 2 == 0) 
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			
			if(i % 3 == 0) 
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
		}
			
		System.out.println(dp[n]);
			
	}
}
	
	