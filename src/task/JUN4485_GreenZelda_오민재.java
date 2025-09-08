package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 화폐 단위 루피
 * - 도둑루피 : 소지한 루피 감소
 * - 도둑루피만 가득한 N*N 동굴, 시작점 (0,0), 도착점(N-1, N-1)
 * - 각 칸마다 도둑루피, 지나면 그만큼 소지금 잃음
 * - 잃는 금액 최소로 이동, 상하좌우 1칸 이동
 * => 최단거리 문제(다익스트라)
 */

public class JUN4485_GreenZelda_오민재 {
	static int n, ans, tc;
	static int[][] map;
	static final int INF = Integer.MAX_VALUE;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		tc = 0;
		while(true) {
			tc++;
			n = Integer.parseInt(br.readLine());

			// 입력이 0이면 종료
			if(n == 0)
				break;
			
			map = new int[n][n];
			ans = 0;
			
			for(int i = 0; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = dijkstra(0, 0);
			
			sb.append("Problem").append(" ").append(tc).append(":").append(" ").append(ans).append('\n');
		}
		
		System.out.println(sb);
		
	}
	
	public static int dijkstra(int sy, int sx) {
		int[][] minDist = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			Arrays.fill(minDist[i], INF);
		}
		
		minDist[sy][sx] = map[sy][sx];
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		pq.offer(new int[] {sy, sx, minDist[sy][sx]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int y = cur[0];
			int x = cur[1];
			int totalDist = cur[2];
			
			// 종료 조건
			if(y == n-1 && x == n-1)
				return totalDist;
			
			for(int dir = 0 ; dir < 4 ;dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || minDist[ny][nx] != INF)
					continue;
				
				if(minDist[ny][nx] > totalDist + map[ny][nx]) {
					minDist[ny][nx] = totalDist + map[ny][nx];
					pq.offer(new int[] {ny, nx, minDist[ny][nx]});
				}
			}
			
		}
		return -1;
	}

}

	
	