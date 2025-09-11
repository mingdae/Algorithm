package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N종류 동전
 *  - 가치의 합 K, 필요한 동전 최소값
 * 
 */

public class BOJ_11047 {
	static int n, total, ans;
	static int[] coins;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		total = Integer.parseInt(st.nextToken());
		ans = 0;
		coins = new int[n];
		
		for(int i = 0 ; i < n ; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = n-1; i >= 0; i--) {
			ans += total / coins[i];
			total %= coins[i];
			if(total == 0)
				break;
		}
		
			
		System.out.println(ans);
			
			
			
	}
}
	
	