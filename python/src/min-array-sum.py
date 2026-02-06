import sys

file_path = "./input.txt"
sys.stdin = open(file_path)


def dfs(depth, value):
    global min_value

    if value > min_value:
        return

    if depth == N:
        min_value = min(min_value, value)
        return

    for i in range(N):
        if not visited[i]:
            visited[i] = 1
            dfs(depth + 1, value + array[depth][i])
            visited[i] = 0


T = int(input())

for tc in range(1, T + 1):
    N = int(input())

    array = [list(map(int, input().split())) for _ in range(N)]
    visited = [0] * N

    min_value = N * 10 + 1

    dfs(0, 0)

    print(f"#{tc} {min_value}")
