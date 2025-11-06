#Write script(s) to receive information from console input to create a dictionary,
# then append to a file. Then, be able to read from the file and search for relevant
# data to return. Start with name search then can go to any data search.
import json
import sys

user_data = {}

with open("user_data.json", 'w') as f:
    json.dump(user_data, f)

def update(topic, data):
    with open("user_data.json", 'r') as f:
        user_data = json.load(f)
        user_data.setdefault(topic, data)
        with open("user_data.json", 'w') as g:
            json.dump(user_data,g)


update("Fname",input("Please enter your first name: "))
update("Lname",input("Please enter your last name: "))
update("Age",input("Please enter your Age: "))
update("FavColor",input("Please enter your favorite color: "))







def search(topic):
    with open("user_data.json", 'r') as f:
        user_data = json.load(f)
        print(f"Your answer is: {user_data[topic]}")

options = {1: "First Name",2: "Last Name", 3: "Age", 4: "Favorite Color"}

for i,item in options.items():
    print(f"{i}. {item}")

try:
    choiced = int(input(f"What're you searching for? (please use numbers)\n"))
except ValueError:
    print(f"response is a string, please input the number of your choice.\n")
    sys.exit()

if choiced > len(options) or choiced < 1:
    print("Please enter a valid option")
    sys.exit()
if choiced == 1:
    search("Fname")
elif choiced == 2:
    search("Lname")
elif choiced == 3:
    search("Age")
elif choiced == 4:
    search("FavColor")








