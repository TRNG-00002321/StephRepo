#Here two integers a and b are given. The given input and its values are passed as arguments
# to the function argumentFunction. The argumentFunction is responsible to return (a+b).
# You need to write this function.

def argumentFunction(a,b):
    if isinstance(a,str) or isinstance(b,str):
        print("a is a string or b is a string")
        pass
    else: return a+b



print(argumentFunction(4,2))