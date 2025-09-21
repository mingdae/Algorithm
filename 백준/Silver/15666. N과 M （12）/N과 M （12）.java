import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] arr;
	static int[] selected;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		visited = new boolean[n];
		selected = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		combi(0, 0);
		System.out.println(sb);
	}
	
	public static void combi(int idx, int cnt) {
		
		if(cnt == m) {
			for(int i = 0 ; i < m; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		int lastUsed = Integer.MAX_VALUE;
		for(int i = idx ; i < n ; i++) {
			if(arr[i] == lastUsed)	continue;
			
			selected[cnt] = arr[i];
			lastUsed = arr[i];
			combi(i, cnt+1);
		}
	}
}
	
	