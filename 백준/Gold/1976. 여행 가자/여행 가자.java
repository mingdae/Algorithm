import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N개 도시, 임의의 도시 사이에 길이 있을수도, 없을수도
 *  - 여행 경로가 가능한 것인지(연결되어있는지)
 *  - 경로 중복 가능, 같은 도시 여러번 반복 가능
 *  
 * [문제 풀이]
 * - UnionFind
 * - 연결되어있다 => 같은 집합에 있다
 * - 간선 정보 입력 => Union
 * - 여행 경로 => 모든 경로의 원소들이 같은 집합에 있는지 확인
 * 
 * 
 * 
 */

public class Main {
	static int n, m;
	static int[] parents;
	
	
	public static void make() {
		for(int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int x) {
		if(parents[x] == x)	return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb)	return false;
		if(pa > pb)	parents[pb] = pa;
		else	parents[pa] = pb;
		
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		parents = new int[n];
		
		make();
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(j > i)	continue;
				if(input == 1) {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int[] route = new int[m];
		for(int i = 0 ; i < m ; i++) {
			route[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		boolean yes = true;
		for(int i = 0; i < m-1; i++) {
			if(union(route[i], route[i+1])) {	
				yes = false;
				break;
			}
				
		}
		if(yes)	System.out.println("YES");
		else	System.out.println("NO");
			
			
			
	}
}
	
	