import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - A[i][j] = i * j
 *  - B[N*N] 에 A[i][j] 정보 넣기
 *  - 오름차순 정렬 -> B[k] 구하기
 *  - 
 *  - 
 * 
 */

public class Main {
	static int n, k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		int left = 1, right = k;
		while(left < right) {
			int mid = (left + right) / 2;
			if(count(mid) >= k)	right = mid;
			else	left = mid + 1;
		}
			
		System.out.println(left);	
			
	}
	
	public static int count(int x) {
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			cnt += Math.min(n, x/i);
		}
		return cnt;
	}
}
	
	