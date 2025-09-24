import java.io.*;
import java.util.*;

public class Main {

	static int T, n, m, k;
	static int[] arr;
	static double ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
			
		m = Integer.parseInt(br.readLine());
		arr = new int[m];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] > max)	max = arr[i];
			n += arr[i];
		}
		k = Integer.parseInt(br.readLine());
		if(k == 1)	System.out.println(1.0);
		else if(max < k)	System.out.println(0.0);
		else if(m == 1)	System.out.println(1.0);
		else {
			for(int i = 0 ; i < m; i++) {
				if(arr[i] < k)	continue;
				
				double p = 1.0;
				
				for(int t= 0; t < k; t++) {
					p *= (double)(arr[i]-t) / (double)(n-t);
				}
				ans += p;
			}
			System.out.println(ans);
		}
	}
}

	
	