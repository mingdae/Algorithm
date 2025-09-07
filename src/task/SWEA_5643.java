package task;

import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 1번 부터 N번 학생 키 비교 결과의 일부만 주어짐
 * - N명의 키는 모두 다름
 * - "부분 순서", "선행 관계" -> 위상 정렬 알고리즘 ?
 * 
 * [목표]
 * 자신의 키가 몇 번 째인지 알 수 있는 학생의 명 수
 * 
 * [풀이]
 * 
 */
public class SWEA_5643 {
	static int T, n, m, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			
			
			
			sb.append("#").append(tc).append(" ").append(ans);
		}
		
		System.out.println(sb);

	}

}
