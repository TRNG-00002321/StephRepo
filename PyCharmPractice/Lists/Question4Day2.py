#Create 2 lists and make one names and the other ages.
#Combine those 2 lists into tuples

arr = ["bob", "nancy", "alice"]
arr2 = [23, 34]

extra = ["cat","dog","lizard"]
mix = zip(arr, arr2) #combines two lists together into a tuple

mix2 = zip(mix, extra)
print(list(mix))
print(list(mix2))

'''
Unzipping
You get however many lists is in the tuple

zippeddata = mix
arr,arr2 = zip(*zippeddata)

'''

