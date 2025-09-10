package task;
import java.io.*;
import java.util.*;


public class SWEA_1249 {
	static int T, n, ans;
	static final int INF = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx=  {0, 0, -1, 1};
	
	static class Node implements Comparable<Node>{
		int y, x, totalDist;

		public Node(int y, int x, int totalDist) {
			super();
			this.y = y;
			this.x = x;
			this.totalDist = totalDist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.totalDist, o.totalDist);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			ans = 0;
			map = new int[n][n];
			
			for(int i = 0 ; i < n ; i++) {
				String str = br.readLine();
				for(int j = 0 ; j < n ; j++) {
					map[i][j] =  str.charAt(j) -'0';
				}
			}
			
			ans = dijkstra(0, 0);
			
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int dijkstra(int sy, int sx) {
		int[][] minDist = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 전처리
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				minDist[i][j] = INF;
			}
		}
		minDist[sy][sx] = 0;
		pq.offer(new Node(sy, sx, minDist[sy][sx]));
		
		while(!pq.isEmpty()) {
			// step 0. 미방문 정점 중 가장 가까운 정점 찾기
			Node cur = pq.poll();
			int y = cur.y;
			int x = cur.x;
			int curDist = cur.totalDist;
			
			// 방문 검사
			if(visited[y][x])	continue;
			visited[y][x] = true;
			
			// 종료 조건
			if(y == n-1 && x == n-1)	
				return curDist;
			
			// step 1. 정점과 연결되어있는 구역 거리 비교
			for(int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				
				// 범위, 방문 검사
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx])	continue;
				
				// 거리 검사
				if(minDist[ny][nx] > curDist + map[ny][nx]) {
					minDist[ny][nx] = curDist + map[ny][nx];
					pq.offer(new Node(ny, nx, minDist[ny][nx]));
				}
			}
		}
		
		return -1;
	}
	
	
}
