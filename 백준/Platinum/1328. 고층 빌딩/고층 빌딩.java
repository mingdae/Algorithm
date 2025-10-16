import java.io.*;
import java.util.*;

/* [문제]
 * - 빌딩 N개, 높이 1~N
 * - 같은 높이 빌딩 X
 * - 가장 왼쪽 / 오른쪽에서 몇개를 볼 수 있는지
 * - 가능한 빌딩 순서의 경우의 수
 * 
 * [풀이]
 * - 증가 수열 개수
 * 
 * 
 *  
 */

public class Main {
    
    static int N, L, R;
    static final int MOD = 1000000007;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        dp = new long[N+1][L+1][R+1];
        dp[1][1][1] = 1;
        
        for(int n = 2; n <= N ; n++){
            for(int l = 1; l <= L; l++){
                for(int r = 1; r <= R; r++){
                    long temp = 0;
                    temp = (dp[n-1][l][r-1] + dp[n-1][l-1][r]) % MOD;
                    temp = (temp + ((n-2) * dp[n-1][l][r] % MOD)) % MOD;

                    dp[n][l][r] = temp;
                }
            }
        }
        
        System.out.println(dp[N][L][R]);
        
    }
}
