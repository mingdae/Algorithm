import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 같은 종류가 둘 이상 있을 수 있음
 *  - 임의의 한 위치부터 k개의 접시 연속해서 먹을 시 할인된 정액 가격 제공
 *  - 쿠폰 발행, 무료 제공 -> 없으면 새로 만들어서 제공
 *  
 *  <목표>
 *  - 초밥 가짓수 최대
 *  
 *  [문제 풀이]
 *  - 크기가 k인 슬라이딩 윈도우 유지
 *  - 왼쪽 제거, 오른쪽 삽입
 *  - 카운팅 배열 활용
 * 
 */

public class Main {
	static int n, ans;
	static int[] arr;
	static int[] cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		int typeCnt = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		cnt = new int[typeCnt+1];
		ans = 0;
		for(int i = 0 ; i < n ; i++) {
			arr[i] =  Integer.parseInt(br.readLine());
		}
		int kinds = 0;
		// 초기 윈도우
		for(int i = 0 ; i < k; i++) {
			if(cnt[arr[i]]++ == 0) 
				kinds++;
		}
		
		ans = kinds + (cnt[coupon] == 0 ? 1 : 0);
		
		for(int start = 0; start < n; start++) {
			int out = arr[start];
			if(--cnt[out] == 0)	kinds--;
			
			int in = arr[(start + k) % n];
			if(++cnt[in] == 1)	kinds++;
			
			int now = kinds + (cnt[coupon] == 0 ? 1 : 0);
			if(now > ans)
				ans = now;
		}
			
		System.out.println(ans);
			
			
			
	}
}
	
	