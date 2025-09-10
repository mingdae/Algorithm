package Baekjoon;
import java.io.*;
import java.util.*;

/* [문제 분석]
 *  - N개의 방, 모두 한 개의 컴퓨터, 각각 랜선 연결
 *  - A와 B가 서로 랜선으로 연결되어 있거나, 다른 컴퓨터로 연결되어있으면 통신 가능
 *  	- 연결 가능한지 여부
 *  - N개를 모두 연결되게 -> MST?
 *  - 서로 연결되어있는 랜선의 길이, 기부할 수 있는 최대값
 * 		=> N개의 컴퓨터를 모두 연결할 수 있는 최소 길이 = MST
 *  - 
 */

public class BOJ_1414 {
	static int n, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		for(int i = 0 ; i < n; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < n ; j++) {
			}
		}
			
			
		System.out.println(ans);
	}
}
	
	