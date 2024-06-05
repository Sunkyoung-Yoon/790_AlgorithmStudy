import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 화분 번호 1 ~ N
        int Q = Integer.parseInt(st.nextToken()); // 물 주는 횟수
        int T = Integer.parseInt(st.nextToken()); // 식물 높이
        int[] arr = new int[N+1];

        // 물 주는 구간
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // 바로 앞 지점이 2고 지금 지점이 -1이라면 뒤에 1번씩 물 주는 구간이 남아있다는 뜻 
            // 바로 앞 지점이 1이고 지금 지점이 -1이라면 여기부터는 물 주는 구간이 아니라는 뜻
            arr[L]++; // 물주는 시작점
            
            if(R+1 > N) continue;

            arr[R+1]--; // 물을 주다가 -1을 만나면 이후에는 물을 줄 필요x
        }

        // 화분은 1~N번
        for(int i=2; i<=N; i++) {
            arr[i] += arr[i-1];
        }

        boolean flag = false;
        for(int i=1; i<=N; i++) {
            if(arr[i] == T) {
                flag = true;
                sb.append(i+" ");
            }
        }

        // T 높이 화분이 없다면 -1
        System.out.println(flag?sb:-1);
    }
}