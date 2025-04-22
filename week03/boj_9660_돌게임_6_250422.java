import java.io.IOException;
import java.util.Scanner;

public class boj_9660_돌게임_6_250422 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        if(N % 7 == 0 || N % 7 == 2){
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }

    }
}
