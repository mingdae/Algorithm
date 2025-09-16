import java.io.*;
import java.util.*;

public class Solution {
    static final int MOD = 1234567891;
    static long[] fact;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        // 팩토리얼 미리 계산
        fact = new long[1000001];
        fact[0] = 1;
        for(int i = 1; i <= 1000000; i++) {
            fact[i] = (fact[i-1] * i) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long ans = nCr(n, r);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    // nCr 계산
    static long nCr(int n, int r) {
        if(r == 0 || n == r) return 1;
        long numerator = fact[n];
        long denominator = (fact[r] * fact[n-r]) % MOD;
        return (numerator * pow(denominator, MOD-2)) % MOD;
    }

    // 거듭제곱 빠른 계산
    static long pow(long base, long exp) {
        long result = 1;
        while(exp > 0) {
            if((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}
