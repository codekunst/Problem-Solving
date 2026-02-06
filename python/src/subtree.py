import sys

file_path = "./input.txt"
sys.stdin = open(file_path)


def dfs(nowNode):
    global result
    result += 1
    visited[nowNode] = True

    for nextNode in tree[nowNode]:
        if not visited[nextNode]:
            dfs(nextNode)

    visited[nowNode] = False


T = int(input())

for tc in range(1, T + 1):
    E, N = map(int, input().split())
    tree = [[] for i in range(E + 2)]
    visited = [False for i in range(E + 2)]
    result = 0

    connect = list(map(int, input().split()))
    for i in range(0, 2 * E, 2):
        tree[connect[i]].append(connect[i + 1])

    dfs(N)

    print(f"#{tc} {result}")
