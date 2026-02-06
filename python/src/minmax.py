T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    numbers = list(map(int, input().split()))

    answer = max(numbers) - min(numbers)
    print(f"#{tc} {answer}")
