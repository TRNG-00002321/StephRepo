#You need to perform three separate tasks based on the given input:
#String Input and Print: Take a text input as a string and print it as it is.
#Integer Input and Add: Take an integer input n, add 10 to it, and print the result.
#Float Input and Multiply: Take a floating-point number as input, multiply it by 10, and print the result.

def userinput(ans):
    if isinstance(ans, int):
        return ans + 10
    elif isinstance(ans, float):
        return ans * 10
    else:
        return ans


print(userinput(12))
print(userinput(1.2))
print(userinput("howdy"))

#isinstance - check to see if the value passed in matches the desired data type
