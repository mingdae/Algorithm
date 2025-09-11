import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 길이 N 수열, 수열의 합
 *  - 모두 더하는게 아니라 두 수를 묶어서 진행
 *  - 묶을 때 위치 상관 X, 자기 자신은 못 묶음
 *  - 묶은 수들은 서로 곱한 후에 더함
 *  - 합을 최대로
 *  
 *  <주의사항>
 *  - 모든 수는 한 번만 묶거나, 아예 묶지 않아야함
 *  - 수열의 수가 음수도 있음(-1000~1000)
 *  - 정답은 항상 int 범위 안
 *  
 *  [문제 풀이]
 *  - 음수처리
 *  	1) 음수가 없을 때 => 원래대로 처리
 *  	2) 음수가 1개 => 묶지 않음
 *  	3) 음수가 2개 이상 => 음수끼리 묶음, 음수도 큰 수끼리 묶어야함
 *  	4) 0도 음수로 처리 -> 수를 작게하는 요소
 *  
 *  - 양수
 *  	1) 큰 수끼리 묶음 => 곱을 최대로
 * 
 *  - 한 번 묶인 수는 더이상 쓰면 안됨 -> 바로 ans에 저장
 *  - 큰 수 부터 묶음 -> 내림차순 정렬
 */

public class Main {
	static int n, ans;
	static PriorityQueue<Integer> maxHeap;
	static PriorityQueue<Integer> minHeap;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬
		minHeap = new PriorityQueue<>(); // 음수 오름차순 정렬
		
		for(int i = 0 ; i < n ; i++) {
			int inp = Integer.parseInt(br.readLine());
			if(inp > 0)	maxHeap.offer(inp);
			else minHeap.offer(inp);
		}

		/* 양수 처리 */
		while(!maxHeap.isEmpty()) {
			// 원소가 1개밖에 없을 때 => 바로 더해줌
			if(maxHeap.size() == 1) {
				ans += maxHeap.poll();
				break;
			}
			
			int big = maxHeap.poll();
			int small = maxHeap.poll();
			
			// 1인 경우를 고려해서 더 큰수로 합함
			if(small == 1)	ans += big + small;
			else	ans += big * small;
		}
		
		while(!minHeap.isEmpty()) {
			if(minHeap.size() == 1) {
				ans += minHeap.poll();
				break;
			}
			
			int small = minHeap.poll();
			int big = minHeap.poll();
			ans += small * big; // 음수는 무조건 곱하는게 더 큼
		}
			
		System.out.println(ans);
	}
}
	
	