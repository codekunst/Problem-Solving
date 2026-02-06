import sys

path = "./input.txt"
file_input = open(path)
sys.stdin = file_input

T = int(input())

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    numbers = list(map(int, input().split()))

    max_sum = -1
    min_sum = 10000 * 100 + 1

    for i in range(N - M + 1):
        total = sum(numbers[i : i + M])
        max_sum = max(max_sum, total)
        min_sum = min(min_sum, total)

    print(f"#{tc} {max_sum - min_sum}")
