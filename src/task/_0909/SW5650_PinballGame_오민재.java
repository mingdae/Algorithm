package task._0909;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - N*N 크기 게임판
 * - 5가지 형태 블록 + 웜홀 + 블랙홀
 * - 상하좌우 이동
 * - 블록, 웜홀, 블랙홀 만나지 않으면 직진
 * 
 * 
 * - 방향 반전
 * 		1) 경계면
 * 		2) 블록의 수평, 수직면
 * - 방향 수직 변경
 * 		1) 블록의 경사면
 * - 웜홀
 * 		- 동일한 숫자를 가진 다른 반대편 웜홀로 나옴, 방향 유지
 * 		- 6~10 최대 5쌍
 * - 블랙홀
 * 		- 게임 종료
 * 
 * - 게임 종료
 * 		- 블랙홀
 * 		- 처음 위치로 돌아옴
 * 
 * - 점수 : 벽이나 블록에 부딪힌 횟수
 * - 출발위치, 진행 방향 임의 선정 가능
 * 
 * <목표> 
 * 	점수의 최댓값
 * 
 * 
 * [문제 풀이]
 *  - 모든 빈칸(0)을 출발점으로 게임 시뮬레이션(4방향 모두 고려)
 *    1. 진행 중 벽을 만났을 때 => 방향 반전, 점수++
 *    2. 진행 중 블록을 만났을 때 => 방향 변경(반전 or 수직), 점수++
 *    3. 진행 중 웜홀을 만났을 때 => 페어 위치로 이동
 *    4. 진행 중 블랙홀을 만났을 때 OR 자기 자신 위치로 돌아왔을때 => 게임 종료 점수 최대값 갱신
 *  
 */
public class SW5650_PinballGame_오민재 {
	static int T, n, ans;
	static int[][] board; // 게임판
	static int[][][] worm; // 웜홀 [번호][2][2]
	static int[] wormCnt;
	static int[] dy = {-1, 1, 0, 0}; // 기본 : 상 하 좌 우
	static int[] dx = {0, 0, -1, 1};
	
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	static int[][] dirChange = {
			{},
			{DOWN, RIGHT, UP, LEFT},	// 1번 블록 
			{RIGHT, UP, DOWN, LEFT},	// 2번 블록 
			{LEFT, UP, RIGHT, DOWN},	// 3번 블록 
			{DOWN, LEFT, RIGHT, UP},	// 4번 블록 
			{DOWN, UP, RIGHT, LEFT}		// 5번 블록 
	};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n+2][n+2];
			worm = new int[11][2][2];
			wormCnt = new int[11];
			ans = 0;
			
			for(int i = 0 ; i < n+2 ; i++) {
				Arrays.fill(board[i], 5);
			}
			
			for(int i = 1 ; i <= n ; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 1 ; j <= n ; j++) {
					int input = Integer.parseInt(st.nextToken());
					board[i][j] = input;
					if(input >= 6) {
						int cnt = wormCnt[input];
						worm[input][cnt][0] = i;
						worm[input][cnt][1] = j;
						wormCnt[input]++;
					}
				}
			}
					
			for(int i = 1 ; i <= n ; i++) {
				for(int j = 1 ; j <= n ; j++) {
					if(board[i][j] == 0) {
						for(int dir = 0 ; dir < 4; dir++) { // 4방향 모두 검사
							game(i, j, dir);
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void game(int sy, int sx, int dir) {
		int score = 0;
		int y = sy;
		int x = sx;
		int d = dir;
		while(true) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			int cur = board[ny][nx];
			
			// 게임 종료 조건 : 출발 위치 OR 블랙홀
			if(ny == sy && nx == sx) break;
			if(cur == -1)	break;
			
			// 빈 칸
			if(cur == 0) {
				y = ny;
				x = nx;
				continue;
			}
				 
			
			// 블록 (벽도 5번 블록으로 초기화)
			// 방향 변경 + 점수 추가
			if(cur <= 5) {
				d = dirChange[cur][d]; 
				score++; 
			}else { // 웜홀 => 맞는 페어로 순간이동
				int y1 = worm[cur][0][0];
				int x1 = worm[cur][0][1];
				
				if(ny == y1 && nx == x1) {
					ny = worm[cur][1][0];
					nx = worm[cur][1][1];
				}else {
					ny = y1;
					nx = x1;
				}
			}
			
			y = ny;
			x = nx;
		}

		ans = Math.max(score, ans);
	}
	
	
}
