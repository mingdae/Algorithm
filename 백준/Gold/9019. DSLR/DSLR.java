
import java.io.*;
import java.util.*;

public class Main {
	static int T, start, target;
	static boolean[] visited;
	static String ans;
	
	static class Node{
		int value;
		String cmd;
		Node(int value, String cmd){
			this.value = value;
			this.cmd = cmd;
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			visited = new boolean[10001];
		
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			
			bfs(start);
		}
		System.out.println(sb);
		
		
	}
	public static void bfs(int start) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(start, ""));
		visited[start] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i = 0 ; i < 4; i++) {
				Node next = getNext(cur, i);
				
				if(next != null) {
					if(next.value == target) {
						sb.append(next.cmd).append('\n');
						return;
					}
					visited[next.value] = true;
					q.offer(next);
				}
			}
		}
		
		
	}
	
	public static Node getNext(Node node, int type) {
		int value = node .value;
		String str = node.cmd;
		if(type == 0) {
			/* D */
			value = (2 * value ) % 10000;
			str += "D";
		}else if(type == 1) {
			/* S */
			value = (value == 0) ? 9999 : value-1;
			str += "S";
		}else if(type == 2) {
			/* L */
			value = (value % 1000) * 10 + (value / 1000);
			str += "L";
		}else {
			/* R */
			value = (value % 10) * 1000 + (value / 10);
			str += "R";
		}
		if(!visited[value])	return new Node(value, str);
		else	return null;
	}
}