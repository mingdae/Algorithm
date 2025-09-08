import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - A[i][j] = i * j
 *  - B[N*N] 에 A[i][j] 정보 넣기
 *  - 오름차순 정렬 -> B[k] 구하기
 *  
 *  - 구구단에서 K 번째 수 => 값 x 이하의 원소 개수가 K 이상이 되는 최소 x 값
 *  - 구구단 : i 번째 행에 i, 2*i, 3*i.... , N*i 있음
 *  - mid 이하 개수 => min(N, mid/i), 한 줄에 N개보다 많을 수는 없음
 * 
 */

public class Main {
	static int n, k, ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		ans = 0;
		int left = 1, right = k; // K번째수는 K보다 클 수 없음
		
		// 매개변수 이분탐색
		while(left <= right) {
			int mid = (left + right) / 2;
			if(count(mid) >= k)	{
				// K보다 크다 : 더 줄일 수 있다 + 정답 후보
				right = mid-1;
				ans = mid;
			}else	
				left = mid + 1; // K보다 작다 : 더 키워야한다
		}
			
		System.out.println(ans);	
			
	}
	
	public static int count(int x) {
		int cnt = 0;
		for(int i = 1; i <= n; i++) { // 1~n번째 줄까지 검사
			cnt += Math.min(n, x/i); // 한 줄에는 최대 n개 숫자
		}
		return cnt;
	}
}
	
	