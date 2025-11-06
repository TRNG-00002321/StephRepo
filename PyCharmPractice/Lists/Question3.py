#You are given a list arr that contains integers. You need to return
# average of the non negative integers.
#Examples:
#Input: arr = [-12, 8, -7, 6, 12, -9, 14]
#Output: avg = 10.0
#Explanation: The non negative numbers are 8 6 12 14. The sum is 8+6+12+14 = 40, Average = 40/4 = 10.0

arr = [-12, 8, -7, 6, 22, -9, 24]
arr2 = [x for x in arr if x > 0]
avg = sum(arr2)/len(arr2)

print(avg)