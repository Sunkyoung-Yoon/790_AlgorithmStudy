N, Q, T = map(int, input().split())

p = [0]*(N+2)
for _ in range(Q):
    l, r = map(int, input().split())
    p[l] += 1
    p[r+1] -= 1

ans = []
for i in range(1, N+2):
    p[i] += p[i-1]
    if p[i] == T:
        ans.append(str(i))

if len(ans) == 0:
    print(-1)
else:
    print(" ".join(ans))