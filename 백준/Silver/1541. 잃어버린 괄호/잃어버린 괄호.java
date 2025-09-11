import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - 괄호를 쳐서 식을 최소화 => - 이후의 부분을 최대화
 *  	=> n - (식) 의 형태가 최소 값 
 *  - +면 괄호로 묶어서 "빼는 값" 을 크게
 *  - -면 새로운 괄호로 묶어서 진행
 */

public class Main {
	static int ans;
	static String plus;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		ans = 0;
		st = new StringTokenizer(br.readLine(), "-");
		
		// 첫 '-'가 나오기 전까지의 문자열 -> 
		// '+'로 파싱해서 숫자 다 더해줘야함
		plus = st.nextToken();
		ans += parse(plus);
		
		while(st.hasMoreTokens()) {
			String minus = st.nextToken();
			ans -= parse(minus);
		}
		System.out.println(ans);

	}
	public static int parse(String str) {
		int sum = 0;
		StringTokenizer nST = new StringTokenizer(str, "+");
		while(nST.hasMoreTokens()) {
			sum += Integer.parseInt(nST.nextToken());
		}
		return sum;
	}
}
	
	