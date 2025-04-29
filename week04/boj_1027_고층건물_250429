import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1027_고층건물_250429{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
//            System.out.println("idx : "+ i + " ,arr[" + i + "] : " + arr[i]);
            int sum = 0;
            double min = -1000000001;
            for(int j = i + 1; j < N; j++) {
                double cur = (double) (arr[j] - arr[i]) / (j-i);
                if(min < cur){
                    min = cur;
//                    System.out.println(min + " : " + arr[j] + "idx : " + j);
                    sum++;
                }
            }

            min = 1000000001;
            for(int j = i-1; j >= 0; j--) {
                double cur = (double) (arr[i] - arr[j]) / (i-j);
                if(min > cur){
                    min = cur;
//                    System.out.println(min + " : " + arr[j] + "idx : " + j);
                    sum++;
                }
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
