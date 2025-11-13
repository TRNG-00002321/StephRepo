import json
from flask import Flask, render_template

app = Flask(__name__)


def view_tasks(file='projects.json', show_completed=True):
    with open(file, "r") as f:
        data = json.load(f)
    # sort by id
    def keyfn(item):
        try:
            return int(item[0])
        except Exception:
            return item[0]

    items = sorted(data.items(), key=keyfn)
    lines = []
    for nid, t in items:
        # if not show_completed and t.get("completed"):
        #     continue
        lines.append(
            {
                "id": nid,
                "title": t.get("title"),
                "note": t.get("note"),
                "completed": t.get("completed"),
            }
        )
    return lines





@app.route('/')
def home():
    return render_template('Home.html')

@app.route('/projects')
def projects():
    tasks = view_tasks()
    return render_template('Projects.html', tasks=tasks)

@app.route('/contact')
def contact():
    return render_template('Contact.html')



if __name__ == '__main__':
    app.run(debug=True)