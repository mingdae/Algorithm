package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 2묶음의 숫자 카드, 카드 수 A, B
 *  - 두 묶음을 합쳐서 하나로 -> A+B 번 비교
 *  - N개의 묶음, 최소 비교 횟수 => 작은거부터 비교하면 되는거 아닌가?
 *  	=> 작은거부터 더한 값이 뒤의 값보다 더 작은 경우가 있음!
 *  	=> ex) 1,1,1,1,3,5,6 이면 앞부터 차례대로 하면 안됨. 1+1도 다시 고려
 *  
 *  [문제 풀이]
 *  - 정렬 후 작은거부터 2개씩 합치는데, 합친게 그 뒤의 원소보다 더 작을 수도 있음!
 *  - 최소값 2개씩만 꺼내고, 다시 넣을 거기떄문에 PQ
 *  - pq.poll 2번씩 해서 합치고, 다시 pq.offer
 */

public class BOJ__1715 {
	static int n, ans;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		pq = new PriorityQueue<>();
		for(int i = 0 ; i < n ; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		// 예외 처리
		if(n == 1) {
			System.out.println(0);
			return;
		}
		
		while(!pq.isEmpty()) {
			int a = pq.poll();
			int b = pq.poll();
			int sum = a + b;
			ans += sum;
			if(pq.isEmpty())	break; // 합병 완료
			pq.offer(sum);
		}
		System.out.println(ans);
	}
}
	