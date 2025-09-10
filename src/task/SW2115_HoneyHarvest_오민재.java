package task;

import java.io.*;
import java.util.*;

/*
 * [문제 분석]
 * - N*N 벌통
 * - 최대한 많은 수익
 * 		- 2명의 일꾼, 채취 벌통 수 M
 * 		- 가로로 연속되도록 M 개 선택, 겹치기 X
 * 		- 일부분만 채취 X, 모든 꿀 한번에 채취
 * 		- 두 일꾼의 채취 최대 양 C -> 각각 M개의 합에서 C를 넘어가면 안됨
 * 		- 수익 : 꿀의 양 제곱합
 * 
 * - N(3~10), M(1~5) 
 * [문제 풀이]
 * - 1. N*N에서 1*M의 가로 후보 만들기 
 * 		- S = N(N-M+1) 개 <= 100개
 * 
 * - 2. S개 중에서 2개를 선택 
 * 		- C(S,2) -> O(S^2)  <= 10000개
 * 		- 선택할때 겹치지 않게
 * 			- 같은 줄이면 c2 >= c1 + M 이어야함
 * 			- 다른 줄이면 다 가능
 * 
 * - 3. 선택된 후보들의 M칸에 처리
 * 		- 합이 C를 넘기지 않을때의 최대값 계산 
 * 		- M칸의 부분집합 처리 -> 2^M, 합이 C를 넘는 부분집합 소거
 */


public class SW2115_HoneyHarvest_오민재 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, C;
    static int[][] A;            // 벌통
    static int[][] best;         // best[r][c] : (r,c)부터 가로 M칸의 최대 수익

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            precomputeBest();
            int ans = pickTwoSegments();
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) A[r][c] = Integer.parseInt(st.nextToken());
        }
        best = new int[N][Math.max(0, N - M + 1)];
    }

    /** (r,c)부터 가로로 M칸의 부분집합 중, 합<=C 일 때 ∑(a^2) 최대값 계산 */
    static int calcBestForSegment(int r, int c) {
        int[] arr = new int[M];
        for (int i = 0; i < M; i++) arr[i] = A[r][c + i];

        // M <= 5 이므로 비트마스크 완전탐색
        int max = 0;
        int totalMask = 1 << M;
        for (int mask = 1; mask < totalMask; mask++) {
            int sum = 0, sq = 0;
            for (int i = 0; i < M; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += arr[i];
                    if (sum > C) { // 용량 초과 시 즉시 중단
                        sq = -1; // invalid
                        break;
                    }
                    sq += arr[i] * arr[i];
                }
            }
            if (sq > 0) max = Math.max(max, sq);
        }
        return max;
    }

    /** 모든 시작 위치 (r,c)에 대해 best[r][c] 전처리 */
    static void precomputeBest() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c <= N - M; c++) {
                best[r][c] = calcBestForSegment(r, c);
            }
        }
    }

    /** 두 구간을 선택해 합 최대 */
    static int pickTwoSegments() {
        int ans = 0;
        int width = N - M + 1;
        for (int r1 = 0; r1 < N; r1++) {
            for (int c1 = 0; c1 < width; c1++) {
                int v1 = best[r1][c1];
                if (v1 == 0 && C > 0) {
                    // 선택 가치가 없을 수 있으나, 다른 구간과 합쳐도 0이면 스킵 가능
                    // (필수는 아님, 미세 최적화)
                }
                // 같은 행: 겹치지 않게 c2 >= c1 + M
                for (int c2 = c1 + M; c2 < width; c2++) {
                    ans = Math.max(ans, v1 + best[r1][c2]);
                }
                // 다른 행: 아무 구간이나
                for (int r2 = r1 + 1; r2 < N; r2++) {
                    for (int c2 = 0; c2 < width; c2++) {
                        ans = Math.max(ans, v1 + best[r2][c2]);
                    }
                }
            }
        }
        return ans;
    }
}
