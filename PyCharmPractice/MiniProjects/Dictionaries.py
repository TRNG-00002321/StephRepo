#1. Create a dictionary of five countries and their capitals. Write a function that takes a
# country name as input and returns its capital.
from functools import reduce

coca = {"Greece":"Athens","Italy":"Rome","France":"Paris","Ireland":"Dublin","Japan":"Tokyo"}

def capital(country):
    try:
        capitals = coca[country]
        return capitals
    except KeyError:
        print("The country does not exist in the dictionary")
print(capital("Italy"))
print(capital("China"))


#2. Make a dictionary of student names and their scores. Write a function to find the
# student with the highest score.

students = {"Bob": 75, "Alice": 86, "Charlie": 24}
print(max(students))



#3. Create a nested dictionary of three employees, each with keys for name, age, and salary.
# Write a function to give each employee a 10% raise and print the updated dictionary.

Employees = {
    1:{
        "name":"Bob",
        "Age":75,
        "Salary (k)":10},
    2:{
        "name":"Charlie",
        "Age":24,
        "Salary (k)":80},
    3:{
        "name":"Alice",
        "Age":58,
        "Salary (k)":65}}
def raises(person):
    person["Salary (k)"] *= 1.10

for employee in Employees.values():
    raises(employee)


print(Employees)

'''4. Write a Python program to add a key to a dictionary
Sample Output
dictionary = {"Name" : "Ram" , "Age" : 23}
add_key = {"City" : "Salem"}
dictionary = {'Name' : 'Ram', 'Age' : 23, 'City' : 'Salem'}'''

dictionary = {'Name' : 'Ram', 'Age' : 23, 'City' : 'Salem'}
dictionary["favColor"]="Red"
print(dictionary)


'''5. Write a Python program to concatenate following dictionaries to create a new one.
Sample Output
Dictionary 1 = {"Name" : "Ram" , "Age" : 23}
Dictionary 2 = {"City" : "Salem", "Gender" : "Male"}
Concatenate Dictionaries = {'Name' : 'Ram', 'Age' : 23, 'City' : 'Salem', 'Gender': 'Male'}'''

Dictionary1 = {"Name" : "Ram" , "Age" : 23}
Dictionary2 = {"City" : "Salem", "Gender" : "Male"}

Dictionary3 = Dictionary1.copy()
Dictionary3.update(Dictionary2)
print(Dictionary3)

'''6. Write a Python program to check whether a given key already exists in a dictionary.
Sample Output
{'Name' : 'Ram', 'Age' : 23,}
Key = Name
Key is Available in the Dictionary'''
Key = "Nam"
for item in Dictionary3:
    if item.find(Key) != -1:
        print("True")
        break
    else:
        print("False")

'''75. Write a Python program to iterate over dictionaries using for loops.
Sample Output
{"Name" : "Ram" , "Age" : 23 , "City" : "Salem", "Gender" : "Male"}
Name : Ram
Age : 23
City : Salem
Gender : Male'''

dictvals = {"Name" : "Ram" , "Age" : 23 , "City" : "Salem", "Gender" : "Male"}

for item,value in dictvals.items():
    print(f"{item}:{value}")



