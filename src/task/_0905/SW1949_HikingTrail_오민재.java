package task._0905;

import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N*N
 *  - 1) 가장 높은 지점 찾기 -> 출발 지점 후보
 *  - 2) 4방 이동 + 더 높이가 낮은곳으로 이동
 *  - 3) 딱 한 곳만 K만큼 깎을 수 있음
 *  
 *  [목표]
 *  - 가장 긴 등산로
 *  
 *  [풀이]
 *  - 출발 지점 후보들 좌표 저장
 *  - 각 후보들에서 모두 dfs
 *  - 이 때 각 후보들에서 K 깎는 모든 경우의수 탐색
 *  - 가장 긴 등산로 출력
 * 
 * 
 * 
 */


public class SW1949_HikingTrail_오민재 {
	
	static int T, N, K, ans;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayList<int[]> start;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			
			map = new int[N][N];
			start = new ArrayList<>();
			
			int maxVal = 0;
			
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxVal = Math.max(maxVal, map[i][j]);
				}
			}
			
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					if(map[i][j] == maxVal) {
						start.add(new int[] {i, j}); // 출발 지점 후보 좌표 저장
					}
				}
			}
			
			for(int i = 0 ; i < N; i++) {
				for(int j =0  ; j < N; j++) {
					int height = map[i][j]; // 현재 높이
					for(int minus = 0; minus <= K && height - minus >=0 ; minus++) {
						map[i][j] = height - minus;
						
						for(int[] pos : start) {
							dfs(pos[0], pos[1], 1);
						}						
					}
					map[i][j] = height;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}
	
	
	public static void dfs(int y, int x, int cnt) {
		
		ans = Math.max(ans, cnt);
		for(int i = 0 ; i < 4 ; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if(map[ny][nx] >= map[y][x])
				continue;
			dfs(ny, nx, cnt+1);
		}

	}
}

