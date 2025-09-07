package task;

import java.io.*;
import java.util.*;

public class SW6782_SquareRootGame_오민재 {
	static int T;
	static long n, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			n = Long.parseLong(br.readLine());
			ans = 0;
			while(n != 2) {
				long sqrt = (long)Math.sqrt(n);
				if(sqrt * sqrt == n) {
					n = sqrt;
					ans++;
				}else {
					long nextSqrt = (sqrt+1) * (sqrt+1);
					ans += nextSqrt - n;
					n = nextSqrt;
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
