import numpy as np

arr = np.array([1,2,3,4,5,6])
print(type(arr))


#1-D Array
arr = np.array([1,2,3,4,5,6])
print(arr)


#Dimensional Arrays
#Numpy uses the ndim attribute that returns an integer of how many dimensions an array has.
a=np.array([1,2,3,4,5,6])
b=np.array([[1,2,3,4,5,6],[1,2,3,4,5,6]])
c=np.array([[[1,2,3,4,5,6],[1,2,3,4,5,6]],[[1,2,3,4,5,6],[1,2,3,4,5,6]]])
print(a.ndim)
print(b.ndim)
print(c.ndim)


#Higher Dimensional Arrays
mdarr = np.array([1,2,3,4,5,6],ndmin=5)
print(mdarr)
print('number of dimensions:',mdarr.ndim)


#Numpy Array Indexing
#Negatives go from the end to the front (positives work the same as Java arrays

#Numpy Array Slicing
#Specify the starting index, the end index, and sometimes step
#[start:end] (end is exclusive)
#[start:end:step]

slarr = np.array([1,2,3,4,5,6,7,8,9,10,11,12])
print(slarr)
print(slarr[10:])
print(slarr[9:12])
print(slarr[:7])
print(slarr[2::3])

print(slarr[:-2])
print(slarr[-10:-2])
print(slarr[::-2])
print(slarr[-1::2])

#2-D Array Slicing

slarr2 = np.array([[1,2,3,4,5,6,7,8,9,10,11,12,13],[1,2,3,4,5,6,7,8,9,10,11,12,13]])
print(slarr2[1,1:4])


#Shape and Reshapping an Array
#the shape of n array is the number of elements in the dimension
#Shape() returns a tuple with each index having the number of elements in each dimension

#reshapping is changing the shape of the array, which can mean adding or removing
# dimensions or changing the number of elements in an array

#1-D to 2-D

slarr4 = slarr
newarr = slarr4.reshape(4,3)
print(newarr)



#1-D to 3-D

slarr3 = slarr
newarr = slarr3.reshape(2,3,2)
print(newarr)

#Flattening Arrays
#Converts a multidimensional array in 1-D (reshape(-1))

newarr2 = newarr.reshape(-1)
print(newarr2)


#Array Iterating
#for loops are used for going through the items of an array.
#You will need more nested loops the more dimensions that are available.

arr = np.array([[[1, 2, 3], [4, 5, 6]], [[7, 8, 9], [10, 11, 12]]])

for x in arr:
  for y in x:
    for z in y:
      print(z)



#the function nditer() is a helper function that can be used for a vast number of
# iterations. this allows for iterating through every element in an array no matter
# the dimensional size.

arr = np.array([[1, 2, 3, 4], [5, 6, 7, 8]])
for x in np.nditer(arr[:, ::2]):   #skips every other element
  print(x)


#Numpy Joining Array
# Joining is taking 2 arrays and outputting a new one. Numpy joins
# arrays using axes. the concactenate function is used to join a
# sequence of arrays together and also takes in axes (defaults 0).
# Implementation: SQL tables use joins by using a key

#Join 2 arrays
arr1 = np.array([1, 2, 3])
arr2 = np.array([4, 5, 6])
arr = np.concatenate((arr1, arr2))
print(arr)

#Join 2 2-D arrays along the rows axis (axis=1)
arr1 = np.array([[1, 2], [3, 4]])
arr2 = np.array([[5, 6], [7, 8]])
arr = np.concatenate((arr1, arr2), axis=1)
print(arr)


#Joining arrays using Stack functions

#Stacking is the same as concatenation but stacking is done on a new axis.
#this allows 2 1-D arrays to be stacked top to bottom.
#axis is defaulted to 0

arr1 = np.array([1, 2, 3])
arr2 = np.array([4, 5, 6])
arr = np.stack((arr1, arr2), axis=1)
print(arr)

#Stacking along rows
arr = np.hstack((arr1, arr2)) #horizontal
print(arr)

#stacks along columns
arr = np.vstack((arr1, arr2)) #vertical
print(arr)


#Array splitting
#array_split takes in an array (duh) and an int value to split that array into
#Ex- input is arr and 3 and output would be ([a],[r],[r]) (purely example)
 #There is also a horizontal and vertical split as well

arr = np.array([1, 2, 3, 4, 5, 6])
newarr = np.array_split(arr, 3)
print(newarr)


# Numpy Searching Arrays
# numpy provides tools that allow for searching in an array for a
# specific value and returns the index if it's found.

arr = np.array([1, 2, 3, 4, 5, 4, 4])
x = np.where(arr == 4)
print(x)
#OUTPUT
#The example above will return a tuple: (array([3, 5, 6],)
#Which means that the value 4 is present at index 3, 5, and 6.

#Search sorted is a method that goes through an array (usually ordered) and returns the index
# of where the desired value should be placed in order to maintain the search order
#this can either be done from the left or right side of an array
# (will be specified with the side parameter if right searching)

arr = np.array([6, 7, 8, 9])
x = np.searchsorted(arr, 7)
print(x)

arr = np.array([6, 7, 8, 9])
x = np.searchsorted(arr, 7, side='right')
print(x)


#Numpy sorting Arrays
#To sort something is to put elements into an ordered sequence. Anything can be ordered as
# long as their is a corresponding relationship to elements, like numeral incline or decline
# or sorting alphabetically.
# Numpy already has a method that sorts an array called sort() (obviously)

arr = np.array([3, 2, 0, 1])
print(np.sort(arr))
#this leaves the original unchanged and outputs a copy of the array.

#2-D example
arr = np.array([[3, 2, 4], [5, 0, 1]])
print(np.sort(arr))


#Numpy Filter Array
#Filtering is taking elements from an existing array and creating another one with those values.
#In Numpy, an array is filtered using a boolean index list which is a list of booleans that
# correspond to indexes in the array. if the value at an index at an index is true, then that
# elements get put into a new array with his friends. If the bool comes up false though, then
# that value is stuck in the old list.

arr = np.array([41, 42, 43, 44])
x = [True, False, True, False]   #this is the bool index list (BIL)
newarr = arr[x]    #this filters elements in arr using x
print(newarr)

arr = np.array([41, 42, 43, 44])

#This is to filter elements over 42 into a new list
filter_arr = []

for element in arr:    # go through each element in arr
  if element > 42:     # if the element is higher than 42, set the value to
    filter_arr.append(True)               # True, otherwise False:
  else:
    filter_arr.append(False)

newarr = arr[filter_arr]
print(filter_arr)
print(newarr)


#This creates a filtered list of only even elements
arr = np.array([1, 2, 3, 4, 5, 6, 7])
filter_arr = arr % 2 == 0
newarr = arr[filter_arr]
print(filter_arr)
print(newarr)