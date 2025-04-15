import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_17305_사탕_배달_250415 {

    static PriorityQueue<Integer> pq3 = new PriorityQueue<>();
    static PriorityQueue<Integer> pq5 = new PriorityQueue<>();
    static long[][] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        sum = new long[2][W + 5];
        long ans = 0;

        for(int i=0; i<N; ++i){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            if(w == 3 ){
                pq3.add(-s);
            }else{
                pq5.add(-s);
            }
        }

        int idx = 3;
        while(!pq3.isEmpty()){
            if(idx > W) break;
            long weight = pq3.poll();
            for(int i= idx; i<idx+3; ++i){
                sum[0][i] = sum[0][i-3] - weight;
            }
            idx +=3;
        }

        for(int i=idx; i<=W; ++i){
            sum[0][i] = sum[0][idx-1];
        }

        idx = 5;
        while(!pq5.isEmpty()){
            if(idx > W) break;
            long weight = pq5.poll();
            for(int i= idx; i<idx+5; ++i){
                sum[1][i] = sum[1][i-5] - weight;
            }
            idx += 5;
        }

        for(int i=idx; i<=W; ++i){
            sum[1][i] = sum[1][idx-1];
        }

        for(int i=0; i<W; ++i){
            ans = Math.max(ans, sum[1][i] + sum[0][W - i]);
        }

        System.out.println(ans);
    }


}
