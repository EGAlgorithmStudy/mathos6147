import java.util.Scanner;
import java.util.StringTokenizer;

public class boj_15650_N과M2_250603 {

    static int[] selected;
    static int N,M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];

        func(0,0);
    }

    static void func(int idx, int cnt) {
        if(cnt == M){
            for(int i=0; i<M; ++i){
                System.out.print(selected[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=idx; i<N; ++i){
            selected[cnt] = i+1;
            func(i+1, cnt+1);
        }
    }
}
