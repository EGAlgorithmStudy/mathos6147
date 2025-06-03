import java.util.Scanner;
import java.util.StringTokenizer;

public class boj_15651_N과M3_250603 {

    static int[] selected;
    static int N,M;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];

        func(0);

        System.out.println(sb.toString());
    }

    static void func(int cnt) {
        if(cnt == M){
            for(int i=0; i<M; ++i){
                sb.append(selected[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; ++i){
            selected[cnt] = i+1;
            func(cnt+1);
        }
    }
}
