from collections import deque


def bfs(start):
    global g, visited
    q = deque([start])
    cnt = 1
    while q:
        now = q.popleft()
        for next in g[now]:
            if not visited[next]:
                visited[next] = True
                q.append(next)
                cnt += 1
    return cnt


def solution(n, wires):
    global g, visited
    answer = n

    for i in range(len(wires)):
        g = [[] for _ in range(n + 1)]
        for j in range(len(wires)):
            if i != j:
                v1, v2 = wires[j]
                g[v1].append(v2)
                g[v2].append(v1)
        visited = [False] * (n + 1)
        sList = []
        for k in range(1, n + 1):
            if not visited[k]:
                visited[k] = True
                sList.append(bfs(k))
        # print(sList)
        answer = min(answer, abs(sList[0] - sList[1]))

    return answer