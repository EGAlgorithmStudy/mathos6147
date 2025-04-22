import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class boj_15482_한글_LCS_250422 {

    static int[][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String a = br.readLine();
        String b = br.readLine();
        if(a.length() < b.length()){
            String tmp = b;
            b = a;
            a = tmp;
        }

        dp = new int[a.length() + 1][2];
        for(int i = 0; i <= a.length(); i++){
            dp[i][0] = 0;
            dp[i][1] = 0;
        }

        for(int i=0; i<a.length(); i++){
            for(int j=0; j<b.length(); j++){
                if(a.charAt(i) == b.charAt(j)){
                    dp[j+1][(i+1)%2] = dp[j][i%2] + 1;
                }else{
                    dp[j+1][(i+1)%2] = Math.max(dp[j][(i+1)%2], dp[j+1][i%2]);
                }
            }
        }

        System.out.println(Math.max(dp[b.length()][0], dp[b.length()][1]));
    }
}
