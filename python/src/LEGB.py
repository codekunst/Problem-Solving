""" global/local변수
  1. Local에서 Global 변수를 재할당 할 수 없다.
    재할당 하려면 global 키워드 사용
  2. Local에서 Global 변수를 참조할 수 있다.
  3. L - E - G - B
    참조에는 우선순위가 있다. 
    Local - Enclosed - Global - Builtin
  4. Enclosed는 nonlocal 키워드를 사용해서 재할당한다.
"""

""" 재귀함수
  1. 자기 자신으로 정의되는 함수
  2. 종료조건이 반드시 필요
"""

a = 10


def parent_func():
    a = 5

    def child_func():
        nonlocal a
        a += 3

    child_func()
    print(a)


parent_func()
