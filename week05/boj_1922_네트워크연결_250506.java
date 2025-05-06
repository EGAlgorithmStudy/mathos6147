import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1922_네트워크연결_250506 {

    static int[] parent;
    static PriorityQueue<line> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int num = 1, ans = 0;
        StringTokenizer st;

        initParent(N);
        for(int i=0; i<M; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new line(a, b, c));
        }

        while(num < N || !pq.isEmpty()){
            line temp = pq.poll();
            if(unionParent(temp.x, temp.y)){
                num++;
                ans += temp.cost;
            }
        }

        System.out.println(ans);
    }

    static void initParent(int N){
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }
    }

    static int findParent(int a){
        if(parent[a] == a){
            return a;
        }

        a = parent[a];
        return parent[a] = findParent(a);
    }

    static boolean unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a == b){
            return false;
        }

        if(a > b){
            parent[a] = b;
        }
        else{
            parent[b] = a;
        }

        return true;
    }

    static class line implements Comparable<line>{
        int cost, x,y;

        line(int x,int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(line o) {
            return cost - o.cost;
        }
    }
}
