package lecture;

import java.io.*;
import java.util.*;

public class PermutationTest {
	static int N = 3, R = 1;
	static int[] input;
	static int[] numbers; // 맨 앞쪽부터 차례대로 뽑힌 수들
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		numbers = new int[R];
		
		for(int i = 0 ; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		permutation(0, 0);
	}
	
	/*
	 * 해당 자리에 들어갈 수 순열로 뽑기
	 * @param cnt : 이전까지 뽑은 수들의 개수
	 * @param flag : 각 원소에 대한 선택(1) / 비선택(0) 비트 표현
	 * 
	 */ 
	public static void permutation(int cnt, int flag) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		// 가능한 모든 수를 해당 자리에 시도
		for(int i = 0 ; i < N; i++) {
			// 이미 선택된 수 패스
			if((flag & 1<<i) != 0) continue;
			numbers[cnt] = input[i]; // 해당 자리에 수 저장
			permutation(cnt+1, flag | 1 << i);
		}
	}
}
