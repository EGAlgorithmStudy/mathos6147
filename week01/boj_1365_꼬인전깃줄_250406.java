import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1365_꼬인전깃줄_250406 {

    static int[] arr = new int[100005];
    static int[] lcs = new int[100005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            lcs[i] = 100005;
        }

        int left, right;
        for(int i=0; i<N; i++) {
            left = 0; right = i;
            while(left < right){
                int mid = (left + right) / 2;
                if(lcs[mid] > arr[i]){
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }

            lcs[left] = arr[i];
        }

        int cnt = 0;
        for(int i=0; i<N; i++) {
            if(lcs[i] != 100005) cnt++;
        }

        System.out.println(N - cnt);
    }
}
