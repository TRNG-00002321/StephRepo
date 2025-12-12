#reads a text file and returns a list of numbers
def read(file_name):
    with open(file_name) as f:
        lines = f.readlines()
        return [float(lines.strip()) for lines in lines]

#returns the sum of all elements in the list from a text file
def calculate_total(file_name):
    numbers=read(file_name)
    return sum(numbers)