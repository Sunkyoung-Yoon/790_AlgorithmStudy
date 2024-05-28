from collections import deque


def solution(priorities, location):
    answer = 1
    q = deque([])
    for i in range(len(priorities)):
        q.append((priorities[i], i))

    priorities.sort(reverse=True)
    idx = 0

    while idx < len(priorities):
        # print(q)
        n = q.popleft()
        if n[0] == priorities[idx]:
            if n[1] == location:
                break
            idx += 1
            answer += 1
        else:
            q.append(n)

    return answer