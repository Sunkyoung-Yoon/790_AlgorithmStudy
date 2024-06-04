import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PROG_전력망을둘로나누기 {

    static int[][] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] wires = new int[N - 1][2];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            wires[i][0] = Integer.parseInt(st.nextToken());
            wires[i][1] = Integer.parseInt(st.nextToken());
        }

        Solution sol = new Solution();
        int answer = sol.solution(N, wires);
        System.out.println(answer);
    }

    public int solution(int n, int[][] wires) {
        arr = new int[n + 1][n + 1];

        for (int i = 0; i < wires.length; i++) {
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }

        int answer = n;

        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];

            arr[a][b] = 0;
            arr[b][a] = 0;

            answer = Math.min(answer, bfs(a, n));

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        return answer;
    }

    private int bfs(int start, int n) {
        boolean[] visit = new boolean[n + 1];
        int cnt = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            int point = queue.poll();
            cnt++;

            for (int i = 1; i <= n; i++) {
                if (!visit[i] && arr[point][i] == 1) {
                    queue.offer(i);
                    visit[i] = true;
                }
            }
        }

        return Math.abs(n - 2 * cnt);
    }
}