import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 1~N번 번호
 * - 두 학생 키 비교 결과의 일부 제공 => 위상정렬
 * - a -> b : a < b 를 의미
 * - 키가 몇 번째인지 알 수 있는 학생이 모두 몇명인지
 * 
 * [문제 풀이]
 * - 키가 몇 번째인지 알 수 있는 사람
 * 	-> 나보다 작은 사람 i 명, 큰 사람 j 명 일때
 * 	-> i + j == n-1 이면 키가 몇 번째 인지 알 수 있음
 *  -> 나보다 작은 사람 : 역방향 그래프에서 도달 가능한 사람
 *  -> 나보다 큰 사람 : 정방향 그래프에서 도달 가능한 사람
 *  
 * 
 */
public class Main {
	static int T, V, E, ans;
	static int[][] dist;
	static int[][] revdist;
	static final int INF = 10000001;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
	
		
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			ans = 0;
			dist = new int[V][V];
			revdist = new int[V][V];
			
			
			for(int i = 0 ; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				dist[from][to] = 1;
				revdist[to][from] = 1;
			}
			
			for(int i = 0 ; i < V; i++) {
				for(int j = 0 ; j < V; j++) {
					if(i != j && dist[i][j] == 0)
						dist[i][j] = INF;
					if(i != j && revdist[i][j] == 0)
						revdist[i][j] = INF;
				}
			}
			
			for(int k = 0 ; k < V; k++) {
				for(int i = 0 ; i < V; i++) {
					for(int j = 0 ; j < V; j++) {
						if(dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
						if(revdist[i][j] > revdist[i][k] + revdist[k][j]) {
							revdist[i][j] = revdist[i][k] + revdist[k][j];
						}
					}
				}
			}
			
			
			for(int i = 0 ; i < V; i++) {
				int sum = 0;
				for(int j = 0 ; j < V; j++) {
					if(dist[i][j] != INF && dist[i][j] != 0)
						sum++;
					if(revdist[i][j] != INF && revdist[i][j] != 0)
						sum++;
				}
				if(sum == V-1) {
					ans++;
				}
					
			}

			
		System.out.println(ans);
		
	}
	
}
