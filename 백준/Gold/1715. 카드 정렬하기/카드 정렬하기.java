import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 2묶음의 숫자 카드, 카드 수 A, B
 *  - 두 묶음을 합쳐서 하나로 -> A+B 번 비교
 *  - N개의 묶음, 최소 비교 횟수 => 작은거부터 비교하면 되는거 아닌가?
 * 
 */

public class Main {
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
		
		if(n == 1) {
			System.out.println(0);
			return;
		}
		
		while(!pq.isEmpty()) {
			int a = pq.poll();
			int b = pq.poll();
			int sum = a + b;
			ans += sum;
			if(pq.isEmpty())	break;
			pq.offer(sum);
		}
		System.out.println(ans);
	}
}
