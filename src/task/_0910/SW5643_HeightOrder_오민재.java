package task._0910;
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
public class SW5643_HeightOrder_오민재 {
	static int T, V, E, ans;
	static boolean[][] reach;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			V = Integer.parseInt(br.readLine());
			E = Integer.parseInt(br.readLine());
			ans = 0;
			reach = new boolean[V][V];
			
			for(int i = 0 ; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				reach[from][to] = true; 
			}
			
			
			for(int k = 0 ; k < V; k++) {
				for(int i = 0 ; i < V; i++) {
					if(!reach[i][k])	continue;
					for(int j = 0 ; j < V; j++) {
						if(reach[k][j])	reach[i][j] = true;
					}
				}
			}
			
			for(int i = 0 ; i < V; i++) {
				int sum = 0;
				for(int j = 0 ; j < V; j++) {
					if(i == j)	continue;
					if(reach[i][j] || reach[j][i])	sum++;
				}
				if(sum == V-1) {
					ans++;
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
}
