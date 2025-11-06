s = " Hello World "

#Case Manipulation methods
#lower - convert all letters in a string to lowercase format
#upper - convert all letters in a string to uppercase format

# Whitespace and Formatting methods (can include specified characters too)
#strip - returns a copy of the input string minus any white space before or after it
#lstrip - strips the white space only from the beginning of the input string (returns copy)
#rstrip - strips the white space only from the end of the input string (returns copy)
#join - Concatenates N amount of strings together through iteration, uses
#       the join call as a seperator
#split - Splits a string based of a Seperator value, you can set the max
#        number of splits if needed with Maxsplit

#Search and Replacement methods
#find - finds the first instance of the substring and returns the index, you can
#       specify start range with start and end, returns -1 if not found
#replace - returns a new string with old substrings replaced with a new substring. Can limit
#          the number of replacements if needed.
#index - finds the first instance of the substring and returns the index, and can also
#        specify range but returns an error if nothing found.

#1. Given two inputs that are stored in variables a and n,
# you need to print a, n times in a single line without space
# between them. Output must have a newline at the end.

a = "b"
n = 10

for i in range(n):
    a = a + "b"

print("1)" + a)







