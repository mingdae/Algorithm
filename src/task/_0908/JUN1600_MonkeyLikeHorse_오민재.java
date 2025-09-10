package task._0908;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 체스판과 같은 이동 방식(장애물 뛰어넘을 수 있음)
 *  - 총 K 번까지만 말 처럼 움직일 수 있음
 *  - 나머지는 상하좌우
 *  - 최소한의 동작으로 시작 -> 도착
 *  - H*W 격차, 0 평지, 1 장애물
 *  
 *  [문제 풀이]
 *  - K번의 말 이동
 *  	1) 이동 구현 => 8방향 dy dx(말이동) / 4방향 dydx(원숭이 이동)
 *  	2) bfs 진행하면서 2가지 분기점을 모두 거리 + 1 로 기록
 *  		2-1) 말 이동횟수가 K 아래일때 말 이동
 *  		2-2) 원숭이 이동
 *  	3)Queue 사이즈를 모두 소모할때까지 가능한 모든 이동 진행 => 같은 레벨 처리
 *  
 *  <주의사항>
 *   1) H = W = 1 일때, 시작점 == 도착점, -1이 아니라 0 출력해야함
 *   2) visited 처리할 때, 말로 이동한 지점과 원숭이 이동한 지점은 따로 처리해야함
 *   		=> 3차원 visited [y][x][used]
 * 
 */

public class JUN1600_MonkeyLikeHorse_오민재 {
	static int n, m, horseCnt, ans;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] horsedy = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] horsedx = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		horseCnt = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m][horseCnt+1];
		ans = -1;
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(n == m && n == 1) {
			System.out.println(0);
		}else {
			bfs(0,0);
			System.out.println(ans);
		}
			
	}
	public static void bfs(int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {y, x, 0});
		visited[y][x][0] = true;
		
		int moveCnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			// 같은 BFS 깊이 처리
			for(int s = 0; s < size; s++) {
				int[] cur = q.poll();
				int cy = cur[0];
				int cx = cur[1];
				int used = cur[2];
				
				// 그냥 이동
				for(int i = 0 ; i < 4; i++) {
					int ny = cy + dy[i];
					int nx = cx + dx[i];
					if(ny < 0 || nx < 0 || ny >= n || nx >= m)
						continue;
					if(visited[ny][nx][used] || map[ny][nx] == 1)
						continue;
					
					if(ny == n-1 && nx == m-1) {
						ans = moveCnt + 1;
						return;
					}
					visited[ny][nx][used] = true;
					q.add(new int[] {ny, nx, used});
				}
				
				// 말 이동
				if(used < horseCnt) {
					for(int i = 0 ; i < 8; i++) {
						int ny = cy + horsedy[i];
						int nx = cx + horsedx[i];
						int nk = used + 1;
						if(ny < 0 || nx < 0 || ny >= n || nx >= m)
							continue;
						if(visited[ny][nx][nk] || map[ny][nx] == 1)
							continue;
						
						if(ny == n-1 && nx == m-1) {
							ans = moveCnt + 1;
							return;
						}
						visited[ny][nx][nk] = true;
						q.add(new int[] {ny, nx, nk});
					}
				}
				
			}
			moveCnt++;
		}
	}
	
	
}
	
	