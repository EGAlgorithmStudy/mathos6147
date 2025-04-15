import java.util.Scanner;

public class boj_24885_주식_250415 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        int k = sc.nextInt();

        long[] p = new long[11];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextLong();
        }

        long count = 0, loan = 0, ans = 0;

        for (int i = 0; i < n - 1; i++) {
            m += p[i] * count;
            count = 0;

            if (p[i] < p[i + 1]) {
                if ((k + 1) * m >= p[i]) {
                    m -= loan;
                    loan = m * k;
                    m += loan;
                    count = m / p[i];
                    m %= p[i];
                }
            } else {
                if ((k + 1) * m >= p[i]) {
                    long mm = m, c = 0, l = loan;
                    mm -= l;
                    l = mm * k;
                    mm += l;
                    c = mm / p[i];
                    mm %= p[i];
                    mm += p[i + 1] * c;
                    if (mm >= ans) {
                        ans = mm;
                    }
                }
            }
        }

        // 마지막 날 처리
        m += p[n - 1] * count;

        if (m > ans) {
            ans = m;
        }

        System.out.println(ans);
        sc.close();
    }
}
