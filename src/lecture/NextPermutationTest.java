package lecture;

import java.io.*;
import java.util.*;

// N개의 수를 입력받아 순서적으로 모두 나열하는 순열 생성
public class NextPermutationTest {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner((System.in));
		int N = sc.nextInt();
		int[] numbers = new int[N];
		for(int i = 0 ;i < N; i++) {
			numbers[i] = sc.nextInt();
		}
		
		//step0. 전처리 : 가장 작은 순열 생성(오름차순 정렬)
		Arrays.sort(numbers);
		do {
			System.out.println(Arrays.toString(numbers));
		}while(np(numbers));
	}
	
	// 현 배열의 순열 상태에서 사전 순으로 다음 순열 생성
	// 다음 순열이 존재하면 true, 아니면 false 반환
	private static boolean np(int[] numbers) {
		int N = numbers.length;
		
		// step1. 꼭대기(i) 찾아 바로 앞 교환위치(i-1) 찾기
		int i = N-1;
		while(i>0 && numbers[i-1] >= numbers[i]) --i;
		if(i == 0)	return false; // 다음 순열 없음(가장 큰 순열)
		
		// step2. 교환위치에 값을 크게 만들 뒤쪽에서 자신보다 큰 값중 가장 작은 값 찾기
		int j = N-1;
		while(numbers[i-1] >= numbers[j]) --j;
		
		
		// step3. i-1 위치값과  j 위치값 교환
		swap(numbers, i-1, j);
		
		// step4. 꼭대기부터 뒤의 모든 순열을 가장 작은 형태로 만듦(오름차순 형태)
		int k = N-1;
		while(i < k) {
			swap(numbers, i++, k--);
		}
		
		return true;
		
		
		
		
		
	}
	
	
	private static void swap(int[] numbers, int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}
