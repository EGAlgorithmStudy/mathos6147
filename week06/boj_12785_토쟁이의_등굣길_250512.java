import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_12785_토쟁이의_등굣길_250512 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[][] arr = new int[201][201];

        for(int i=0; i<201; ++i){
            for(int j=0; j<201; ++j){
                arr[i][j] = 0;
                if(i == 0){
                    arr[i][j] = 1;
                }
                if(j == 0){
                    arr[i][j] = 1;
                }
            }
        }

        for(int i=1; i<201; ++i){
            for(int j=1; j<201; ++j){
                arr[i][j] = (arr[i-1][j] + arr[i][j-1]) % 1000007;
            }
        }

        System.out.println((long)arr[x - 1][y - 1] * arr[X - x][Y - y] % 1000007);
    }
}
