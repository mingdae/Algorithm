import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N개의 회의, 회의실 사용표
 *  - 각 회의 i : 시작시간, 끝 시간
 *  - 겹치지 않게 하면서 사용할 수 있는 회의의 최대 개수
 *  - 회의 중단 불가, 끝과 동시에 시작 가능
 *  - 시작과 끝 시간이 같을 수 있음
 * 
 * [문제 풀이]
 * - 회의가 끝나는 시간 기준으로 정렬
 * - 끝나는 시간이 같으면 시작하는 시간 기준으로 정렬
 * - 하나 선택 -> 그 뒤에 가장 빨리 시작하는 회의 선택
 * 
 */

public class Main {
	
	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		Meeting(int start, int end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			if(this.end == o.end)	return Integer.compare(this.start, o.start);
			return Integer.compare(this.end, o.end);
		}
	}
	
	static int n, ans;
	static Meeting[] meets;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		meets = new Meeting[n];
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meets[i] = new Meeting(start, end);
		}
		Arrays.sort(meets);
		int now = meets[0].end; // 첫 번째 미팅 선택
		ans = 1;
		
		for(int i = 1; i < n; i++) {
			if(meets[i].start < now) // 현재보다 시작지점이 빠르면 pass
				continue;
			
			// 현재 이후 가장 가까운 시작 지점
			now = meets[i].end;
			ans++;
		}
		System.out.println(ans);
	}
}
	
	