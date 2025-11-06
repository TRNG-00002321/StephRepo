#Write a file to copy another


with open("report.txt","rb") as f:
    with open("report2.txt","wb") as c:
        c.write(f.read())

with open("report2.txt","r") as f:
    data = f.read()
    print(data)












