
import java.io.*;
import java.util.*;

public class Main {
	static int n, ans;
	static char[][] map;
	static int[][] color;
	static int red, green, blue;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static final int RED = 1, GREEN = 2, BLUE = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		color = new int[n][n];
		red = green = blue = 0;
		
		for(int i = 0 ; i < n ; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n; j++) {
				if(color[i][j] == 0)
					bfs(i, j, map[i][j]);
			}
		}
		ans = red + green + blue;
		sb.append(ans).append(" ");
		
		red = blue = ans = 0;
		color = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n; j++) {
				if(color[i][j] == 0)
					colorBfs(i, j, map[i][j]);
			}
		}
		ans = red + blue;
		sb.append(ans);
		System.out.println(sb);
	}
	
	
	public static void bfs(int sy, int sx, char start) {
		int label = 0;
		if(start == 'R') {
			label = ++red;
		}else if(start == 'G') {
			label = ++green;
		}else {
			label = ++blue;
		}
		
		color[sy][sx] = label;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sy, sx}); 
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			
			for(int i = 0 ; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= n)	continue;
				if(color[ny][nx] != 0)	continue;
				if(map[ny][nx] != start)	continue;
				color[ny][nx] = label;
				q.offer(new int[] {ny, nx});
			}
		}
	}
	
	public static void colorBfs(int sy, int sx, char start) {
		int label = 0;
		boolean isBlue = false;
		if(start == 'R' || start == 'G') {
			label = ++red;
		}else {
			label = ++blue;
			isBlue = true;
		}
		
		color[sy][sx] = label;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sy, sx}); 
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			
			for(int i = 0 ; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= n)	continue;
				if(color[ny][nx] != 0)	continue;
				
				if(isBlue && map[ny][nx] != 'B')	continue;
				else if(!isBlue && map[ny][nx] == 'B')	continue;
				color[ny][nx] = label;
				q.offer(new int[] {ny, nx});
			}
		}
	}
	
}