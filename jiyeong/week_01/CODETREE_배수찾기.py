N = int(input())

arr = [3, 5, 3, 3, 5, 3, 3]
nums = [3, 5, 6, 9, 10, 12, 15]
idx = N%7 - 1
num = arr[idx]
m = N//7
if N % 7 == 0:
    m -= 1
# print(idx)
print(15*m+nums[idx])