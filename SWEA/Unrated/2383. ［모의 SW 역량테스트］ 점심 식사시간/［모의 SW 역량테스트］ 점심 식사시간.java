import java.io.*;
import java.util.*;

/* [문제 분석]
 * - N*N
 * - 최대한 빠른 시간 내에 아래층으로 내려가야함
 * - 사람 P, 계단 입구 S
 * - 이동 완료 시간 : 모든 사람들이 계단을 내려가 아래 층으로 이동 완료
 * - 이동 시간 = 계단 입구까지 이동시간 + 내려가는 시간
 * 		1) 계단 입구까지 이동 시간 : |Py - Sy| + |Px - Sx| = 멘하탄거리
 * 		2) 계단 내려가는 시간
 * 			- 계단 입구 도착 후 1분 후 내려갈 수 있음
 * 			- 계단 위에 동시에 3명까지만 있을 수 있음
 * 			- 이미 3명이 내려가고 있는 경우, 한 명이 완전히 내려갈 때까지 입구에서 대기
 * 			- 계단마다 내려가는데 걸리는 시간 K 주어짐
 * 
 * - 이동 완료 최소 시간 => 이동에 가장 오래거리는 사람의 마지막 도착 시간
 * 
 * 
 * <입력 주의사항>
 * - 사람 : 1, 2~10 : 계단 입구(길이 의미)
 * - 계단 입구는 반드시 2개
 * 
 * [문제 풀이]
 *  - P명의 사람이 각각 2개의 계단을 선택하는 경우의 수 => 2^P
 *    => 부분집합? -> 비트마스킹으로 상태 표현 가능 P명 -> P개 bit

 *  - 그럼 각 부분집합에 대해서 시뮬레이션해서 최댓값 갱신
 *  
 *  [구현]
 *  1. 비트마스킹 2^P 상태 만들기
 *  	1-1) 배열 순회, 사람 좌표 저장 + 계단 좌표 저장 + 사람 몇명인지 세기
 *  	1-2) 비트마스킹으로 2^P 상태 만들기
 *  
 *  2. 시뮬레이션 + 최댓값 갱신
 *  	2-0) 마스킹된 비트열 파싱 => 어떤 계단에 누가 가는지 저장
 *  	2-1) 각 계단에 대해 시뮬레이션 -> 최대값 갱신
 *  	2-2) 시뮬레이션 
 *  		- 각 사람들에서 계단까지의 거리 정보(계단 도착 시간) 저장
 *  		- 오름차순 정렬 -> 빠른 순서대로 진행
 *  		- PQ : 계단을 내려가고 있는 사람들의 탈출 완료 시간
 *  		- 도착시간 k 가 들어왔을 때 => PQ에 K 보다 작은 값들은 다 빼내줘야함(탈출 완료)		
 *  		- 만약 PQ의 size가 3보다 크거나 같으면 => PQ의 가장 빨리 끝나는 애 + 1에 내려가기 시작
 *  		- 	  			 3보다 작으면 => 도착시간 + 1 에 내려가기 시작
 *  		- 끝 = 시작 + 계단 길이, 최댓값 갱신
 *  
 *  3. 거리 함수 만들기		
 * 
 * 
 *  <주의사항>
 *  - 계단에 도착하고 1분 후에 내려가기 시작해야함
 *  - 대기열 때문에 이미 기다리고 있었으면 => 앞에 사람 빠지자 마자 바로 이동 시작
 */
public class Solution {
	
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static int T, n, peopleCnt, ans;
	static int[][] map;
	static ArrayList<Point> people, stairs;
	static ArrayList<Integer> arrive1, arrive2, stairsLen;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			map = new int[n][n];
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			stairsLen = new ArrayList<>();
			
/*  
    1. 비트마스킹 2^P 상태 만들기 
   	1-1) 배열 순회, 사람 좌표 저장 + 계단 좌표 저장 + 사람 수 저장
   	1-2) 비트마스킹으로 2^P 상태 만들기
*/ 
			/* 1-1) 배열 순회, 사람 좌표 저장 + 계단 좌표 저장 + 사람 수 저장 */
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					/* 사람 위치 저장 */
					if(map[i][j] == 1) {
						people.add(new Point(i, j));
					}
					
					/* 계단 위치 저장 */
					if(map[i][j] >= 2) {
						stairs.add(new Point(i, j));
						stairsLen.add(map[i][j]);
					}
				}
			}
			
			peopleCnt = people.size();
			
			/* 1-2) 비트마스킹으로 2^P 상태 만들기 + 비트열 파싱 */
			for(int mask = 0 ; mask < (1 << peopleCnt); mask++) {
				parseMask(mask);
			}

			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	/* 2-0) 마스킹된 비트열 파싱 => 어떤 계단에 누가 가는지 저장 */
	public static void parseMask(int mask) {
		arrive1 = new ArrayList<>(); // 각 계단 도착 시간 저장
		arrive2 = new ArrayList<>();
		
		// 111000 : 1,2,3번 사람 -> 1번 계단, 4, 5, 6번 사람 -> 2번 계단
		for(int i = 0 ; i < peopleCnt; i++) {
			Point p = people.get(i); // i번째 사람
			if((mask & (1<<i)) == 0) { // 1번 계단
				 arrive1.add(dist(p, stairs.get(0)));
				
			}else { // 2번 계단
				arrive2.add(dist(p, stairs.get(1)));
			}
		}
		
		int len1 = stairsLen.get(0);
		int len2 = stairsLen.get(1);
		
		// 2개 계단 각각 시뮬레이션
		int end1 = simulate(arrive1, len1);
		int end2 = simulate(arrive2, len2);
		
		// 2개 계단 모두 종료하는데 걸리는 시간
		int res = Math.max(end1, end2);
		
		// 결과 최소값 갱신
		ans = Math.min(res, ans);
	}
	
	/*
   		2-2) 시뮬레이션 
   		- 각 사람들에서 계단까지의 거리 정보(계단 도착 시간) 저장
   		- 오름차순 정렬 -> 빠른 순서대로 진행
  		- PQ : 계단을 내려가고 있는 사람들의 탈출 완료 시간
   		- 도착시간 k 가 들어왔을 때 => PQ에 K 보다 작은 값들은 다 빼내줘야함(탈출 완료)		
   		- 만약 PQ의 size가 3보다 크거나 같으면 => PQ의 가장 빨리 끝나는 애 + 1에 내려가기 시작
   		- 	  			 3보다 작으면 => 도착시간 + 1 에 내려가기 시작
   		- 끝 = 시작 + 계단 길이, 최댓값 갱신
	 */
	public static int simulate(List<Integer> arrive, int len) {
	    if (arrive.isEmpty()) return 0;
	    Collections.sort(arrive);

	    PriorityQueue<Integer> pq = new PriorityQueue<>();
	    int lastFinish = 0;

	    for (int a : arrive) {
	        int start = a + 1; // 도착 후 1분 대기

	        // 계단이 꽉 차있으면 가장 먼저 끝나는 사람 나갈 때까지 대기
	        if (pq.size() == 3) {
	            int earliest = pq.poll();
	            if (earliest > start) start = earliest;
	        }

	        int finish = start + len;
	        pq.offer(finish);
	        lastFinish = Math.max(lastFinish, finish);
	    }

	    return lastFinish;
	}

	/* 거리 함수 */
	public static int dist(Point p1, Point p2) {
		return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
	}
}
