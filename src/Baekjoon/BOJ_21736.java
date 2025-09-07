package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_21736 {
	static int n, m, ans;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static char[][] campus;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = 0;
		campus = new char[n][m];
		visited = new boolean[n][m];
		
		int y = 0;
		int x = 0;

		for(int i = 0 ; i < n ; i++) {
			String str = br.readLine();
			for(int j = 0; j < m ; j++) {
				campus[i][j] = str.charAt(j);
				if(campus[i][j] == 'I') {
					y = i;
					x = j;
				}
			}
		}
		
		dfs(y, x);

		System.out.println((ans == 0) ? "TT" : ans );
	}
	
	public static void dfs(int y, int x) {
		visited[y][x] = true;
		
		// 사람을 만나는 경우
		if(campus[y][x] == 'P') {
			ans++;
		}
		
		// 4방 탐색
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 범위 검사
			if(ny < 0 || nx < 0 || ny >= n || nx >= m)
				continue;
			
			// 벽 검사 + 방문 검사
			if(campus[ny][nx] == 'X' || visited[ny][nx])
				continue;
			
			dfs(ny, nx);
		}

	}
}
