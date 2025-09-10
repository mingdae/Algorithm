import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - A,B,C (부피 1~200) 물통
 *  - A,B는 비어있고 C는 꽉차 있음
 *  - 한 물통이 비거나, 다른 물통이 가득 찰 때까지 물을 부을 수 있음
 *  - A가 비어있을 때, C에 담겨있을수 있는 물의 양 경우의 수 모두 출력
 *  - 오름차순 출력
 * 
 * [풀이]
 * 
 * 
 */

public class Main {
	static int A,B,C;
	static boolean[] ans;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[A+1][B+1];
		ans = new boolean[C+1];
		
		dfs(0, 0);
		
		for(int i = 0 ; i <= C; i++) {
			if(ans[i])	sb.append(i).append(" ");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int a, int b){
		// 기저 조건
		if(visited[a][b])	return;
		
		visited[a][b] = true;
		int c = C - a - b;
		
		// 정답 후보
		if(a == 0)	ans[c] = true;
		
		// 6가지 붓는 방법
		// A->B, A->C, B->A, B->C, C->A, C->B
		// 1. A -> B
		if(a > 0 && b < B) {
			int move = Math.min(a, B-b);
			dfs(a-move, b+move);
		}
		
		// 2. A -> C
		if(a > 0 && c < C) {
			int move = Math.min(a, C-c);
			dfs(a-move, b);
		}
		
		// 3. B -> A
		if(b > 0 && a < A) {
			int move = Math.min(b, A-a);
			dfs(a+move, b-move);
		}
		
		// 4. B -> C
		if(b > 0 && c < C) {
			int move = Math.min(b, C-c);
			dfs(a, b-move);
		}
		
		// 5. C -> A
		if(c > 0 && a < A) {
			int move = Math.min(c, A-a);
			dfs(a + move, b);
		}
		
		// 6. C -> B
		if(c > 0 && b < B) {
			int move = Math.min(c, B-b);
			dfs(a, b+move);
		}
		
	}
}
	
	