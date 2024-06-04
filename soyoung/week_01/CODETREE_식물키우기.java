import java.io.*;
import java.util.*;

public class CODETREE_식물키우기 {

    static int N, Q, T, L, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 높이 변화량
        int[] height = new int[N + 2];

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            height[L]++; // 범위 증가
            if (R + 1 <= N) {
                height[R + 1]--;
            }
        }

        int[] heightList = new int[N + 1];
        heightList[1] = height[1];
        for (int i = 2; i <= N; i++) {
            heightList[i] = heightList[i - 1] + height[i];
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if (heightList[i] == T) {
                sb.append(i).append(" ");
                flag = true;
            }
        }

        // 결과 출력
        if (flag) {
            System.out.println(sb.toString().trim());
        } else {
            System.out.println(-1);
        }
    }
}