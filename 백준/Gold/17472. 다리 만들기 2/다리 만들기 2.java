import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 모든 섬을 다리로 연결  
 *  - N*M 크기 격자, 땅 or 바다
 *  - 섬 : 땅이 상하좌우로 붙어있는 덩어리
 *  - 다리는 바다에만 건설
 *  - 모든 섬을 연결, 한 다리의 방향이 중간에 바뀔 수 없음 => 가로 OR 세로
 *  - 다리의 길이는 2 이상
 *  - 다리 길이의 최소값 => MST
 * 
 * [문제 풀이]
 * - MST를 쓸 수 있는 형태로 만들어야함
 * - 간선 리스트 필요
 * 		정점 : 각 섬 번호
 * 		간선 : 섬 - 섬을 잇는 다리
 * 
 * 1. 각 섬에 번호 라벨링 => BFS, DFS 4방 탐색, Flood Fill
 * 2. 완탐 => 섬 - 섬으로 놓을 수 있는 모든 다리 후보 탐색
 * 		2-0) 0이 아닌 모든 정점에서 탐색 -> 그때의 섬 번호 저장 필요
 * 		2-1) 섬의 모든 정점 (y,x)에 대하여 4방 탐색 => 
 * 				1. 0이 아니면 continue;
 * 				2. 0이면 그 방향으로만 탐색 진행, 다리길이++
 * 					탐색 중 0이 아닌 숫자를 만나면 다리 후보(길이 2이상)
 * 					탐색 중 0이 아닌 숫자를 만나기 전에 벽을 만나면 종료
 * 				3. 시작 섬, 도착 섬 번호 저장, from, to, weight 만들기
 * 		2-2) 다리 후보 간선 리스트에 저장
 * 3. Kruskal 
 * 
 * 
 * 
 */

public class Main {
	
	/* Kruskal Edge */
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static PriorityQueue<Edge> pq;
	
	/* Union Find */
	static int[] parents;
	
	public static void make() {
		for(int i = 1; i <= islandNum; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int x) {
		if(parents[x] == x)	return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb)	return false;
		if(pa > pb)	parents[pb] = pa;
		else parents[pa] = pb;
		
		return true;
	}
	
	/* 기본 변수 */
	static int n, m, ans, islandNum;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		
		ans = 0;
		
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/* 1. 각 섬에 번호 라벨링 => BFS, DFS 4방 탐색, Flood Fill */
		islandNum = 1;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					bfs(i, j, islandNum);
					islandNum++;
				}
			}
		}
		islandNum--;
		/* 섬 라벨링 디버깅 */
//		printMap();
		
		 /* 2. 완탐 => 섬 - 섬으로 놓을 수 있는 모든 다리 후보 탐색 */
//	  		2-0) 0이 아닌 모든 정점에서 탐색 -> 그때의 섬 번호 저장 필요
//	  		2-1) 섬의 모든 정점 (y,x)에 대하여 4방 탐색 => 
//	  				1. 0이 아니면 continue;
//	  				2. 0이면 그 방향으로만 탐색 진행, 다리길이++
//	  					탐색 중 0이 아닌 숫자를 만나면 다리 후보(길이 2이상)
//	  					탐색 중 0이 아닌 숫자를 만나기 전에 벽을 만나면 종료
//	  				3. 시작 섬, 도착 섬 번호 저장, from, to, weight 만들기
//	  		2-2) 다리 후보 간선 리스트에 저장
		
	
		pq = new PriorityQueue<>();
		
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m; j++) {
				if(map[i][j] != 0) {
					int from = map[i][j]; // 출발 섬 번호
					for(int dir = 0 ; dir < 4; dir++) { // 탐색 시작하면 방향은 고정
						dfs(i, j, from, dir, 0);
					}
				}
			}
		}
		
		/* 다리 후보 디버깅 */
//		printBridge();

		
		
		/* 3. Kruskal */
		parents = new int[islandNum+1];
		make();
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int from = cur.from;
			int to = cur.to;
			int weight = cur.weight;
			
//			System.out.println("from : " + from + " to : " + to + " len : " + weight);
			// union 실패 => 사이클 continue;
			if(!union(from, to))	continue;
			
//			System.out.println("here! cnt : " + cnt);
			// union 성공 => 누적
			// n-1번 성공 => MST 완성
			ans += weight;
			if(++cnt == islandNum-1)	break;
		}
		
//		System.out.println("result! cnt : " + cnt + " islandNum : " + islandNum);
		if(cnt != islandNum-1)	System.out.println(-1);
		else	System.out.println(ans);
		
		
	}
	
	public static void bfs(int sy, int sx, int num) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sy, sx});
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			map[y][x] = num; // 섬 라벨링
			
			for(int dir = 0 ; dir < 4 ; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				
				// 범위검사 + 방문검사
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx])
					continue;
				// 섬 검사
				if(map[ny][nx] == 0)
					continue;
				
				visited[ny][nx] = true;
				q.offer(new int[] {ny, nx});
			}
		}
	}
	
	// sy, sx : 출발 좌표, snum : 출발 섬 번호, dir : 방향, len : 다리 길이
	public static void dfs(int sy, int sx, int from, int dir, int len) {
		int ny = sy + dy[dir];
		int nx = sx + dx[dir];
		
		// 범위검사, 같은 섬이면 가지치기
		if(ny < 0 || nx < 0 || ny >= n || nx >= m)	return;
		if(map[ny][nx] == from) return;
		
		
		// 종료 조건 : 출발지와 다른 번호의 섬을 만났을 때
		// 다리 길이가 2이상이면 유효한 다리, 다리 후보 추가
		// 간선리스트, PQ 추가
		if(map[ny][nx] != 0 && map[ny][nx] != from ) {
			int to = map[ny][nx];
			if(len >= 2) 
				pq.offer(new Edge(from, to, len));
			
			return;
		}
		
		// 바다, 계속해서 탐색
		if(map[ny][nx] == 0) {
			dfs(ny, nx, from, dir, len+1);
		}
	}
	
	
	
	
	public static void printMap() {
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < m ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void printBridge() {
		for(Edge e : pq) {
			System.out.println("from : " + e.from + " to : " + e.to + " len : " + e.weight);
		}
		
	}
	
}
	
	