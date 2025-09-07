package task;

import java.io.*;
import java.util.*;


/*
 * [문제 분석]
 * - R*C 격자
 * - 첫째 열 : 출발지점, 마지막 열 : 도착 지점
 * 
 * [메인 로직]
 * - 파이프 연결 
 * 	 1. 건물에는 파이프 X
 *   2. 파이프라인은 무조건 첫째 열 시작, 마지막 열 끝
 *   3. 이동 : 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선
 *   	- dx = {1, 1, 1} / dy = {-1, 0, 1}
 *   4. 되도록 많이 => 그리디, 제일 위에서부터 탐색 시작, 이동은 가능한 한 위로 이동
 *   	(오른쪽 위부터 탐색)
 *   5. 경로 겹칠 수 없음, 서로 접할 수 없음, 칸을 지나는 파이프는 하나
 *   	=> 지나온 길을 visited 처리
 *   	=> 이미 지나온 길이 마지막까지 갈 수 없는 길이면, 더이상 재 탐색 필요 X => 원복 불필요
 *   
 *   [목표]
 *   1. 최대 개수
 *   
 *   
 */

public class JUN3109_Bakery_오민재 {
	static int r, c, ans;
	static int[] dx = {1, 1, 1};
	static int[] dy = {-1, 0, 1}; // 그리디, 가능한 한 위쪽으로 먼저 탐색
	static boolean[][] visited; // 처음부터 true, false로 배열 설정
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		visited = new boolean[r][c];
		
		for(int i = 0 ; i < r; i++) {
			String input = br.readLine();
			for(int j = 0; j < c; j++) {
				visited[i][j] = (input.charAt(j) == '.') ? false : true;
			}
		}
		
		ans = 0;
		for(int i = 0 ; i < r ; i++) {
			dfs(i, 0);
		}
		
		System.out.println(ans);
	}
	
	public static void dfs(int y, int x) {
		visited[y][x] = true;
		// 기저조건
		if(x == c-1) { // 마지막 열에 도착
			ans++;
			return;
		}
		
		// 반복

		for(int i = 0 ; i < 3; i++) { // 3방향 탐색
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 범위 조건
			if(ny < 0 || nx < 0 || ny >= r || nx >= c || visited[ny][nx])
				continue;
			if(!visited[ny][nx]) { // 탐색이 가능할 때
				dfs(ny, nx);
				break;
			}
		}
		
	}
}
