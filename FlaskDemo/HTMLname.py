from flask import Flask, render_template
app = Flask(__name__)
@app.route('/hello/<name>')
def hello(name):
    return render_template('helloname.html',name=name)


@app.route('/marks/<int:score>')
def hello_world(score):
    return render_template('scores.html',score=score)

if __name__ == '__main__':
    app.run(debug=True)