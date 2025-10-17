import java.io.*;
import java.util.*;

/* [문제]
 * 
 *  - 0 ~ 4 : 중심, 상, 좌, 하, 우
 *  - 0에서 시작
 *  - 두 발이 같은 지점 X
 *  
 *  <이동>
 *  - 중앙 -> 다른 지점 : 2
 *  - 다른 지점 -> 인접 지점 : 3
 *  - 반대편 -> 4
 *  - 같은 지점 -> 1
 *  
 * [풀이]
 * 
 * - dp[i][L][R] : i번째 지점까지 갔을 때, 왼발, 오른발이 각각 L, R위에 있을때의 최소 힘
 * - i번째 명령이 x로 이동하는 것일때
 *      => 왼발로 이동 , 오른발로 이동 각각 갱신
 *      => 마지막에 전체 순회하면서 최소값 찾기
 *  
 */

public class Main {
    static int[][][] dp;
    static ArrayList<Integer> seq;
    static final int INF = 500000;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        seq = new ArrayList<>();
        
        while(true){
            int inp = Integer.parseInt(st.nextToken());
            
            // 0이 입력되면 종료
            if(inp == 0)    break;
            seq.add(inp);
        }
        int len = seq.size();
        dp = new int[len+1][5][5]; // dp[len][L][R]

        // 미방문 지점 INF 처리
        for(int i = 0; i <= len; i++){
            for(int L = 0; L < 5; L++){
                Arrays.fill(dp[i][L], INF);
            }
        }

        // 출발 지점 초기화
        dp[0][0][0] = 0;

        for(int i = 0 ; i < len; i++){
            int next = seq.get(i);

            for(int L = 0; L < 5; L++){
                for(int R = 0; R < 5; R++){
                    int cur = dp[i][L][R];
                    
                    // 도달 불가 지점
                    if(cur >= INF)   continue;

                    // 1. 왼발로 이동
                    dp[i+1][next][R] = Math.min(dp[i+1][next][R], cur + move(L, next));

                    // 2. 오른발로 이동
                    dp[i+1][L][next] = Math.min(dp[i+1][L][next], cur + move(R, next));

                }
            }
        }
        ans = Integer.MAX_VALUE;
        for(int L = 0; L < 5; L++){
            for(int R = 0; R < 5; R++){
                ans = Math.min(ans, dp[len][L][R]);
            }
        }
        System.out.println(ans);
    }

    // 이동 함수 from -> to
    public static int move(int from, int to){
        /* 
         * 1. 같은 지점 
         * 2. from = 0 
         * 3. 반대편 이동
         * 4. 인접구역 이동
         * 
         */
        if(from == to){
            return 1;
        }
        else if(from == 0){
            return 2;
        }else if((from + to) % 2 == 0){
            return 4;
        }else   return 3;
 
    }

}
