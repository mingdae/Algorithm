package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N개의 방, 모두 한 개의 컴퓨터, 각각 랜선 연결
 *  - A와 B가 서로 랜선으로 연결되어 있거나, 다른 컴퓨터로 연결되어있으면 통신 가능
 *  	- 연결 가능한지 여부
 *  - N개를 모두 연결되게 -> MST?
 *  - 서로 연결되어있는 랜선의 길이, 기부할 수 있는 최대값
 * 		=> N개의 컴퓨터를 모두 연결할 수 있는 최소 길이 = MST
 * 
 * [문제 풀이]
 * - MST
 * - 1. 입력 문자 파싱 (a~z : 1~26, A~Z : 27~52, 0 : 랜선 X)
 * - 2. 인접행렬 -> 간선리스트
 * - 3. Kruskal
 * 		- Edge class 만들기(weight comparable) 
 * 		- 간선리스트 PQ
 * 		- weight가 짧은 순서로 꺼내면서 UnionFind
 * 		- Union 실패 => 사이클 발생, continue
 * 		- UnionFind가 n-1번 이루어지면 => MST 완성
 * 		- 끝까지 돌았는데 n-1번이 안되면 => -1 출력
 * 
 * - 4. Kruskal을 진행하면서 weight 계속 누적, 최소 길이 저장
 * - 5. ans = total - MST 
 * 
 * 
 * 
 * 
 */

public class BOJ_1414 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, w;
		Edge(int from, int to, int w){
			this.from = from;
			this.to = to;
			this.w = w;
		}
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	/*Union Find*/
	private static void make() {
		for(int i = 0 ; i < n; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int x) {
		if(parents[x] == x)	return x;
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb)	return false;
		if(pa > pb)	parents[pb] = pa;
		else parents[pa] = pb;
		
		return true;
	}
	
	static int n, total, mst, ans;
	static PriorityQueue<Edge> pq;
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		total = ans = mst = 0;
		parents = new int[n];
		pq = new PriorityQueue<>();
		
		make();
		for(int i = 0 ; i < n; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < n ; j++) {
				char ch = line.charAt(j);
				
				int inp = 0;
				if(ch >= 'A' && ch <= 'Z')
					inp = ch - 'A' + 27;
				else if(ch >= 'a' && ch <= 'z')
					inp = ch - 'a' + 1;
				else // ch = '0'
					continue;
				
				pq.offer(new Edge(i, j, inp));
				total += inp;
			}
		}
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int from = cur.from;
			int to = cur.to;
			int weight = cur.w;
			
			// UnionFind 실패 : 사이클
			if(!union(from, to))	continue;
			
			// UnionFind 성공 : 카운트
			mst += weight;
			
			// UnionFind n-1번 진행 => MST 완성
			if(++cnt == n-1) {
				break;
			}
		}
		
		// cnt != n-1 : MST 완성 X, 연결 안되어있음
		if(cnt != n-1)	ans = -1;
		else	ans = total - mst;
		
		System.out.println(ans);
	}
}
	
	