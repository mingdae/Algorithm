package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - N 8~28 4의 배수
 * - 16 진수 숫자 N 개
 * - 한 방향씩 회전 -> 뒤의 문자열 앞에 추가 OR 첫 문자열 뒤로 추가
 * - 만들 수 있는 모든 수 중 K 번째로 큰 수
 * - 중복 제거 필요
 * 
 * [문제 풀이]
 * - cnt = N/4 : 비밀번호 문자열 길이, 회전 횟수
 * - cnt번 회전하는 동안
 * 		- cnt 길이 끊어서 문자열로 변환 -> 10진수로 변환(Long)
 * 		- 중복 제거, 정렬 -> TreeSet 삽입
 * - 회전
 * 		- 맨 앞의 문자열 뒤로 추가, 다음 탐색 시 해당 인덱스부터 탐색 시작
 * 
 * 
 * 
 */
public class SW5658_TreasureBox_오민재 {
	
	static int T, n, k;
	static long ans;
	static Set<Long> set;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ans = 0;
			
			// cnt = 비밀번호의 길이 = 회전 횟수
			int cnt = n / 4; 
			
			// 문자열로 입력 
			String str = br.readLine();
			
			set = new TreeSet<>(Collections.reverseOrder());
			
			// cnt번 회전
			for(int rotateCnt = 0 ; rotateCnt < cnt; rotateCnt++) {
				for(int stringIdx = rotateCnt ; stringIdx < n + rotateCnt; stringIdx += cnt) {
					// 1. cnt 자리씩 끊어서 String으로 변환 -> 10진수로 변환
					String tmp = str.substring(stringIdx,stringIdx+cnt);
					long demical = Long.parseLong(tmp, 16);
					
					// 2. TreeSet에 삽입
					set.add(demical);
				}
				
				// 3. 회전 처리 : 맨 앞의 숫자를 뒤로
				str += str.charAt(rotateCnt);
				
			}
			
			int idx = 1;
			
			// 4. TreeSet에서 K번째 큰 수
			for(long v : set) {
				if(idx++ == k) {
					ans = v;
					break;
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
}
