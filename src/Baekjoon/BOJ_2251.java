package Baekjoon;
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

public class BOJ_2251 {
	static int a,b,c;
	static TreeSet<Integer> ts = new TreeSet<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
			
	}
}
	
	