import sys

path = "./input.txt"

sys.stdin = open(path)


def dfs():
    while stack:
        node = stack.pop()

        if maze[node[0]][node[1]] == 3:
            return 1

        for i in range(4):
            nr, nc = [node[0] + dr[i], node[1] + dc[i]]

            if (
                0 <= nr
                and nr <= N - 1
                and 0 <= nc
                and nc <= N - 1
                and maze[nr][nc] != 1
                and not visited[nr][nc]
            ):
                visited[nr][nc] = True
                stack.append([nr, nc])

    return 0


T = int(input())
dr, dc = [1, -1, 0, 0], [0, 0, -1, 1]

for tc in range(1, T + 1):
    N = int(input())
    maze = [list(map(int, list(input()))) for _ in range(N)]

    start = [[r, c] for r in range(N) for c in range(N) if maze[r][c] == 2][0]
    stack = [start]
    visited = [[False] * N for _ in range(N)]
    visited[start[0]][start[1]] = True

    answer = dfs()

    print(f"#{tc} {answer}")
