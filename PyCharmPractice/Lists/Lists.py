#create a list of 5 elements then make a new list containing elements only including letter A in first list

list1 = ["Apple","Banana","Orange","Kiwi","Blueberry"]
list2 = [x for x in list1 if "a" in x.lower()]
# for item in list1:
#     if item.__contains__("a"):
#         list2.append(item)

print(list2)

#input 2 numbers, calculate and display their division

def divide(num1,num2):
    if isinstance(num1,str) or isinstance(num2,str):
        print("num1 is a string or num2 is a string")
        pass
    elif num2 == 0:
        print("num2 is zero, Undefined")
        pass
    else:
        print(num1/num2)         #print(int(num1/num2))   integer version



divide(5,2)