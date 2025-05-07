import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9203_호텔예약_250507 {

    static int[][] calTime = new int[4][12];
    static int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int T, B, C;
    static final int dayTime = 1440;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        calTime[0][0] = 0;
        for(int j=0; j<4; j++) {
            for (int i = 1; i < 12; ++i) {
                calTime[j][i] = calTime[j][i - 1] + month[i];
                if(j == 3 && i == 2) calTime[j][i]++;
            }
            if(j!= 3)
                calTime[j+1][0] = calTime[j][11] + month[12];
        }


        for (int t = 1; t <= T; t++) {
            int ans = 1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            List<Hotel> hotels = new ArrayList<>();
            for(int j = 0; j < B; ++j){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String startDate = st.nextToken();
                String startTime = st.nextToken();
                String endDate = st.nextToken();
                String endTime = st.nextToken();
                hotels.add(new Hotel(calculateTime(startDate, startTime), calculateTime(endDate, endTime)));
            }

            Collections.sort(hotels, new Comparator<Hotel>() {
                @Override
                public int compare(Hotel o1, Hotel o2) {
                    if(o1.startTime == o2.startTime)
                        return o1.endTime - o2.endTime;
                    return o1.startTime - o2.startTime;
                }
            });

            PriorityQueue<Hotel> pq = new PriorityQueue<>();
            pq.add(new Hotel(hotels.get(0).startTime, hotels.get(0).endTime +C));
            for(int i=1; i<hotels.size(); i++){
                int curTime = hotels.get(i).startTime;
                while(!pq.isEmpty()){
                    if(pq.peek().endTime <= curTime){
                        pq.remove();
                    }
                    else break;
                }
                pq.add(new Hotel(hotels.get(i).startTime, hotels.get(i).endTime + C));
                ans = Math.max(ans, pq.size());
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Hotel implements Comparable<Hotel>{
        int startTime, endTime;
        public Hotel(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Hotel o) {
            return endTime - o.endTime;
        }
    }

    static int calculateTime(String startDate, String endDate) {

        StringTokenizer st = new StringTokenizer(startDate, "-");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        day += calTime[year - 2013][month - 1];

        st = new StringTokenizer(endDate, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        minute += day * dayTime + hour * 60;

        return minute;
    }
}
