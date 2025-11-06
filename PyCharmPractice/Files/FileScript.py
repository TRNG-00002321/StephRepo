'''
R is for read mode
W is for creating or overriding mode
A is for append mode
X is for making a new file
B for binary mode
T for text mode
'''
# file = open('test.txt', 'r')
# #contents = file.readline()
# #contents2 = file.readlines()
# # for line in file:
# #     print(line.strip())
#
# while chunk := file.read(100):
#     print(chunk)
# file.close()


#with open("Test.txt","r") as file: \n content = file.read() \n print(content)
'''
readline() : Reads one line at a time (this includes the new line character)
readLines() : Reads all the lines into a list


'''
import os
path = os.path.join("Test.txt")
with open(path, 'r') as f:
    print(f.read())


with open("Test.txt", 'w') as f:
    f.write('Hello World\n')
    f.write('Hello World part 2: Electric Boogaloo\n')



# try:
#     with open("XFiles.txt", 'x') as f:
#         f.write('Welcome to the Matrix\n')
# except FileNotFoundError:
#

lines = ["Line1\n", "Line2\n", "Line3\n"]
with open("MultiLines.txt", 'w') as f:
    f.write('\n'.join(lines))

name = "Alice"
score = 95

with open("report.txt", 'w') as f:
    f.write(f"Student: {name}\nScore: {score}\n")



#Binary Files
#Use wb mode to write binary files
data = bytes([120,3,255,0,100])
with open("binary.dat", 'wb') as f:
    f.write(data)




#Write a file to copy another and do another to copy an image to an image file
