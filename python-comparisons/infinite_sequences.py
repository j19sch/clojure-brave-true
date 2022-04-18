"""
Clojure
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))


(take 10 (even-numbers))
; => (0 2 4 6 8 10 12 14 16 18)
"""

# https://stackoverflow.com/questions/5234090/how-to-take-the-first-n-items-from-a-generator-or-list
# https://stackoverflow.com/questions/51858765/generating-an-infinite-list

# https://docs.python.org/3/library/itertools.html#itertools.islice
# https://docs.python.org/3/reference/simple_stmts.html#the-yield-statement
# https://docs.python.org/3/glossary.html#term-generator
# https://docs.python.org/3/glossary.html#term-generator-iterator
# https://docs.python.org/3/glossary.html#term-iterator

import itertools

# needs generator function
# https://stackoverflow.com/a/51859030
# https://stackoverflow.com/questions/51858765/generating-an-infinite-list

def even_numbers(numb=None):
  numb = 0 if numb is None else numb

  while True:
    yield numb
    numb = numb + 2

evens = even_numbers()
print(type(evens))  # generator

top10 = itertools.islice(evens, 10)
print(type(top10))  # itertools.islice
print(list(top10))

also = itertools.islice(even_numbers(), 10)

for _ in also:
  print(_)

for _ in even_numbers():
  if _ > 18:
    break
  else:
    print(_)

for index, number in enumerate(even_numbers()):
  if index > 10:
    break
  else:
    print(number)


def more_even_numbers(numb=None):
  numb = 0 if numb is None else numb
  yield numb

  numb = numb + 2
  print(numb)
  more_even_numbers(numb)

more_evens = more_even_numbers()
more_top10 = itertools.islice(more_evens, 10)
print(type(more_top10))  # itertools.islice
print(f"length {len(list(more_top10))}")

for _ in more_top10:
  print(_)  # returns only 0


def moar_even_numbers(numb=None):
  numb = 0 if numb is None else numb
  yield moar_even_numbers(numb + 2)  # return will hit RecursionError: maximum recursion depth exceeded

moar_10 = itertools.islice(moar_even_numbers(), 10)
print(f"length {len(list(moar_10))}")
