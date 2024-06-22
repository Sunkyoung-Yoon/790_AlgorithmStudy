import java.io.*;
import java.util.*;

public class Main {
    public static int maxScore = 0;
    public static ArrayList<int[]>[] dirArr;
    public static boolean[][] visited;
    public static int[][] arr;
    public static int[][] startPos;
    public static int[] dr = new int[]{-1, 0, 1, 0};
    public static int[] dc = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = initArray(N, br);
        startPos = initStartPos(M, br);
        dirArr = new ArrayList[M];
        visited = new boolean[N + 1][N + 1];

        for (int i = 0; i < dirArr.length; i++){
            dirArr[i] = new ArrayList<>();
        }
        for (int[] pos : startPos){
            int r = pos[0];
            int c = pos[1];

            visited[r][c] = true;
        }

        DFS(startPos[0], 0, 0);

        System.out.print(maxScore);
    }

    // 열매 수를 담고 있는 2차원 배열 생성
    public static int[][] initArray(int N, BufferedReader br) throws IOException{
        int[][] tmp = new int[N+1][N+1];

        for (int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++){
                tmp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return tmp;
    }

    // 친구들의 초기 시작 위치를 담고 있는 2차원 배열 생성
    public static int[][] initStartPos(int M, BufferedReader br) throws IOException{
        int[][] tmp = new int[M][2];

        for (int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tmp[i] = new int[]{r, c};
        }

        return tmp;
    }

    // 첫번째 친구부터 마지막 친구까지 이어나가 백트래킹으로 모든 경우의 수 체크
    public static void DFS(int[] pos, int depth, int pNum){
        if (depth == 3){
            if (dirArr[dirArr.length - 1].isEmpty()){
                DFS(startPos[pNum+1], 0, pNum + 1);
            } else {
                int score = 0;

                for (int i = 1; i < arr.length; i++){
                    for (int j = 1; j < arr[i].length; j++){
                        if (visited[i][j]){
                            score += arr[i][j];
                        }
                    }
                }

                maxScore = Math.max(score, maxScore);
            }
            return;
        }

        for (int i = 0; i < 4; i++){
            int nr = pos[0] + dr[i];
            int nc = pos[1] + dc[i];

            if (validatePosition(nr, nc, arr)){
                if (!visited[nr][nc]){
                    int score = arr[nr][nc];
    
                    visited[nr][nc] = true;
                    dirArr[pNum].add(new int[]{nr, nc});
                    DFS(new int[]{nr, nc}, depth + 1, pNum);
                    dirArr[pNum].remove(dirArr[pNum].size() - 1);
                    visited[nr][nc] = false;
                }
            }
        }
    }

    // 델타 탐색 유효성 체크
    public static boolean validatePosition(int nr, int nc, int[][] arr){
        return nr > 0 && nr < arr.length && nc > 0 && nc < arr[nr].length;
    }
}
