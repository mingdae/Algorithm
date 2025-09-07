package Baekjoon;

import java.io.*;
import java.util.*;

/*[문제 분석]
 * - N개의 좌표 X(1) ... X(N)
 * - 좌표 압축
 * - X(new i) = X(i) > X(j)를 만족하는 서로 다른 좌표 X(j)의 갯수
 * - 좌표 압축 결과 출력
 * 
 * [제약 조건]
 * - 시간 제한 2초
 * - N 1 ~ 10^7 -> NlogN
 * - Xi 값 굉장히 큼
 * 
 * 
 */


public class BOJ_18870 {
	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		TreeSet<Integer> ts = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			ts.add(arr[i]); // 중복 제거, 자동 정렬
		}
		
		
		Map<Integer, Integer> m = new HashMap<>();
		int idx = 0;
		for(int num : ts) {
			m.put(num, idx++);
		}
		
		for(int i = 0; i < n ; i++) {
			sb.append(m.get(arr[i])).append(" ");
		}
		System.out.println(sb);
	
	}
}
