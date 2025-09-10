import java.io.*;
import java.util.*;

/* [문제 분석]
 * - N개 나무
 * - 하루 한 나무에만 물 줄 수 있음
 * - 홀수 1 자라고, 짝수 2 자람
 * 
 * <목표>
 * - 모든 나무의 키가 처음에 가장 컸던 나무와 같아지도록하는 최소 날짜 수
 * 
 * [문제 풀이]
 * - 홀수 + 짝수 페어 => 3 자람
 * - 홀수를 기준으로 필요한 성장 길이를 줄여나감
 * - 필요한 홀수 : 차이 % 2, 짝수 : 차이 / 2
 *  1. 홀수 > 짝수
 * 		- 홀수를 다 채워나가면 짝수는 알아서 채워짐
 * 		- (odd * 2)-1
 *  2. 홀수 <= 짝수
 *  	- 홀수 먼저 다 채우기
 *  	- 남은 수는 이틀에 3씩 채울 수 있음
 *  	- 나머지 더해주기
 */

public class Solution {
	static int T, n, ans;
	static int[] trees;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine().trim());
			trees = new int[n];
			ans = 0;
			st = new StringTokenizer(br.readLine().trim());
			int maxH = 0; // 최대 높이
			for(int i = 0 ; i < n; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, trees[i]);
			}
			
			int total = 0;
			int odds = 0;
			int evens = 0;
			for(int i = 0 ; i < n ; i++) {
				int tmp = maxH - trees[i];
				total += tmp;
				evens += tmp / 2;
				odds += tmp % 2;
			}
				
			if(odds > evens) { // 홀수가 더 많으면 => 홀수를 처리하면서 짝수 모두 처리 가능
				ans += odds * 2 - 1;
			}else {
				// 짝수가 더 많으면 => 홀수 먼저 처리
				ans += odds * 2; // odd개 만큼의 짝수도 처리
				int remains = total - odds * 3; // 처리하고 남은 높이
				ans += (remains / 3) * 2  + remains % 3;
			}

			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	
}
