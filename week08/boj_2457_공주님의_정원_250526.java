import java.io.*;
import java.util.*;

public class boj_2457_공주님의_정원_250526 {
    static class Flower implements Comparable<Flower> {
        int start, end;

        Flower(int m1, int d1, int m2, int d2) {
            this.start = m1 * 100 + d1;
            this.end = m2 * 100 + d2;
        }

        @Override
        public int compareTo(Flower f) {
            if (this.start == f.start) return f.end - this.end;
            return this.start - f.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Flower> flowers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());
            flowers.add(new Flower(m1, d1, m2, d2));
        }

        Collections.sort(flowers);

        int count = 0;
        int cur = 301;
        int idx = 0;
        int maxEnd = 0;

        while (cur <= 1130) {
            boolean found = false;

            while (idx < flowers.size() && flowers.get(idx).start <= cur) {
                if (flowers.get(idx).end > maxEnd) {
                    maxEnd = flowers.get(idx).end;
                    found = true;
                }
                idx++;
            }

            if (!found) {
                System.out.println(0);
                return;
            }

            count++;
            cur = maxEnd;
        }

        System.out.println(count);
    }
}
