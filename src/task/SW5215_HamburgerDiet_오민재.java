package task;

import java.io.*;
import java.util.*;

public class SW5215_HamburgerDiet_오민재 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] score = new int[N];
            int[] cal = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            int maxScore = 0;
            int totalSubset = 1 << N;

            for (int mask = 0; mask < totalSubset; mask++) {
                int sumScore = 0;
                int sumCal = 0;

                for (int i = 0; i < N; i++) {
                    if ((mask & (1 << i)) != 0) { 
                        sumScore += score[i];
                        sumCal += cal[i];
                    }
                }

                if (sumCal <= L) {
                    maxScore = Math.max(maxScore, sumScore);
                }
            }

            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }

        System.out.print(sb);
    }
}
