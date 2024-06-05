import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 15 18 20 21 24 25 27
        // 15를 기준으로 0 1 2 3 4 5 6 7
        int[] arr = {0, 3, 5, 6, 9, 10, 12};

        int N = Integer.parseInt(br.readLine());

        int idx = N%7; // 몇번째를 더할 건 지
        int mul = N/7;  // 몇번째 15의 배수인 지

        System.out.println(15*mul + arr[idx]);
    }
}