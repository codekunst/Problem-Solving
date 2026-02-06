import sys

input = sys.stdin

diff = [[0, 1], [1, 0], [0, -1], [-1, 0]]

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    index = int[N * N][2]
    result = int[N][N]

    search(0, 0)

    print()
