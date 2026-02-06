T = int(input())

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    numbers = list(map(int, input().split()))
    prefix = [0 for i in range(N + 1)]
    for i in range(1, N + 1):
        prefix[i] = prefix[i - 1] + numbers[i - 1]
    lst = []
    for i in range(M, N + 1):
        lst.append(prefix[i] - prefix[i - M])
    print(f"#{tc} {max(lst) - min(lst)}")
