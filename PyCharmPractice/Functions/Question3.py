#Given a number n, find the first digit of the number.

def first(num1):
    if isinstance(num1,str):
        print("this is a string")
        pass
    else:
        print(str(num1)[:1])


first(8765434543)