import java.util.*;
import java.io.*;

public class CODETREE_단어정렬2 {

    static int N;
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();
        
        // 각 단어를 리스트에 추가
        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }
        
        // 정렬: 길이 짧은 순 + 짧다면 사전순
        words.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length());
            } else {
                return o1.compareTo(o2);
            }
        });
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append("\n");
        }
        System.out.print(sb.toString());
    }
}