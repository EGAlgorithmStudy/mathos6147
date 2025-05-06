import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_13302_리조트_250506 {

    static int n,m;
    static boolean[] holiday;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        holiday = new boolean[n+1];

        if(m>0) {
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<m; i++) {
                holiday[Integer.parseInt(st.nextToken())] = true;
            }
        }

        dp = new int[n+1][n+1];
        for(int i=0; i<n+1; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(1, 0));

    }

    static int solve(int day, int coupon) {

        if(day > n) return 0;

        if(dp[day][coupon] != -1) return dp[day][coupon];

        dp[day][coupon] = Integer.MAX_VALUE;
        if(holiday[day]) {
            return dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon));
        }
        else {
            if(coupon >= 3 ) {
                dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon-3));
            }
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon)+ 10000);
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day+3, coupon+1) +25000);
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day+5, coupon+2) + 37000);

        }

        return dp[day][coupon];
    }
}
