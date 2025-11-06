#create a list 1-10 then display numbers reduced
from functools import reduce

arr = [1,2,3,4,5,6,7,8,9,10]
arr2 = reduce(lambda x,y: x+y, arr)
print(arr2)

#lambda goes through every element and applies a function
#reduce applies a function of 2 arguments cumulatively to the items of an iterable from left to
#right, reducing the iterable to a single value, it requires the import functools to use.