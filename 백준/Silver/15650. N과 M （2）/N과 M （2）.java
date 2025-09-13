
import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] select;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		select = new int[m];
		visited = new boolean[n+1];
		combi(1, 0);
	}
	
	public static void combi(int idx, int cnt) {
		if(cnt == m) {
			for(int x : select) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = idx; i <= n; i++) {
			select[cnt] = i;
			combi(i+1, cnt+1);
		}
	}
}