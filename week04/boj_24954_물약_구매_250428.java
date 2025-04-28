import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_24954_물약_구매_250428 {
    static int[] cost;
    static int[][] sale;
    static int[] selected;
    static boolean[] visited;
    static int[] c;
    static int N, answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N];
        selected = new int[N];
        visited = new boolean[N];
        sale = new int[N][N];
        c = new int[N];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; ++i){
            int t = Integer.parseInt(br.readLine());
            for(int j=0; j<t; ++j){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                sale[i][a-1] = p;
            }
        }

        permutation(0);

        System.out.println(answer);
    }

    static void permutation(int cnt){
        if(cnt == N){
            for(int i=0; i<N; ++i){
                c[i] = 0;
            }

            int sum = 0;
            for(int i=0; i<N; ++i){
                int target = selected[i];
                sum += Math.max(cost[target] - c[target], 1);
                for(int j=0; j<N; ++j){
                    if(sale[target][j] > 0){
                        c[j] += sale[target][j];
                    }
                }
            }

            answer = Math.min(answer, sum);
            return;
        }

        for(int i=0; i<N; ++i){
            if(visited[i]) continue;
            visited[i] = true;
            selected[cnt] = i;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}
