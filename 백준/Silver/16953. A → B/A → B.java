
import java.io.*;
import java.util.*;

public class Main {
	static int from, target, ans;
	static Map<Integer, Integer> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		from = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		ans = -1;
		bfs(target);
		
		System.out.println(ans);
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		map.put(start, 0);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur % 2 == 0) {
				int next = cur / 2;
				if(!map.containsKey(next)) {
					map.put(next, map.get(cur) + 1);
					if(next == from) {
						ans = map.get(next)+1;
						break;
					}
					q.offer(next);
				}
			}
			
			if(cur > 10 && cur % 10 == 1) {
				int next = cur / 10;
				if(!map.containsKey(next)) {
					map.put(next, map.get(cur) + 1);
					if(next == from) {
						ans = map.get(next)+1;
						break;
					}
					q.offer(next);
				}
			}
		}
	}
}