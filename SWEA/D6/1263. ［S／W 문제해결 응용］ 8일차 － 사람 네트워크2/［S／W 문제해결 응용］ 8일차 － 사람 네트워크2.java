import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 정점 수 N
 * - 모든 정점에서 다른 모든 정점으로의 최단거리
 * - CC(i) : 정점 i 에서 다른 모든 정점으로의 최단거리의 합
 * - N 최대 1000
 * - 플로이드 워샬
 */
public class Solution {
	static int T, n, ans;
	static int[][] dist;
	static final int INF = 100000001;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			n = Integer.parseInt(st.nextToken());
			dist = new int[n][n];
			ans = INF;
			for(int i = 0; i < n; i++) {
				for(int j = 0 ; j < n ; j++) {
					int input = Integer.parseInt(st.nextToken());
					if(input == 0 && i != j)	dist[i][j] = INF;
					else	dist[i][j] = input;
				}
			}
			
			
			for(int k = 0; k < n ; k++) {
				for(int i = 0 ; i < n ; i++) {
					for(int j = 0 ; j < n ; j++) {
						if(dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}


			for(int i = 0 ; i < n ; i++) {
				int sum = 0;
				for(int j = 0 ; j < n ; j++) {
					sum += dist[i][j];
				}
				ans = Math.min(ans, sum);
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	
}
