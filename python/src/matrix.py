# matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]

# for c in range(3):
#     for r in range(3):
#         print(matrix[r][c])


# zero_matrix = [[0 for _ in range(4)] for _ in range(3)]

# zero_matrix = [[0] * 4 for _ in range(3)]


# print(zero_matrix)

# matrix = [list(map(int, input().split())) for _ in range(3)]

# print(matrix)


# 전치
matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]

matrix_transpose = [[0] * 3 for _ in range(3)]

for i in range(3):
    for j in range(3):
        matrix_transpose[j][i] = matrix[i][j]

print(matrix_transpose)

transposed_matrix = list(zip(*matrix))
print(transposed_matrix)
