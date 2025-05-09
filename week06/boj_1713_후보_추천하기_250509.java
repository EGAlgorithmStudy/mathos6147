import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1713_후보_추천하기_250509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[101];
        int[] when = new int[101];
        int[] image = new int[N];
        int cnt = 0;
        for(int i = 0; i < 101; i++) {
            arr[i] = 0;
            when[i] = 0;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; ++i) {
            int number = Integer.parseInt(st.nextToken());
            boolean isInImage = false;
            for(int j = 0; j < N; j++) {
                if(image[j] == number) {
                    arr[number]++;
                    isInImage = true;
                    break;
                }
            }

            if(isInImage) {continue;}
            if(cnt == N) {
                int target = -1, count = 9999;


                for(int j=0; j<N; ++j){
                    if(arr[image[j]] == count){
                        if(when[image[j]] < when[image[target]]){
                            target = j;
                            count = arr[image[j]];
                        }
                    }
                    else if(arr[image[j]] < count){
                        target = j;
                        count = arr[image[j]];
                    }
                }
                arr[image[target]] = 0;
                image[target] = number;
            }
            else{
                image[cnt++] = number;
            }
            arr[number]++;
            when[number] = i+1;
        }

        for (int i = 0; i < 101; i++) {
            arr[i] = 0;
        }

        for(int i = 0; i < N; i++) {
            arr[image[i]] = 1;
        }

        for (int i = 1; i < 101; ++i) {
            if(arr[i] == 1)System.out.print(i + " ");
        }
    }
}
