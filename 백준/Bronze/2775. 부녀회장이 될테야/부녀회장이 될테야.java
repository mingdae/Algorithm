import java.io.*;
import java.util.*;

public class Main {

	static int T, k, n;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			k = Integer.parseInt(br.readLine());
			n = Integer.parseInt(br.readLine());
			arr = new int[k+1][n+1];
			for(int c = 0; c <= n ; c++) {
				arr[0][c] = c;
			}
			for(int r = 1 ; r <= k ; r++) {
				for(int c = 1 ; c <= n; c++) {
					int sum = 0;
					for(int p = 1; p <= c; p++) {
						sum += arr[r-1][p];
					}
					arr[r][c] = sum;
				}
			}
			sb.append(arr[k][n]).append('\n');
		}
		System.out.println(sb);
	}
}
	
	