package task;
import java.io.*;
import java.util.*;
/*
	1) 문제 요약(summary)
		- 사다리 타기, 첫 줄에서 시작해서 맨 아랫줄의 도착지점(2) 까지 이동
    - 도착지점에 다다를 수 있는 시작 지점을 찾는 문제
    - 가로 방향전환, 방향 전환 이후엔 아래로만 이동, 바닥에 도착하면 멈춤
    - 한 막대에서 출발한 가로선이 다른 막대를 가로지르는 경우는 없다
	
	2) 풀이 전략(strategy)
		- 입력 받을때 도착 지점을 저장 
    - 도착 지점에서부터 역으로 사다리 따라가기
      1) 기본적으로 위로 이동
      2) 왼쪽, 오른쪽에 1을 만나면 방향 꺾기
      3) 꺾어서 이동하다가 0을 만나면 다시 위로 이동
    - 종료 조건 : 첫 행까지 다다르면 종료
	
	3) 주의할 점(note)
    무한루프에 빠지지 않도록 디버깅
*/

public class SW1210_Ladder1_오민재 {
	static int ans;
	static final int N = 100;
	static int[][] map;
	static int start;
	static int row;
	static int col;
	

	static void turnRight() { // 오른쪽으로 꺾기
		while(col < N-1 && map[row][col+1] == 1)
			col++;
		row--;
	}
	
	static void turnLeft() { // 왼쪽으로 꺾기
		while(col >= 1 && map[row][col-1] == 1)
			col--;
		row--;
	}
	
	static void move() { // 도착지점에서 거꾸로 추적
		while(row != 0) {
			if(col < N-1 && map[row][col+1] == 1)
				turnRight();
			else if(col >= 1 && map[row][col-1] == 1)
				turnLeft();
			else
				row--;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		

		for(int tc = 1; tc <= 10; tc++) {
			int t = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2)
						start = j; // 도착지점
				}
			}
			
			row = N-2;
			col = start;
			move();
			ans = col;
			
			sb.append("#" + tc + " " + ans).append('\n');
		}
		System.out.println(sb);
	}

}
