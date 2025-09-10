package lecture;

import java.util.*;
import java.io.*;

public class FloydTest {
	static int N, dist[][];
	static final int INF = 999;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점 수
		dist = new int[N][N]; // 두 정점간 최단 거리 저장 배열
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				dist[i][j] = sc.nextInt();
				if(i != j && dist[i][j] == 0) {
					dist[i][j] = INF;
				}
			}
		}
		System.out.println("초기 dist 배열------------");
		print();
		
		for(int k = 0 ; k < N; k++) { // k : 경유지
			for(int i = 0 ; i < N; i++) { // i : 출발지
				for(int j = 0 ; j < N; j++) { // j : 도착지
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.print("경유지 : " + k + "(" + i + "->" + j + ")");
						System.out.println(dist[i][j] + "=>" + (dist[i][k] + dist[k][j]));
						
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
			print();
		}
	}
	
	private static void print() {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				System.out.printf("%5d\t", dist[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------------------------");
	}
}
