import java.io.*;
import java.nio.Buffer;
import java.util.*;

/* [문제]
 * - N*M 행렬 A, M*K 행렬 B
 * - 연산 수 N * M * K
 * - 곱셈 연산 횟수의 최소값
 * - 행렬 순서 변경 X
 * 
 * [풀이]
 * - dp[i][j] : i 번째 행렬부터 j번째 행렬까지의 최소 연산 횟수
 * - 목표 : dp[1][N]
 * - 점화식
 *     - k로 나눈다고 생각
 *     - i번째~K번째 연산 * K+1번째 ~ j번째 연산
 *     - dp[i][k] + dp[k+1][j] + 마지막 연산(N*M*K)
 *     =>  dp[i][j] = Math.min(dp[i][k] + dp[K+1][j] + r[i] * c[k] * c[j] )
 */

public class Main {
    static int[][] dp;
    static int[] R, C;
    static int n, ans;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        R = new int[n+1];
        C = new int[n+1];
        dp = new int[n+1][n+1];

        // 초기화
        for(int i = 1; i <= n ; i++)   Arrays.fill(dp[i], INF);

        for(int i = 1; i <= n; i++)    dp[i][i] = 0;

        for(int i = 1 ; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());    
        }

        for(int end = 2; end <= n; end++){
            for(int start = end - 1; start >= 1 ; start--){
                for(int mid = start; mid < end; mid++){
                    int calc = R[start] * C[mid] * C[end];
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end] + calc);
                }
            }
        }
        
        System.out.println(dp[1][n]);
        

    }


}
