import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] str = new String[n];

        for(int i=0; i<n; i++) {
            str[i] = br.readLine();
        }

        Arrays.sort(str, (s1, s2) -> {
            if(s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            else {
                return s1.length() - s2.length();
            }
        });

        for(String s : str) {
            sb.append(s+"\n");
        }

        System.out.println(sb);

    }
}