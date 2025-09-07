package task;

import java.io.*;
import java.util.*;

public class SW3421_BurgerMaster_오민재 {
    static int N, M;
    static List<int[]> banPairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            banPairs = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                banPairs.add(new int[]{a, b});
            }

            int count = 0;
            int total = 1 << N;

            for (int mask = 0; mask < total; mask++) {
                if (isValid(mask)) count++;
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    static boolean isValid(int mask) {
        for (int[] pair : banPairs) {
            int a = pair[0], b = pair[1];
            if (((mask >> a) & 1) == 1 && ((mask >> b) & 1) == 1) {
                return false;
            }
        }
        return true;
    }
}
