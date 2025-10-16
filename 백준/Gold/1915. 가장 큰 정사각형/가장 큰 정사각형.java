import java.io.*;
import java.util.*;

/*
 * - dp[i][j] : (i,j)를 오른쪽 마지막 변으로 하는 정사각형의 최대 변 길이
 *  
 * - 현재 값이 0이면 => dp[i][j] = 0
 *  
 * - 현재 값이 1이면 상, 좌상, 좌 3개의 변이 모두 최소 길이 k 이상이어야 
 *    현재 칸 포함된 정사각형 확장 가능
 *     => dp[i][j] = 1 + MIN(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
 * 
 * - 목표 : MAX(dp[i][j]) * MAX(dp[i][j])        
 *   
 * 
 */

public class Main {
    
    static int n, m, ans;
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1]; // 1-index
        dp = new int[n+1][m+1];

        /* 1-index, 0-padding */
        for(int i = 1 ; i <= n ; i++){
            String input = br.readLine();
            for(int j = 1 ; j <= m; j++){
                map[i][j] = input.charAt(j-1) - '0';
            }
        }
        
        int maxDp = 0;
        for(int i = 1; i <= n ; i++){
            for(int j = 1; j <= m; j++){
                if(map[i][j] == 0)   dp[i][j] = 0;
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
                maxDp = Math.max(maxDp, dp[i][j]);
            }
        }
        System.out.println(maxDp * maxDp);

        

    }
}
