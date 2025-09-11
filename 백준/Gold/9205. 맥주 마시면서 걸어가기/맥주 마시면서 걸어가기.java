import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 한 박스 20개
 *  - 50미터에 한 병씩 마심
 *  - 50미터를 가려면 -> 그 직전에 한 병 마셔야함(충전 개념)
 *  - 빈 병 버리고 새 맥주 살 수 있음, 단 20병 초과 X
 *  - 출발 할 때도 한 병 마시고 가야함
 * 
 * [문제 풀이]
 * - 시작점에서부터 1000 거리를 이동 할 때 그 안에 편의점 or 도착점이 있는지 확인
 * - 없으면 종료, sad
 * - 편의점 있으면 => 충전, 다시 1000
 * - 도착점 있으면 => 종료, happy
 * - x, y 상태 공간 트리? -> x+y 값 확인, x+y의 값이 이전 값보다 1000이 넘어갈 수 없음
 * - 편의점 좌표를 싹 Queue에 넣어놓고 확인?
 * 
 */

public class Main {
	static int T, storeCnt;
	static int sy, sx, ty, tx; // 출발, 도착 지점 좌표
	static Point[] stores; // 편의점 좌표
 	static boolean[] visited; // 편의점 방문 여부 
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			storeCnt = Integer.parseInt(br.readLine());
			stores = new Point[storeCnt];
			visited = new boolean[storeCnt];
			/* 시작 지점 좌표 */
			st = new StringTokenizer(br.readLine());
			
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			Point sp = new Point(sy, sx);
			
			/* 편의점 좌표 */
			for(int i = 0 ; i < storeCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()); 
				int x = Integer.parseInt(st.nextToken()); 
				stores[i] = new Point(y, x);
			}
			
			/* 도착 지점 좌표 */
			st = new StringTokenizer(br.readLine());
			ty = Integer.parseInt(st.nextToken());
			tx = Integer.parseInt(st.nextToken());
			Point tp = new Point(ty, tx);
			
			/* 처음부터 바로 도달 가능하면 종료*/
			if(canMove(sp, tp))	{
				sb.append("happy").append('\n');
				continue;
			}
			
			/* BFS */
			Queue<Point> q = new ArrayDeque<>();
			q.offer(sp);
			boolean ans = false;
			while(!q.isEmpty()) {
				Point cur = q.poll(); // 현재 위치
				
				// 기저 조건 : 도착점에 도달할 수 있을 때
				if(canMove(cur, tp)) {
					ans = true;
					break;
				}
				
				// 탐색 : 편의점 좌표중에서 탐색
				for(int i = 0 ; i < storeCnt; i++) {
					
					// 이미 방문한 편의점
					if(visited[i])	continue;
					
					// 방문 가능한 편의점
					if(canMove(cur, stores[i])) {
						visited[i] = true;
						q.offer(stores[i]);
					}
				}
			}

			if(ans)	sb.append("happy").append('\n');
			else	sb.append("sad").append('\n');
		}
		System.out.println(sb);
	}
	
	/* 이동 가능 : 거리가 1000이하인지 확인 */
	public static boolean canMove(Point p1, Point p2) {
		int dist = Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
		if(dist > 1000)	return false;
		return true;
	}

}
	
	