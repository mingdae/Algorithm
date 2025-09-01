package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 4*4 격자, 0~9 숫자
 * - 동서남북 6번 이동, 차례대로 이어붙이면 7자리 숫자
 * - 재방문 가능, 0으로 시작 가능
 * - 범위 검사 필요
 * - 만들 수 있는 서로다른 7자리수의 개수
 * 
 * [문제 풀이]
 * - Set으로 중복제거
 * - DFS 탐색하면서 매번 val*10 + newVal  로 자리수 증가
 * - cnt == 6 이되면 val 을 Set에 삽입, 중복 제거
 * - 마지막에 Set의 size 출력
 * 
 * <탐색>
 * - 4방탐색, 방문 처리 X => DFS로 6번 카운트
 * - 시작위치는 배열의 모든 위치에서 시작해야함
 */
public class SW2819_GridNumbers_오민재 {

	static int T, ans;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			map = new int[4][4];
			ans = 0;
			set = new HashSet<>();
			
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 4; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < 4; i++) {
				for(int j = 0 ; j < 4; j++) {
					dfs(i, j, 0, map[i][j]);
				}
			}
			
			ans = set.size();
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	// y,x : 시작 좌표, cnt : 탐색 횟수, val : 현재 값
	public static void dfs(int y, int x, int cnt, int val) {
		// 기저 조건
		if(cnt == 6) {
			set.add(val);
			return;
		}
		
		for(int dir = 0 ; dir < 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
				continue;
			
			// 다음 탐색
			dfs(ny, nx, cnt+1, val*10 + map[ny][nx]);
			
		}
	}
	
	
}
