import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2110_공유기설치_250405 {

    static List<Integer> houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        houses = new ArrayList<>();

        for(int i=0; i<N; ++i){
            int h = Integer.parseInt(br.readLine());
            houses.add(h);
        }
        Collections.sort(houses);

        int x = 1, y = houses.get(N - 1) - houses.get(0);
        int ans = 0;
        while (x <= y) {
            int mid = (x + y) / 2;

            if(checkFunction(mid, N, C)){
                ans = mid;
                x = mid + 1;
            }else{
                y = mid-1;
            }
        }

        System.out.println(ans);
    }

    private static boolean checkFunction(int target, int N, int C) {
        int count = 1;
        int lastLocate = houses.get(0);

        for(int i = 1; i < N; i++) {
            int locate = houses.get(i);

            if(locate - lastLocate >= target) {
                count++;
                lastLocate = locate;
            }
        }
        return count >= C;
    }
}
