import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 진실을 아는 사람이 파티에 온다 -> 진실을 말한다
 *  - 사람 수 N, 파티 수 M
 *  - 거짓말을 할 수 있는 파티 개수의 최대값
 *  
 *  [문제 풀이]
 *  - 먼저 진실을 아는 사람을 하나의 집합으로 묶음 -> truth 변수에 루트 노드 저장
 *  - M 번의 파티의 인원을 각각 하나의 집합으로 묶음
 *  - 다시 M번의 파티를 순회하면서 부모가 truth와 같은지 확인
 * 
 */

public class Main {
	static int n, m, truthCnt, truth, ans;
	static int[] parents, roots;
	
	public static void make() {
		for(int i = 0; i <= n; i++)
			parents[i] = i;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = 0;
		parents = new int[n+1];
		roots = new int[m];
		make();
		st = new StringTokenizer(br.readLine());
		truthCnt = Integer.parseInt(st.nextToken());
		truth = 0;
		for(int i = 0 ; i < truthCnt; i++) {
			int input = Integer.parseInt(st.nextToken());
			union(input, truth);
		}
		truth = find(truth); // 진실을 아는 집합의 루트 노드
		
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int root = Integer.parseInt(st.nextToken());
			
			if(cnt == 1) {
				roots[i] = root;
				continue;
			}
			
			for(int j = 1 ; j < cnt; j++) {
				int input = Integer.parseInt(st.nextToken());
				union(input, root);
			}
			roots[i] = find(root);
		}
		
		for(int i = 0 ; i < m ; i++) {
			if(find(roots[i]) != find(truth))
				ans++;
		}
		
		if(m == 0)	System.out.println(m);
		else	System.out.println(ans);

	}
}
	
	