import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 분할 정복
 *  - 좌상 -> 우상 -> 좌하 -> 우하 순서로
 *  - 
 * 
 */

public class Main {
	static int n, y, x, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		
		n = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken()); 
		x = Integer.parseInt(st.nextToken());
		ans = 0;
		int size = (int)Math.pow(2, n);
		
		
		dfs(size, y, x);
	
		System.out.println(ans);
	}
	
	public static void dfs(int size, int r, int c) {
		
		if(size == 2) {
			int offset = 2 * r + c;
			ans += offset;
			return;
		}
		
		// 구역 구하고 ans(시작위치) 더해주기
		int nsize = size / 2;
		int pow = nsize * nsize;
		int nr = r / nsize;
		int nc = c / nsize;
		int offset = 2 * nr + nc;
		ans += pow * offset;
		
		dfs(nsize, r % nsize, c % nsize);
	}
}
	
	