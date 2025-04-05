import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_15685_드래곤커브_250404 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] curve = new int[11][1025];
    static int[][] map = new int[101][101];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Queue<int[]> q = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        curve[0][0] = 0;
        curve[1][0] = 0; curve[1][1] = 1;

        for(int i=2; i<=10; ++i){
            int n = 1 << (i-1);
            int m = 1 << (i-2);
            for(int j=0; j < n; ++j){
                curve[i][j] = curve[i-1][j];
            }

            for(int j=0; j < m; ++j){
                curve[i][j+n] = (curve[i-1][j] + 2) % 4;
                curve[i][j+m+n] = curve[i-1][j+m];
            }
        }

        for(int i=0; i<N; ++i){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
            for(int j=0; j< (1 << g); ++j){
                x += dx[(curve[g][j] + d) % 4];
                y += dy[(curve[g][j] + d) % 4];
                map[x][y] = 1;
            }
        }

        for(int i=0; i< 100; ++i){
            for(int j=0; j< 100; ++j){
                if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1){
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
