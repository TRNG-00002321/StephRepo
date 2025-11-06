'''<strong>Coding Assignment</strong>
<strong></strong>
<strong>If, Else, and Elif Problems:</strong>

For this problem you are going to make a program that simulates the output of a vending machine that only takes in
coins of American currency.
1.Your program should take an integer as an input from the user (either a 1, 2, or 3) that corresponds with an option
for a drink from the vending machine outlined below and assign the corresponding price to a variable as a float.
Use your knowledge of if, elif, and else statements to complete this part of the problem. Your code should
have an else statement that prints a message and ends the program using sys.exit() if the user does not enter a valid
input number.
Vending Machine:
1.water = $1.00
2.cola = $1.50
3.gatorade = $2.00
2.After placing an order, the user should be prompted to enter inputs 4 times:
1.The first time, the user should be prompted to enter the number of quarters they put in the machine. Assign this
number to a variable as an integer.
2.The second time, the user should be prompted to enter the number of dimes they put in the machine. Assign this
number to a variable as an integer.
3.The third time, the user should be prompted to enter the number of nickles they put in the machine. Assign this
number to a variable as an integer.
4.The fourth time, the user should be prompted to enter the number of pennies they put in the machine. Assign this
number to a variable as an integer.
3.Create a variable to hold the total value of all the coins the user has put into the machine.
4.Use flow control statements to print the user's change or output a message asking the user to try again depending
on whether the total value of the coins the user has put into the machine is enough to pay for the item they ordered.
New knowledge for this problem:
1.%f format specifier
2.import sys and sys.exit()
3.int()'''
import sys

from jaraco.functools import except_

options = {1:("Water",1.00),2: ("Cola",1.50), 3: ("Gatorade",2.00)}

for i, (item,value) in options.items():
    print(f"{i}. {item}: ${value:.2f}")
try:
    choiced = int(input(f"What drink would you like? (please use numbers)\n"))
except ValueError:
    print(f"response is a string, please input the number of your choice\n")
    sys.exit()

if int(choiced) > len(options):
    print("Please enter a valid option")
    sys.exit()

def properinput(coin):
    try:
        n = int(input(f"Please enter the number of {coin} to use: "))
        if n < 0:
            print(f"Please enter a positive number of {coin} to use.")
            sys.exit()
        return n
    except ValueError:
        print("Invalid input: please enter a whole number.")
        sys.exit()

q = 25 * properinput("Quarters")
d = 10 * properinput("Dimes")
n = 5 * properinput("Nickels")
p = properinput("Pennies")

item, value = options[choiced]

totalinput = (q+d+n+p)/100

if value > totalinput:
    print(f"Not enough money given. try again later with ${value-totalinput:.2f} more in funds.")
    sys.exit()
elif value < totalinput:
    print(f"You just bought {item} and have some change!: ${totalinput-value:.2f}")
    sys.exit()
else:
    print(f"You just bought {item}!")
    sys.exit()

















