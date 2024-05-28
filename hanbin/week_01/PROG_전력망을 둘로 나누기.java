// 풀이 전략: 와이어 연결 끊어짐 상태를 boolean 배열로 관리, BFS. O(간선수 * (노드수 + 간선수 - 1))

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        // 인접 리스트 생성
        List<Integer>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();
        }
        
        // 와이어 연결
        for (int[] wire : wires){
            nodes[wire[0]].add(wire[1]);
            nodes[wire[1]].add(wire[0]);
        }
        
        // 와이어 끊어짐 상태 관리 배열
        boolean[] isCut = new boolean[n + 1];
        
        
        for (int[] wire : wires){
            // 와이어 연결 끊기
            isCut[wire[0]] = true;
            isCut[wire[1]] = true;
            
            // 끊어진 와이어 환경에서 BFS 돌리기
            boolean[] visited = new boolean[n + 1];
            
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++){
                if(visited[i]) continue;
                
                list.add(BFS(i, visited, nodes, isCut));
            }
            
            // 끊어진 와이어 다시 복구시키기
            isCut[wire[0]] = false;
            isCut[wire[1]] = false;
            
            // 리스트의 사이즈가 2가 아닐때는 전력망 네트워크가 2분할 되지 않은 것이므로 continue
            if (list.size() != 2) continue;
            
            answer = Math.min(answer, Math.abs(list.get(1) - list.get(0)));
        }
        
        return answer;
    }
    
    public int BFS(int num, boolean[] visited, List<Integer>[] nodes, boolean[] isCut){
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[num] = true;
        queue.add(num);
        
        int cnt = 0;
        for(; !queue.isEmpty(); cnt++){
            int cur = queue.poll();
            
            for (int i : nodes[cur]){
                if (visited[i] || (isCut[cur] && isCut[i])) continue;
                visited[i] = true;
                queue.add(i);
            }
        }
        return cnt;
    }
}
