N = int(input())

ans = []
for _ in range(N):
    ans.append(input().strip())

sorted_ans = sorted(ans, key=lambda x : (len(x), x))
for word in sorted_ans:
    print(word)