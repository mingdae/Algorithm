package task;

import java.io.*;
import java.util.*;

public class SWEA_1227 {
	static int ans;
	static final int T = 10;
	static final int N = 100;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] visited;
	static int[][] maze;
	static int sy, sx; // 시작점 좌표
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			br.readLine(); // 한 줄 넘기기
			
			maze = new int[N][N];
			visited = new boolean[N][N];
			ans = 0; sy = 0; sx = 0 ;
			
			for(int i = 0 ; i < N; i++) {
				String str = br.readLine();
				for(int j = 0 ; j < N; j++) {
					maze[i][j] = str.charAt(j) - '0';
					if(maze[i][j] == 2) {
						sy = i; sx = j; // 출발 좌표 저장
					}
				}
			}
			
			dfs(sy, sx);

			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int y, int x) {
		visited[y][x] = true;
		
		// 도착 여부 판단
		if(maze[y][x] == 3) {
			ans = 1;
			return;
		}
		
		for(int i = 0 ; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 범위 검사
			if(ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			// 벽 검사 + 방문 검사
			if(maze[ny][nx] == 1 || visited[ny][nx])
				continue;
			
			dfs(ny, nx);
		}
	}

}
