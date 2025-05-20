public class prog_퍼즐_게임_챌린지_250520 {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1, right = 100000;
        int answer = -1;

        while(left <= right){
            int mid = (left + right) / 2;

            if(parametic(diffs, times, limit, mid)){
                answer = mid;
                right = mid - 1;  // 더 작은 level로도 가능한지 확인
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    boolean parametic(int[] diffs, int[] times, long limit, int level){
        long time = 0;

        for(int i=0; i<diffs.length; ++i){
            if(i == 0 || diffs[i] <= level){
                time += times[i];
            } else {
                int mistakes = diffs[i] - level;
                time += (long)mistakes * (times[i] + times[i - 1]) + times[i];
            }

            if(time > limit) return false; // 중간에 초과하면 탈락
        }

        return true;
    }
}
