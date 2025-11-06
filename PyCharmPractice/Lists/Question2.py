#You are given a number k and a list arr[] that contains integers. You need to return
# list of numbers that are less than k.
#Example
#Input: arr[] = [54, 43, 2, 1, 5], k = 6
#Output: 2 1 5
#Explanation: 2 1 5 are less than 6.


arr = [54, 43, 2, 1, 7]
k = 50

arr2 = [x for x in arr if x < k]

print(arr2)




