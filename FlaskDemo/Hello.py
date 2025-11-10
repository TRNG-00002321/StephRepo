from flask import Flask
import math
app = Flask(__name__) #Flask constructor

@app.route('/')
def hello_world():
    return "Hello, World!"

@app.route('/hello')
def hello_again():
    return "Hello, Taco Cat, what is your Query?"

def howdy():
    return 'Meowdy'

@app.route('/hello/<name>')
def meowdy_name(name):
    return 'Meowdy %s!' % name

@app.route('/square/<int:x>')
def square(x):
    return str(x**2)

@app.route('/carea/<float:x>')
def c_area(x):
    return str(x**2 * math.pi)
@app.route('/add/<int:x>/<int:y>')
def add(x,y):
    return str(x+y)
app.add_url_rule('/Howdy', view_func=howdy)

if __name__ == '__main__':
    app.run(debug=True)