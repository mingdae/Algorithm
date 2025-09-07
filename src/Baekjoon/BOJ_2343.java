package Baekjoon;

import java.io.*;
import java.util.*;

/* [문제 분석]
 * - 강의는 순서대로 주어짐 -> 정렬 X
 * - 연속되게 구간 나눌 수 있음
 * - 강의 N개, 블루레이 개수 M개
 * - 블루레이 크기 최소화 : 한 블루레이에 담을 수 있는 강의 길이
 * - 즉, 블루레이 크기로 모든 강의를 M개의 구간으로 나눠서 담을 수 있어야함
 * - 모든 블루레이는 크기 같음
 * 
 * [풀이]
 * - 매개변수 이분탐색
 * - 블루레이 크기에 대해서 이분 탐색
 * 		범위 : max(arr[i]) ~ sum(arr[i])
 * 		- 적어도 하나의 강의는 담을 수 있어야함
 * 		- 모든 강의를 담을 수 있는 것보다 클 수 없음
 * 

 * 	
 * 
 * 
 */



public class BOJ_2343 {
	static int n, m, ans;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		
		int maxVal = 0;
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxVal = Math.max(maxVal, arr[i]);
			sum += arr[i];
		}
		
		int start = maxVal;
		int end = sum;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int test = Check(mid);
			
			if(test <= m) { // 정답 후보, 저장해두고 계속 진행
				ans = mid;
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		
		
		System.out.println(ans);
	}
	
	// 필요한 블루레이 개수
	public static int Check(int size) {
		int need = 1;
		int sum = 0;
		for(int len : arr) {
			if(sum + len > size) {
				need++;
				sum = len;
			}else {
				sum += len;
			}
		}
		
		return need;
		
	}
}
