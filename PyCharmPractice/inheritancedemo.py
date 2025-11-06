class Animal:
    def speak(self):
        print("Animal sounds")

class Dog(Animal):
    def speak(self):
        print("Dog sounds")

d= Dog()
d.speak()
