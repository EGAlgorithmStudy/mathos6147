import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1309_동물원_250506 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int MOD = 9901;

        long[][] dp = new long[N + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1; // 기저 사례

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        long ans = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
        System.out.println(ans);
    }
}

/**
 * 0 일때 1개  1
 * 1 일때 3개  1 + 2*1 (1 + 2)
 * 2 일때 7개 (3 * 2 + 1) 1 + 2*2 + 2 (1 + 2 + 4) 3 * 2 + 1
 * 3 일때 17개 (7 * 2 + 3)1 + 2*3 + 8 + 2 (1 + 6 + 8 + 2) 7 * 2 + 3
 * 4 일때 41개 (17 * 2 + 7)1 + 2*4 + (10 + 6 + 2) + (6 + 4 + 2) + 2 (1 + 2 + 2 + 4 + 8 + 16)
 * 5 일때    1 + 2*5 + (14 + 10 + 6 + 2) + (8 + 6 + 4 + 2) +  + 2
 */
