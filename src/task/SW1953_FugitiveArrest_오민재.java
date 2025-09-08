package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 목표 : 탈주범이 있을 수 있는 위치의 개수
 * - 시간당 1 거리 이동
 * - 7 종류 구조물
 * 	 1) 상, 하, 좌, 우
 * 	 2) 상, 하
 *   3) 좌, 우
 *   4) 상, 우
 *   5) 하, 우
 *   6) 하, 좌
 *   7) 상, 좌
 * 
 * - N*M 맵, 경과시간 L(1이 시작점)
 * - 2차원 델타 배열 dy[type][dir] 로 방향 결정
 * - isConnected -> 방향이 dir일때 올 수 있는 다음 map[ny][nx] 값은 정해져있음
 * 
 * 
 */
public class SW1953_FugitiveArrest_오민재 {
	static int T, n, m, ans;
	static int sy, sx, time; // 시작 위치, 경과 시간
	static int[][] map;
	static int[][] dist;
	
	static int[][] dy = {
			{0, 0, 0, 0}, // 0번. 이동 X
			{-1, 1, 0, 0}, // 1번. 상하좌우
			{-1, 1, 0, 0}, // 2번. 상하
			{0, 0, 0, 0}, // 3번. 좌우
			{-1, 0, 0, 0}, // 4번. 상우
			{0, 1, 0, 0}, // 5번. 하우
			{0, 1, 0, 0}, // 6번. 하좌
			{-1, 0, 0, 0} // 7번. 상좌
	};
	
	static int[][] dx = {
			{0, 0, 0, 0}, // 0번. 이동 X
			{0, 0, -1, 1}, // 1번. 상하좌우
			{0, 0, 0, 0}, // 2번. 상하
			{0, 0, -1, 1}, // 3번. 좌우
			{0, 0, 0, 1}, // 4번. 상우
			{0, 0, 0, 1}, // 5번. 하우
			{0, 0, -1, 0}, // 6번. 하좌
			{0, 0, -1, 0} // 7번. 상좌
	};
	
	static class Node{
		int y, x, type;

		public Node(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			ans = 0;
			map = new int[n][m];
			dist = new int[n][m];
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < m ; j++) {
					dist[i][j] = -1;
				}
			}
			
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < m ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs(sy, sx);
			
			for(int i = 0; i < n; i++) {
				for(int j = 0 ; j < m; j++) {
					if(dist[i][j] != -1) {
						ans++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void bfs(int y, int x) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(y, x, map[y][x]));
		dist[y][x] = 0;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;
			int ctype = cur.type;
			
			// 종료 조건
			if(dist[cy][cx] == time-1)
				break;

			for(int dir = 0 ; dir < 4; dir++) {
				int ny = cy + dy[ctype][dir];
				int nx = cx + dx[ctype][dir];
				
				// 범위 검사
				if(ny < 0 || nx < 0 || ny >= n || nx >= m) 
					continue;
				
				// 방문검사 + 갈수 없는 곳 검사
				if(dist[ny][nx] != -1 || map[ny][nx] == 0)
					continue;
				
				// 연결 검사
				if(!isConnected(ny, nx, dir))
					continue;
				
				dist[ny][nx] = dist[cy][cx] + 1;
				
				q.offer(new Node(ny, nx, map[ny][nx]));
			}
			
		}
		
	}
	
	public static boolean isConnected(int y, int x, int dir) {
		int type = map[y][x];
		switch(dir) {
			case 0 :  // 상 -> 하 필요
				return (type == 1 || type == 2 || type == 5 || type == 6);
				
			case 1 : // 하 -> 상 필요
				return (type == 1 || type == 2 || type == 4 || type == 7);

			case 2 : // 좌 -> 우 필요
				return (type == 1 || type == 3 || type == 4 || type == 5);
				
			case 3 : // 우 -> 좌 필요
				return (type == 1 || type == 3 || type == 6 || type == 7);
		}
		
		return false;
	}
	
	
}
