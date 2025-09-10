package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - N*N 활주로
 * - 각 지형의 높이
 * - 활주로 가로, 세로로 건설 가능한지 확인
 * - 높이 동일한 구간에서 건설 가능
 * - 높이 다른 구간 : 경사로 설치
 * - 경사로 : 길이 X, 높이 1
 * - 높이 차이가 1이고 낮은 지형 높이가 동일하게 경사로 길이만큼 연속되어야 설치 가능
 * - 활주로를 건설 할 수 있는 경우의 수 계산
 * 
 * [문제 풀이]
 * - 모든 가로, 세로 변에 대해서 완전 탐색
 * - canBuild 한 변에 대한 검사
 * 		1. 인접한 두 지역의 높이가 같으면 -> continue
 * 		2. 다르면
 * 			2-1) 높이 차가 2 이상이면 -> return false;
 * 			2-2) 오르막일때 -> 지금 위치 이전 x 개의 좌표가 
 * 				1) 모두 같은 높이 && 2) 활주로 설치 X && 3) 범위 안에 있을때
 * 				설치 가능 
 * 			2-3) 내리막일때
 * 				1) 모두 같은 높이 && 2) 활주로 설치 X && 3) 범위 안에 있을때
 * 				설치 가능 
 * 
 */


public class SW4014_Runway_오민재 {
	static int T, n, x, ans;
	static int[][] map;
	static int[] test;
	static boolean[] used;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			ans = 0;
			
			map = new int[n][n];
			test = new int[n];
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 가로
			for(int row = 0 ; row < n ; row++) {
				for(int col = 0; col < n; col++) {
					test[col] = map[row][col];
				}
				
				if(canBuild(test)) 
					ans++;
			}
			
			// 세로
			for(int col = 0 ; col < n ; col++) {
				for(int row = 0; row < n; row++) {
					test[row] = map[row][col];
				}
				
				if(canBuild(test)) 
					ans++;
			}

			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	// 한 줄 설치 가능한지 확인
	public static boolean canBuild(int[] line) {
		used = new boolean[n];
		
		// 검사
		for(int i = 0; i < n-1; i++) {
			int diff = line[i+1] - line[i];
			// 1. 같은 높이면 continue;
			if(diff == 0)	continue;
			
			// 2. 다른 높이면
			// 2-1) 인접한 곳이 높이차가 2 이상이면 불가
			if(Math.abs(diff) >= 2)	return false;
			
			// 2-2) 오르막
			if(diff == 1) {
				int height = line[i];
				
				// 범위 검사 + x만큼 연속되어 있는지 + 이미 설치되지 않았는지
				for(int k = 0 ; k < x; k++) {
					int idx = i - k;
					if(idx < 0 || line[idx] != height || used[idx])
						return false;
				}
				
				// 설치
				for(int k = 0 ; k > x; k++)
					used[i-k] = true;
			}
			else {  // 2-3) 내리막
				int height = line[i+1];
				
				// 범위 검사 + x만큼 연속되어 있는지 + 이미 설치되지 않았는지
				for(int k = 1 ; k <= x; k++) {
					int idx = i + k;
					if(idx >= n || line[idx] != height || used[idx])
						return false;
				}
				
				// 설치
				for(int k = 1 ; k <= x; k++)
					used[i+k] = true;
			}
		}
		return true;
	}
}
