package task;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N*N 도시, 각 칸은 빈칸(0) / 치킨집(2) / 집(1) 중 하나
 *  - (r,c) 좌표, 1부터 시작
 *  - "치킨 거리" : 집에서 가장 가까운 치킨집 사이의 거리
 *  	- 각 집은 각자의 치킨거리를 가지고 있음
 *  	- 도시의 치킨거리 : 모든 집의 치킨거리의 합
 * 	- 거리 : |r1-r2| + |c1-c2|
 *  - 일부 치킨집 폐업
 *  - 도시에서 최대 M개를 고르고, 나머지 모두 폐업
 *  - 어떻게 골랐을때 도시의 치킨거리 가장 작은지
 *  
 *  
 *  [문제 풀이]
 *  - 최대 M개이지만, 거리가 최소가 되려면 M개를 뽑는게 이득
 *  - 치킨집 리스트 중 M개를 뽑는 조합 생성
 *  	- 각 조합에 대해 도시의 치킨거리 계산, 최소값 갱신
 * 
 */

public class JUN15686_ChickenDelivery_오민재 {
	static int n, deleteCnt, ans;
	static int[][] map;
	static List<int[]> chicken, home, selected;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		deleteCnt = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		
		map = new int[n][n];
		selected = new ArrayList<>();
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}else if(map[i][j] == 1) {
					home.add(new int[] {i, j});
				}
			}
		}
		
		
		combi(0, 0);
		
			
		System.out.println(ans);
	}
	
	public static void combi(int idx, int cnt) {
		if(cnt == deleteCnt) {
			ans = Math.min(ans, Calc());
			return;
		}
		
		for(int i = idx; i < chicken.size(); i++) {
			selected.add(chicken.get(i));
			combi(i+1, cnt+1);
			selected.remove(chicken.get(i)); // 원복
		}
		
	}
	
	public static int Calc() {
		int sum = 0;
		for(int[] house : home) {
			int dist = Integer.MAX_VALUE;
			for(int[] shop : selected) {
				dist = Math.min(dist, distance(house, shop));
			}
			sum += dist;
		}

		return sum;
	}
	
	public static int distance(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}
	
	