import java.io.*;
import java.util.*;

public class boj_15649_N과M1_250527 {
	
	static int N,M;
	static int[] selected;
	static boolean[] number;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
        
        selected = new int[N];
        number = new boolean[N+1];
        
        func(0);
	}
	
	
	static void func(int cnt){
		if(cnt == M){
	        for(int i=0; i<M; i++){
	        	System.out.print(selected[i] + " ");
	        }
	        System.out.println();
	        return;
		}
		
		for(int i=1; i<=N; i++){
			if(!number[i]){
				number[i] = true;
				selected[cnt] = i;
				func(cnt+1);
				number[i] = false;
			}
			
		}
	}
}
