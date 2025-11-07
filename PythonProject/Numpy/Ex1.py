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

slarr = np.array([1,2,3,4,5,6,7,8,9,10,11,12,13])
print(slarr)
print(slarr[10:])
print(slarr[9:12])
print(slarr[:7])
print(slarr[2::3])


















