public class pro_비밀_코드_해독.java {
    
    static int[] number;
    static int c = 0;
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        number = new int[n+1];
        
        func(1, 0, n, q, ans);
        answer = c;
        return answer;
    }
    
    static void func(int idx, int cnt, int n, int[][] q, int[] ans){
        if(cnt == 5){
            for(int i=0; i<q.length; ++i){
                int count = 0; 
                for(int j=0; j<5; ++j){
                    if(number[q[i][j]] == 1){
                        count++;
                    }
                }
                if(count != ans[i]){
                    return;
                }
            }
            
            c++;
            return;
        }
        
        for(int i=idx; i<=n; ++i){
            number[i] = 1;
            func(i+1, cnt+1, n, q, ans);
            number[i] = 0;
        }
    }
}
