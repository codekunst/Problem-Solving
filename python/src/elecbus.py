T = int(input())

for tc in range(1, T + 1):
    K, N, M = map(int, input().split())
    stations = list(map(int, input().split()))
    dp = [N + 1 for i in range(0, N + K + 1)]

    for i in range(K + 1):
        dp[i] = 0

    for station in stations:
        for i in range(1, K + 1):
            dp[station + i] = min(dp[station + i], dp[station] + 1)

    if dp[N] == N + 1:
        print(f"#{tc} 0")
    else:
        print(f"#{tc} {dp[N]}")
