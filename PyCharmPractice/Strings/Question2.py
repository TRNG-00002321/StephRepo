#Given a string s, you need to check if it is palindrome or not.
# A palidrome is a string that reads the same from front and back.

s = "Moewdy Pardner"
s2 = s[::-1]

if s2 == s:
    print("yes, it's a palindrome")
else:
    print("no, it's not a palindrome")


