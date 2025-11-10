from flask import Flask, redirect, url_for

app = Flask(__name__)
@app.route('/admin/<admin>')
def admin(admin):
    return 'Welcome Admin! %s!'%admin.upper()
@app.route('/guest/<name>')
def guest(name):
    return 'Welcome Guest! %s!'%name.upper()
@app.route('/user/<name>')
def welcome(name):
    if name == 'admin':
        return redirect(url_for('admin', admin = name))
    else:
        return redirect(url_for('guest', name = name))



if __name__ == '__main__':
    app.run(debug=True)