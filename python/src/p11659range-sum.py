import sys
from itertools import accumulate

input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().split())

nums = list(map(int, input().split()))

acc = [0] + list(accumulate(nums))

for _ in range(M):
    i, j = map(int, input().split())
    print("%d\n" % (acc[j] - acc[i - 1]))
