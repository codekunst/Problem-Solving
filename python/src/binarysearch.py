T = int(input())

for tc in range(1, T + 1):
    P, A, B = map(int, input().split())
    A_Count, B_Count = 0, 0

    left, right = 1, P

    while True:
        center = (left + right) // 2
        A_Count += 1

        if center > A:
            right = center
        elif center < A:
            left = center
        else:
            break

    left, right = 1, P

    while True:
        center = (left + right) // 2
        B_Count += 1

        if center > B:
            right = center
        elif center < B:
            left = center
        else:
            break

    if A_Count < B_Count:
        print(f"#{tc} A")
    elif A_Count > B_Count:
        print(f"#{tc} B")
    else:
        print(f"#{tc} 0")
