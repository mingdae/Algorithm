package task._0909;
import java.io.*;
import java.util.*;

public class SW3307_LIS_오민재 {
	static int T, n, ans;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			ans = 0;
					
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < n ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = LIS(arr);
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int LIS(int[] a) {
		int len = 0;
		int[] lis = new int[n];
		
		for(int x : a) {
			int pos = Arrays.binarySearch(lis, 0, len, x);
			if(pos < 0)	pos = -(pos + 1);
			lis[pos] = x;
			if(pos == len)
				len++;
		}
		return len;
	}

}
