import sys

file_path = "./input.txt"

sys.stdin = open(file_path)

T = int(input())

for tc in range(1, T + 1):
    N, M, L = map(int, input().split())

    tree = [0 for i in range(N + 1)]

    for i in range(M):
        node, value = map(int, input().split())
        tree[node] = value

    for i in range(N, 0, -1):
        tree[i // 2] += tree[i]

    print(f"#{tc} {tree[L]}")
