#Create a list of numbers and print their squares

arr = [1,2,3,4,5,6]

for i in arr:
    print(i**2)

'''can also use list(map(lamda x:x**2, arr)) in place of i**2
this method would be faster to run'''