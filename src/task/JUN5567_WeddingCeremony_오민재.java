package task;
import java.io.*;
import java.util.*;

public class JUN5567_WeddingCeremony_오민재 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Integer>[] friends = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) friends[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            friends[from].add(to);
            friends[to].add(from);
        }

        boolean[] invited = new boolean[n + 1];

        for (int friend : friends[1]) {
            invited[friend] = true;
        }

        for (int friend : friends[1]) {
            for (int next : friends[friend]) {
                invited[next] = true;
            }
        }
        invited[1] = false; 

        int cnt = 0;
        for (int i = 2; i <= n; i++) if (invited[i]) cnt++;
        System.out.println(cnt);
    }
}