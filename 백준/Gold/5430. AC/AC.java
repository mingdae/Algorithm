
import java.io.*;
import java.util.*;


public class Main {
	static int T, n;
	static char[] cmd;
	static ArrayList<Integer> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			String input = br.readLine();
			int len = input.length();
			cmd = new char[len];
			
			for(int i = 0 ; i < len; i++) {
				cmd[i] = input.charAt(i);
			}
			
			n = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			input = br.readLine();
			boolean isError = false;
			
			/* 예외 처리 */
			if(n == 0) {
				for(int i = 0; i < len ; i++) {
					if(cmd[i] == 'D') {
						sb.append("error").append('\n');
						isError = true;
						break;
					}
				}
				
				if(isError)	continue;
				sb.append('[').append(']').append('\n');
				continue;
			}
			
			
			input = input.substring(1, input.length()-1);
			int idx = 0;
			st = new StringTokenizer(input, ",");
			for(int i = 0 ; i < n ; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			int dir = 1;
			int left = 0;
			int right = arr.size()-1;
			for(char cur : cmd) {
				if(cur == 'R') {
					dir *= -1;
//					reverse(arr);
				}else { // 'D'
					if(left > right) {
						isError = true;
						sb.append("error").append('\n');
						break;
					}
					if(dir == 1)	left++;
					else	right--;
				}
			}
			
			if(isError)	continue;
			
			sb.append("[");
			if(left > right) {
				sb.append("]").append('\n');
				continue;
			}
			
			if(dir == -1) {
				for(int i = right; i > left; i--) {
					sb.append(arr.get(i)).append(",");
				}
				sb.append(arr.get(left));
			}else {
				for(int i = left; i < right; i++) {
					sb.append(arr.get(i)).append(",");
				}
				sb.append(arr.get(right));
			}
			
			sb.append("]").append('\n');
		}
		System.out.println(sb);
	}
	
//	
//	public static void reverse(int[] a) {
//		int len = a.length;
//		for(int i = 0 ; i < len / 2; i++) {
//			swap(a, i, len-i-1);
//		}
//		return;
//	}
//	
//	
//	public static void swap(int[]a, int x, int y) {
//		int tmp = a[x];
//		a[x] = a[y];
//		a[y] = tmp;
//		return;
//	}
}