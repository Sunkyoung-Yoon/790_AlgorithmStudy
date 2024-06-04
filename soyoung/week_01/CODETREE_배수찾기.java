import java.io.*;
import java.util.*;

public class CODETREE_배수찾기 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Queue<Long> pq = new LinkedList<>();

        pq.add(3L);
        pq.add(5L);

        long now = 0;
        int num = 1;

        for (int i = 0; i < N; i++) {
            now = pq.poll();

            pq.add(3L*num);
            pq.add(5L*num);
            num++;
        }

        System.out.println(now);
    }
}
