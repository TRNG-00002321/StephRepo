from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def Shape(self):
        pass
    @abstractmethod
    def Area(self):
        pass

class Circle(Shape):
    def __init__(self, radius=1):
        self.radius = radius

    def Shape(self):
         return 'Circle'

    def Area(self):
        return self.radius**2*3.14

class Rectangle(Shape):
    def __init__(self, width=1, height=1):
        self.width = width
        self.height = height

    def Shape(self):
         return 'Rectangle'

    def Area(self):
        return self.width*self.height

class Triangle(Shape):
    def __init__(self,base=1,height=1):
        self.base = base
        self.height = height

    def Shape(self):
         return 'Triangle'

    def Area(self, ):
        return 0.5*self.base*self.height

circle = Circle(4)
print(circle.Shape())
print(circle.Area())


rectangle = Rectangle(4,5)
print(rectangle.Shape())
print(rectangle.Area())


triangle = Triangle(4,6)
print(triangle.Shape())
print(triangle.Area())


"""This is an abstract class"""

# shape = Shape()
# print(shape.Shape())
'''Create a class shape with a method that returns the area 
Inherit the shape class to circle, rectangle, and triangle classes
Calculate the area and print the result
return not print'''