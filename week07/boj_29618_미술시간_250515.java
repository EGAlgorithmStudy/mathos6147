import java.io.*;
import java.util.*;

public class boj_29618_미술시간_250515 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }

        int[] ans = new int[n + 1];

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            // 색칠할 인덱스를 저장할 임시 리스트
            List<Integer> toErase = new ArrayList<>();

            // lower_bound(a)부터 b 이하까지
            for (Integer it = set.ceiling(a); it != null && it <= b; it = set.higher(it)) {
                ans[it] = x;
                toErase.add(it);
            }

            // 색칠한 인덱스는 집합에서 제거
            for (int idx : toErase) {
                set.remove(idx);
            }
        }

        // 빠른 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(' ');
        }
        System.out.println(sb);
    }
}
