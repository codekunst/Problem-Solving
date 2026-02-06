import sys

file_path = "./input.txt"
sys.stdin = open(file_path)


def binary_search(left, right, target, count):
    if left > right:
        return -1

    mid = (left + right) // 2

    if mid < target:
        return binary_search(mid, right, target, count + 1)
    elif mid > target:
        return binary_search(left, mid, target, count + 1)
    else:
        return count


T = int(input())

for tc in range(1, T + 1):
    P, A, B = map(int, input().split())
    A_count = binary_search(0, P, A, 0)
    B_count = binary_search(0, P, B, 0)

    (
        print(f"#{tc} A")
        if A_count < B_count
        else print(f"#{tc} B") if A_count > B_count else print(f"#{tc} 0")
    )
