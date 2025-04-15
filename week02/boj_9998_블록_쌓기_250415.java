import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9998_블록_쌓기_250415 {
    static long[][] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new long[2][N];
        long answer = Long.MAX_VALUE;
        long minNum = Long.MAX_VALUE;
        long maxNum = Long.MIN_VALUE;
        for (int i = 0; i < 2; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                arr[i][j] = Long.parseLong(st.nextToken());
                minNum = Math.min(minNum, arr[i][j]);
                maxNum = Math.max(maxNum, arr[i][j]);
            }
        }

        long left = minNum, right = maxNum;
        while (left <= right) {
            long mid = left + (right - left) / 2;

            long costMid = func(mid);
            long costMidPlus = func(mid + 1);

            answer = Math.min(answer, costMid);

            if (costMid > costMidPlus) {
                left = mid + 1;
            } else {
                if (mid == 0) break;
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static long func(long mid){
        long ret = 0;
        for(int idx=0; idx<2; ++idx) {
            for (int i = 0; i < N / 2; ++i) {
                ret += Math.abs(arr[idx][i] - (mid + N / 2 - i));
                ret += Math.abs(arr[idx][N - 1 - i] - (mid + N / 2 - i));
            }
            ret += Math.abs(arr[idx][N / 2] - mid);
        }
        return ret;
    }
}
