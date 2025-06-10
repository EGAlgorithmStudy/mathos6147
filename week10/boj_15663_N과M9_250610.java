import java.util.*;

public class boj_15663_N과M9_250610 {

    static int[] selected, visited;
    static int[] arr;
    static int N,M;
    static StringBuilder sb;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

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

        func(0);
    }

    static void func(int cnt) {
        if(cnt == M){
            sb = new StringBuilder();
            for(int i=0; i<M; ++i){
                sb.append(arr[selected[i]]+" ");
            }
            if(!set.contains(sb.toString())){
              set.add(sb.toString());
              System.out.println(sb.toString());
            }
            return;
        }

        for(int i=0; i<N; ++i){
            if(visited[i] == 0){
                visited[i] = 1;
                selected[cnt] = i;
                func(cnt + 1);
                visited[i] = 0;
            }
        }
    }
}
