package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_30804 {
	static int n, ans, count;
	static int[] cnt;
	static Deque<Integer> fruits;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		fruits = new ArrayDeque<>();
		cnt = new int[10]; // 1 ~ 9번 과일
		count = 9; // 과일 종류 수
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			int num = Integer.parseInt(st.nextToken());
			fruits.add(num);
			if(cnt[num] == 0)	count--;
			cnt[num]++;
		}
		
		
		Calc();
		ans = fruits.size();
		sb.append(ans);
		System.out.println(sb);
	}
	
	public static void Calc() {
		while(count > 2) {
			
		}
		
		
	}
}
