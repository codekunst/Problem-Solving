import sys

file_path = "./input.txt"

sys.stdin = open(file_path)

T = int(input())


def union(nodeA, nodeB):
    nodeA = find(nodeA)
    nodeB = find(nodeB)

    if nodeA != nodeB:
        parents[nodeB] = nodeA


def find(node):
    if parents[node] == node:
        return parents[node]
    parents[node] = find(parents[node])
    return parents[node]


for tc in range(1, T + 1):
    N, M = map(int, input().split())

    parents = [i for i in range(N + 1)]

    connected = list(map(int, input().split()))

    for i in range(0, 2 * M, 2):
        union(connected[i], connected[i + 1])

    for i in range(N + 1):
        parents[i] = find(i)

    print(f"#{tc} {len(set(parents)) - 1}")
