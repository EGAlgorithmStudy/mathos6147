import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_20302_민트초코_250508 {
    static int[] arr = new int[100001];
    static List<Integer> primes = new ArrayList<Integer>();
    static Set<Integer> set = new HashSet<Integer>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=2; i<=100000; ++i){
            if(arr[i] == 0){
                for(int j=2; i*j<=100000; ++j){
                    arr[i*j] = 1;
                }
                primes.add(i);
                set.add(i);
            }
        }
        
        for(int i=0; i<=100000; ++i){
            arr[i] = 0;
        }
        
        int firstNum = Integer.parseInt(st.nextToken());
        
        if (firstNum == 0) {
            System.out.println("mint chocolate");
            return;
        }
        
        func(Math.abs(firstNum), true);
        
        boolean isPlus = true;
        
        for(int i=1; i<N*2-1; ++i){
            if(i%2 == 0){
                int num = Integer.parseInt(st.nextToken());
                
                if (num == 0) {
                    if (isPlus) {
                        System.out.println("mint chocolate");
                        return;
                    }
                    continue;
                }
                
                func(Math.abs(num), isPlus);
            } else {
                if(st.nextToken().equals("*")){
                    isPlus = true;
                } else {
                    isPlus = false;
                }
            }
        }
        
        for(int prime : primes){
            if(arr[prime] < 0){
                System.out.println("toothpaste");
                return;
            }
        }
        
        System.out.println("mint chocolate");
    }
    

    static void func(int num, boolean isPlus){
        if(num == 1) return;
        
        if(set.contains(num)){
            arr[num] += isPlus ? 1 : -1;
            return;
        }
        
        for(int prime : primes){
            if(num == 1) break;
            if(prime * prime > num) break;
            
            while(num % prime == 0){
                arr[prime] += isPlus ? 1 : -1;
                num /= prime;
            }
        }
        
        if(num > 1){
            arr[num] += isPlus ? 1 : -1;
        }
    }
}
