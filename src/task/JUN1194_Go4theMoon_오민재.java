package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 * - '.' 빈칸, 이동 O
 * - '#' 벽, 이동 X
 * - 'a~f' 열쇠, 이동 O, 처음 들어가면 열쇠 집음, 여러번 사용 가능
 * - 'A~F' 문, 대응하는 열쇠가 있을 때만 이동 가능
 * - '0' : 현재 위치
 * - '1' : 출구
 * 
 * - 이동 횟수의 최소값 -> 최단거리
 * - N*M 크기 미로
 * 
 * [문제 풀이]
 * - 열쇠가 있을때 한 방문과 열쇠가 없을 때 한 방문을 다르게 처리해줘야함
 * - 6종류 열쇠 -> 비트마스킹으로 0~63으로 표현
 * 
 * 이동
 * - 1) 벽, 범위 검사 -> 이동 X
 *   2) '1' -> 종료
 *   3) 문 -> 필요한 열쇠가 있는지 검사 (&연산), 없으면 이동 X
 *   4) 열쇠 -> 현재 키 값에서 | 연산으로 키 더 해주기
 *   5) 방문하지 않았으면 -> Queue에 넣어주기
 *   	5-1) key가 있을 때 방문한 것과 없을 때 방문한걸 다른 상태로 처리 
 *   	5-2) Queue가 빌때까지 처리하고, 다 비면 새로 삽입 -> Depth 처리
 * 
 */
public class JUN1194_Go4theMoon_오민재 {
	
	static int n, m, ans;
	static int sy, sx; // 시작위치 
	static char[][] map;
	static boolean[][][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	
	static class Node{
		int y, x, key;
		Node(int y, int x, int key) {
			this.y = y;
			this.x = x;
			this.key = key;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = 0;
		map = new char[n][m];
		
		visited = new boolean[n][m][64];
		
		for(int i = 0; i < n ;i++) {
			String str = br.readLine();
			for(int j = 0 ;j < m; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					sy = i; sx = j;
					map[i][j] = '.';
				}
			}
		}
		
		ans = bfs(sy, sx);
		
		System.out.println(ans);
	}
	
	public static int bfs(int sy, int sx) {
		Queue<Node> q = new ArrayDeque<>();
		
		q.add(new Node(sy, sx, 0));
		visited[sy][sx][0] = true;
		
		int steps = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Node cur = q.poll();
				int y = cur.y;
				int x = cur.x;
				int key = cur.key;
				
				if(map[y][x] == '1') {
					return steps;
				}
				
				for(int dir = 0; dir < 4; dir++) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					
					// 범위 검사
					if(ny < 0 || nx < 0 || ny >= n || nx >= m)
						continue;
					
					// 벽 검사
					if(map[ny][nx] == '#')
						continue;
					
					int nkey = key;
					// 문을 만났을 때, 열쇠가 없으면 건너뜀
					if('A' <= map[ny][nx] && map[ny][nx] <= 'F') {
						int needKey = map[ny][nx] - 'A';
						if((key & (1 << needKey)) == 0)
							continue;
					}
					
					// 열쇠를 만났을 때, 열쇠 얻음 -> key 비트 값 변경
					if('a' <= map[ny][nx] && map[ny][nx] <= 'f') {
						int getKey = map[ny][nx] - 'a';
						nkey = key | (1 << getKey);
					}
					
					if(!visited[ny][nx][nkey]) {
						visited[ny][nx][nkey] = true;
						q.offer(new Node(ny, nx, nkey));
					}
				}

			}
			steps++;		
		}
		return -1;
	}
}
	
	