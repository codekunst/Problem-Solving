import sys

file_path = "./input.txt"
sys.stdin = open(file_path)

sys.setrecursionlimit(10000)
input = sys.stdin.readline


def DFS(cur):
    visited.add(cur)

    for nxt in adj_list[cur]:
        if not nxt in visited:
            DFS(nxt)


N, M = map(int, input().split())

adj_list = [[] for _ in range(N + 1)]

for _ in range(M):
    s, e = map(int, input().split())
    adj_list[s].append(e)
    adj_list[e].append(s)

visited = set()

answer = 0

for node in range(1, N + 1):
    if not node in visited:
        DFS(node)
        answer += 1

print(answer)
