#2. Given three inputs that are stored in the variables a, b,
# and c. You need to print a a times and b b times  in a single
# line separated by c.

a = 3
b = 4
c = "b"

fin = ""
for i in range(a):
    fin = fin + str(a)

for j in range(b):
    fin = fin + str(b)

print(c.join(fin))