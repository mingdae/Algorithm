package Baekjoon;

import java.io.*;
import java.util.*;


/*
 * [문제 분석]
 * - 땅 고르기 작업
 * - N * M 형태 집터, 시작 점 (0, 0)
 * - 집터 내의 땅의 높이를 일정하게 바꾸기
 * - 시작 시 인벤토리 B 개의 블록
 * - 높이 최대 256, 음수 불가 -> 가능한 높이가 0 ~ 256 : 범위가 굉장히 작음
 *   -> 완탐 가능
 * 
 * - 메인 로직
 * 		1. 좌표(i,j)의 가장 위의 블록 제거, 인벤토리에 추가 -> 2초
 * 		2. 인벤토리에서 블록 하나 꺼내서 좌표(i,j)의 가장 위의 블록 위에 놓기 -> 1초
 * 
 * [목표]
 * - 땅 고르기 작업의 최소 시간, 땅 높이 출력
 * 
 * [구현]
 * - 현재 높이의 최소 ~ 최대값 범위 내에서 모든 가능한 후보를 탐색
 *   최소 시간이 되는 땅의 높이 중 가장 높은 것 출력
 * 
 */
public class BOJ_18111 {
	static int n, m;
	static long items, ansTime, ansHeight;
	static int[][] ground;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		items = Long.parseLong(st.nextToken());
		ground = new int[n][m];
		
		int minH = Integer.MAX_VALUE;
		int maxH = 0;
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
				minH = Math.min(minH, ground[i][j]);
				maxH = Math.max(maxH, ground[i][j]);
			}
		}
		
		ansTime = Long.MAX_VALUE;
		ansHeight = 0;
		// minH ~ maxH 가능한 높이 후보군 모두 테스트 (완탐)
		for(int testH = minH ; testH <= maxH; testH++) { 
			long sec = 0;  // 연산에 필요한 시간
			long item = 0; 
			// 모든 배열 완전 탐색
			for(int j = 0; j < n ; j++) { 
				for(int h : ground[j]) {
					// 더 높으면, h - testH 만큼 제거, 인벤토리 추가
					if(h > testH) { 
						sec += 2 * (h-testH); 
						item += (h- testH);
					}
					// 더 낮으면, testH - h 만큼 블록 쌓기, 인벤토리 제거
					else { 
						sec += (testH -h); 
						item -= (testH - h);
					}
				}
			}
			
			// 실패 조건
			if(item + items < 0)	continue;
			
			// 성공 시 최소값 갱신, 같을 때도 값 갱신
			if(sec <= ansTime) {
				ansTime = sec;
				ansHeight = testH;
				
			}
			
		}
		
		
		sb.append(ansTime + " " + ansHeight);

		System.out.println(sb);
	}
}
