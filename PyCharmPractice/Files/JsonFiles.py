import json

#data is written in the format that a dictionary or list would use
my_data = {
    "name": "Stephenie",
    "age": 20,
    "City": "Plano",
    "isStudent": True,
    "courses": [
        "Mathematics", "Science"
    ]
}

#write method will overwrite the previous json materials
with open("me.json", 'w') as f:
    json.dump(my_data, f, indent=4) #indent 4 make it readable for people

with open("me.json", 'r') as f:
    data = json.load(f)            #gets pulled from the file and put into a dictionary format for Python
    print(data)


    #write some data and put it into a json file (user input only)







