package task;
import java.io.*;
import java.util.*;

public class JUN1325_Hacking_오민재 {
	static int n, m;
	static ArrayList<Integer>[] trust;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		trust = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++){
			trust[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			trust[from].add(to);
		}

		int[] count = new int[n+1];
		
		int max = 0;
		
		for(int i = 1; i <= n; i++){
			count[i] = bfs(i);
			max = Math.max(max, count[i]);
		}

		for(int i = 1; i <= n; i++){
			if(count[i] == max){
				sb.append(i).append(" ");
			}
		}

		System.out.println(sb);
	}

	public static int bfs(int start){
		visited = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		int cnt = 1;
		visited[start] = true;
		q.add(start);
		
		while(!q.isEmpty()){
			int node = q.poll();
			for(int next : trust[node]){
				if(!visited[next]){
					q.add(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}
