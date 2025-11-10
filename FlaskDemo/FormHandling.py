from flask import Flask, render_template, request, redirect, url_for
app = Flask(__name__)

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['nm']
        if username.lower() == 'pardner':
            return redirect('https://www.instagram.com/cats_and_cowboy_hats/p/DQZYkoykT1X/')
        else:
            return 'Meowdy %s!' % username
    else:
        username = request.args.get('nm')
        return 'Meowdy %s!' % username


if __name__ == '__main__':
    app.run(debug = True)
