package task;
import java.io.*;
import java.util.*;

public class ColoringAPT_오민재 {
	static int n, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 2;
		dp[2] = 3;
		for(int i = 3; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		ans = dp[n];
		
		System.out.println(ans);
		// n = 8 -> ans = 55
			
	}
}
	
	