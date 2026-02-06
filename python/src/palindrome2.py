import sys

path = "./input.txt"

sys.stdin = open(path)


def isPalindrome(left, right, word):
    flag = True

    while left < right:
        if word[left] != word[right]:
            flag = False
            break
        left += 1
        right -= 1

    return flag


T = int(input())
answer = ""

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    wordsRow = [list(input()) for _ in range(N)]

    wordColumn = list(zip(*wordsRow))

    for i in range(N):
        wordA = wordsRow[i]
        wordB = wordColumn[i]

        for j in range(N - M + 1):
            wordA_sliced = wordA[j : j + M]
            wordB_sliced = wordB[j : j + M]

            answer = (
                wordA_sliced
                if wordA_sliced == wordA_sliced[::-1]
                else wordB_sliced if wordB_sliced == wordB_sliced[::-1] else answer
            )

    print(f"#{tc} {''.join(answer)}")
