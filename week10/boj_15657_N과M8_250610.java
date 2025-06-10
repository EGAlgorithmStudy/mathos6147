import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj_15657_N과M8_250610 {

    static int[] selected, visited;
    static int[] arr;
    static int N,M;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        visited = new int[N];
        arr = new int[N];

        st = new StringTokenizer(sc.nextLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        func(0,0);

        System.out.println(sb.toString());
    }

    static void func(int cnt, int idx) {
        if(cnt == M){
            for(int i=0; i<M; ++i){
                sb.append(arr[selected[i]]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=idx; i<N; ++i){
            selected[cnt] = i;
            func(cnt + 1, i);
        }
    }
}
