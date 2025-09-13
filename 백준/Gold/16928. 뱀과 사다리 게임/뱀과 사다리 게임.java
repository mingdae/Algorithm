
import java.io.*;
import java.util.*;

public class Main {
	static int ladder, snake, ans;
	static int[] dist;
	static Map<Integer, Integer> ladderMap, snakeMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		ladder = Integer.parseInt(st.nextToken());
		snake = Integer.parseInt(st.nextToken());
		dist = new int[101];
		ladderMap = new HashMap<>();
		snakeMap = new HashMap<>();
		ans = 0;
		
		
		for(int i = 0 ; i < ladder; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			ladderMap.put(key, value);
		}
		
		for(int i = 0 ; i < snake; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			snakeMap.put(key, value);
		}
		
		bfs(1);
		System.out.println(ans);
	}
	
	public static void bfs(int start) {
		dist[start] = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		
		boolean arrive = false;
		while(!q.isEmpty() && !arrive) {
			int cur = q.poll();
			
			/* 주사위 1~6 이동*/
			for(int move = 1; move <= 6; move++) {
				int next = cur + move;
				
				// 도착
				if(next >= 100) {
					arrive = true;
					ans = dist[cur] + 1;
					break;
				}
				
				// 사다리, 뱀
				if(ladderMap.containsKey(next)) {
					next = ladderMap.get(next);
				}else if(snakeMap.containsKey(next)) {
					next = snakeMap.get(next);
				}
				
				// 방문 검사
				if(dist[next] != 0) continue;
				dist[next] = dist[cur] + 1;
				q.offer(next);

			}
		}
		
	}
}