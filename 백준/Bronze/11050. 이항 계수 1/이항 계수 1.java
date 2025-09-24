import java.io.*;
import java.util.*;

public class Main {

	static int n, k, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ans = 1;
		for(int x = 0; x < k; x++) {
			ans *= (n-x);
		}
		
		for(int x = 1; x <= k; x++) {
			ans /= x;
		}
		System.out.println(ans);
	}
}
	
	