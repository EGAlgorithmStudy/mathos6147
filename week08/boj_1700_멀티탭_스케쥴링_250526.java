import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_1700_멀티탭_스케쥴링_250526 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] schedule = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> plugged = new HashSet<>();
        int unplugCount = 0;

        for (int i = 0; i < K; i++) {
            int current = schedule[i];
            
            if (plugged.contains(current)) continue;
            
            if (plugged.size() < N) {
                plugged.add(current);
                continue;
            }

            int toUnplug = -1;
            int farthest = -1;

            for (int device : plugged) {
                int nextUse = Integer.MAX_VALUE;
                for (int j = i + 1; j < K; j++) {
                    if (schedule[j] == device) {
                        nextUse = j;
                        break;
                    }
                }

                if (nextUse == Integer.MAX_VALUE) {
                    toUnplug = device;
                    break;
                }

                if (nextUse > farthest) {
                    farthest = nextUse;
                    toUnplug = device;
                }
            }

            plugged.remove(toUnplug);
            plugged.add(current);
            unplugCount++;
        }

        System.out.println(unplugCount);
    }
}
