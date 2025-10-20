import java.io.*;
import java.util.*;

/* [문제]
 * - 1~N 번 도시, 도시 사이에 길이 있을수도, 없을 수도 있음
 * - 출발점 => N개의 도시 모두 거쳐서 다시 원래의 도시로
 * - 재방문 X (출발점 예외)
 * - 가장 적은 비용
 * - W[i][j] 비용
 *      - i 에서 j로 가는 비용, 비대칭, 양의 정수
 *      - W[i][i] = 0
 *      - W[i][j] = 0 이면 길이 없는 경우
 * 
 *  [풀이]
 *  - 모든 출발지점 1~N 에 대하여 외판원 순회 최소 비용 확인
 *  - dp[cur][mask] 
 *      : 현재 cur지점, 방문 mask 상태에서 모든 도시 방문 후 0으로 복귀 최소비용
 *  - 방문 확인 : mask & (1 << mask) ! = 0
 *  - 다음 경로 : mask | (1 << mask) 
 *  - 길 확인 : cost[cur][next] == 0
 *  - 점화식 : MIN(dp[cur][mask], cost[cur][next] + dp[next][mask | (1<<next)])
 *  
 * 
 */

public class Main {
    static final int INF = 1_000_000_000;
    static int N, FULL, ans;
    static int[][] cost, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        FULL = (1 << N) - 1;
        dp = new int[N][FULL + 1];
        for(int i = 0 ; i < N; i++){
            Arrays.fill(dp[i], -1);
        }

        ans  = tsp(0, 1);
        System.out.println(ans);
    
    }

    public static int tsp(int cur, int mask){
        // 기저 조건
        // 모든 도시 방문 완료 -> 시작점으로 복귀
        if(mask == FULL){
            if(cost[cur][0] == 0)   return INF; // 시작점으로 복귀 불가
            return cost[cur][0];
        }

        // 현재 cur, mask 상태가 이미 방문한 상태면 
        // 저장된 dp 값 바로 반환(메모이제이션)
        if(dp[cur][mask] != -1) return dp[cur][mask];

        int ret = INF;
        for(int next = 0 ; next < N; next++){
            // 방문 검사
            if((mask & (1 << next)) != 0) continue;

            // 길 확인
            if(cost[cur][next] == 0)    continue;

            // 다음 가능 경로 모두 탐색, 최소 후보(cand) 갱신
            // 다음 상태 : tsp(next, nextMask)
            int nextMask = mask | (1 << next);
            int cand = cost[cur][next] + tsp(next, nextMask);
            if(cand < ret)  ret = cand;
        }

        return dp[cur][mask] = ret; // 메모이제이션
    }
}
