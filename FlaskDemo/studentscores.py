from flask import Flask, render_template
app = Flask(__name__)

@app.route("/grades")
def grades():
    class_grades = {
        "Math": [85, 86, 87, 88, 89, 90, 91, 92],
        "Physics": [78, 79, 80, 81, 82, 83, 84],
        "Chemistry": [92, 93, 94, 95, 96, 97, 96],
    }
    return render_template("scores.html", class_grades=class_grades)

if __name__ == '__main__':
    app.run(debug=True)


