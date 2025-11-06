class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print(f"{self.name} makes noise")

class Dog(Animal):
    def __init__(self, name, color):
        super().__init__(name)
        self.color = color

    def describe(self):
        print(f"{self.name} is a {self.color} dog")


dog = Dog("Dog", color="red")
dog.describe()
