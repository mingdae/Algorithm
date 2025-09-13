
import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] arr;
	static int[] select;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		select = new int[m];
		visited = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		combi(0);
	}
	
	public static void combi(int cnt) {
		if(cnt == m) {
			for(int x : select) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0 ; i < n ; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			select[cnt] = arr[i];
			combi(cnt+1);
			visited[i] = false;
		}
	}
}