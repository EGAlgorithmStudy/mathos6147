import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_29726_숏코딩의_왕_브실이_250415 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] dp1 = new int[N+1];
        int[] dp2 = new int[N+1];
        int answer = -100001;

        dp1[0] = 100001;
        dp2[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
            dp1[i+1] = Math.min(dp1[i], arr[i]);
        }

        for(int i=N-1; i>=0; i--){
            dp2[N-i] = Math.max(dp2[N-i-1], arr[i]);
        }



        for(int i=0; i<=M; ++i){
            answer = Math.max(answer, dp2[M - i + 1] - dp1[i + 1]);
        }

        System.out.println(answer);
    }
}
