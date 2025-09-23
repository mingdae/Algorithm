import java.io.*;
import java.util.*;

public class Main {

	static int T, n, m;
	static int[][]dp;
	
	// C(m,n) = C(m-1, n) + C(m-1, n-1)
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			dp = new int[m+1][n+1];

			if(n==m)	sb.append(1).append('\n');
			else if(n == 1)	sb.append(m).append('\n');
			else {
				for(int i = 1 ; i <= m; i++) {
					for(int j = 1; j <= i ; j++) {
						if(j > n)	break;
						if(j == 1)	dp[i][j] = i;
						else if(i == j)	dp[i][j] = 1;
						else {
							dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
						}
						
					}
				}
				sb.append(dp[m][n]).append('\n');
			}

		}
		System.out.println(sb);
	}

}
	
	