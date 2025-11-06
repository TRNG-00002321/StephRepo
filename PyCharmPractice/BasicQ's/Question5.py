#Given two numbers a and b, you need to swap their values so a holds the value of b
# and b holds the value of a. Just write the code to swap values of a and b at the specified place.

a = 3
b = 4

print("a: " + str(a) + "   b: " + str(b) + "\n")

temp = a
a = b
b = temp

print("a: " + str(a) + "   b: " + str(b) + "\n")
