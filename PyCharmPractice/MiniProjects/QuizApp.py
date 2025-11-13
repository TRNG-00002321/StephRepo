quiz = {
    1:{"What planet is known as the RED PLANET?":"Mars" },
    2:{"What is the largest ocean in the world?":"Pacific Ocean"},
    3:{"Who was the first President of the United States?":"George Washington"},
    4:{"What is the perimeter of a square with sides that are 5 inches long?\n\t(Write answer with units)":"20 inches"},
    5:{"Who wrote the book Harry Potter and the Sorcerer’s Stone?":"J.K. Rowling"},
    6:{"What is the fastest land animal?":"Cheetah"}
}

answers = {}
correctScore =0;
for key, value in quiz.items():
    for key2, value2 in value.items():
        #print(str(key) + ": " + str(key2))
        answers[key] = input(str(key) + ": " + str(key2) + " ")
        if answers[key].lower() == value2.lower():
            correctScore += 1





print("You got " + correctScore + " correct!")