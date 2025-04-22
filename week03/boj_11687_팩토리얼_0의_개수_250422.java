import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11687_팩토리얼_0의_개수_250422 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long M = Long.parseLong(br.readLine());

        long left = 1, right = 5 * M;
        long ans = Long.MAX_VALUE;

        while(left <= right){
            long target = left + (right - left) / 2;
//            System.out.println(target + " " + left + " " + right);
            long num = function(target);

            if(num > M){
                right = target-1;
            }else if(num < M){
                left = target+1;
            }else{
                right = target-1;
                ans = Math.min(ans,target);
            }
        }

        if(ans == Long.MAX_VALUE){
            System.out.println(-1);
        }
        else System.out.println(ans);
    }

    static long function(long target){
        long ret = 0;
        long num = 5;
        while(num <= target){
            ret += target / num;
            num *= 5;
        }

        return ret;
    }

    /**
     * 1 5
     * 2 10
     * 3 15
     * 4 20
     * 5 x
     * 6 25
     * 7 30
     * 8 35
     * 9 40
     * 10 45
     * 11 x
     * 12 50
     * 13 55
     * 14 60
     * 15 65
     * 16 70
     * 17 x
     * 18 75
     * 19 80
     * 20 85
     * 21 90
     * 22 95
     * 23 x
     * 24 100
     * 25 105
     * 26 110
     * 27 115
     * 28 120
     * 29 x
     * 30 x
     * 31 125 = 5 * 25
     */
}
