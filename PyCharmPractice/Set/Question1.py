#You are given an array arr[] of size n. You have to insert all elements
# of arr[] into a set and return that set .You are also given an integer x.
# If x is found in set then erase it from set and print "erased x",
# otherwise, print "not found".


arr = [1,2,3,4,5,6,7,8,9,10]
x = 6

def setexclusion(vals,val):
    fin = set()
    outval = set()

    fin.update(vals)

    outval.add(val)
    fins = fin.difference(outval)
    if fins == fin:
        print("not found")
    else:
        print("erased x")
    print(fins)


setexclusion(arr,x)
