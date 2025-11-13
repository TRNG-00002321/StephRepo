"""
To-Do List Manager (JSON) Flask made
Functions:
- add_task(file, title, note=None) *Create*
- view_list(file)                  *Read*
- mark_complete(file, task_id)     *Update*
- delete_task(file, task_id)       *Delete*
- dictionary uses key pairs with ids for each task
- safe file creation & error handling
"""
from flask import Flask, redirect, request, render_template, url_for
import json
import os

app = Flask(__name__)
DEFAULT_FILE = "to-do.json"
def _ensure_path(path):
    if not isinstance(path, (str, bytes, os.PathLike)):
        raise TypeError(
            f"Expected a file path (str/bytes/os.PathLike), got {type(path).__name__}. "
            "Check the order of arguments when calling create/read/update."
        )
    return path
def file_exists(file):
    # Add this debug line at the top of any function that receives `file`
    print("DEBUG: file param type:", type(file), "value:", repr(file))
    _ensure_path(file)

    if not os.path.exists(file):
        with open(file, "w") as f:
            json.dump({}, f)

def _next_id(tasks):
    """Returns the next highest key id. Empty dictionaries start at 1."""
    key_ids = []
    for k in tasks.keys():
        try:
            key_ids.append(int(k))
        except Exception:
            continue
    return str(max(key_ids) + 1) if key_ids else "1"


def create(file, title, note=None):
    _ensure_path(file)
    tasks = read(file)
    nid = _next_id(tasks)
    tasks[nid] = {
        "title": title,
        "note": note or "", #if there is or isn't notes for task
        "completed": False,
    }
    update(file,tasks)
    return nid


def read(file=DEFAULT_FILE):
    _ensure_path(file)
    file_exists(file)
    #Read the json file
    with open(file, "r") as f:
        try:
            data = json.load(f)
            if not isinstance(data, dict):
                # replace invalid content with empty dict
                return {}
            return data
        except json.JSONDecodeError:
            return {}

def view_tasks(file=DEFAULT_FILE, show_completed=True):
    _ensure_path(file)
    tasks = read(file)
    # sort by id
    def keyfn(item):
        try:
            return int(item[0])
        except Exception:
            return item[0]

    items = sorted(tasks.items(), key=keyfn)
    lines = []
    for nid, t in items:
        if not show_completed and t.get("completed"):
            continue
        lines.append(
            {
                "id": nid,
                "title": t.get("title"),
                "note": t.get("note"),
                "completed": t.get("completed"),
            }
        )
    return lines


def update(file, data):
    _ensure_path(file)
    if isinstance(data, dict):
        with open(file, "w") as f:
            json.dump(data, f, indent=4, sort_keys=True)
    else:
        tasks = read(file)
        if data not in tasks:
            raise KeyError(f"Task id {data} not found")
        tasks[data]["completed"] = True
        return tasks[data]


def delete(file, task_id):
    _ensure_path()
    tasks = read(file)
    if task_id not in tasks:
        raise KeyError(f"Task id {task_id} not found")
    del tasks[task_id]
    update(tasks, file)
    return True

'''
file = DEFAULT_FILE
print("Simple To-Do List "
      "\nCommands: "
      "\nAdd, List, Done, Delete, Quit")
while True:
    cmd = input("> ").strip()
    if not cmd:
        continue
    parts = cmd.split(maxsplit=1)
    action = parts[0].lower()
    arg = parts[1] if len(parts) > 1 else ""

    try:
        if action == "add":
                # usage: add Title | optional note after '|' e.g. add Buy milk | 2 liters
            if not arg:
                print("Usage: add <title>  or: add <title> | <note>")
                continue
            if "|" in arg:
                title, note = [s.strip() for s in arg.split("|", 1)]
            else:
                title, note = arg, ""
            nid = create(file, title, note)
            print(f"Added task id {nid}.")
        elif action == "list":
            tasks = view_tasks(file, show_completed=True)
            _print_tasks(tasks)
        elif action == "done":
            # usage: done <id>
            if not arg:
                print("Usage: done <task_id>")
                continue
            mark_complete(file, arg)
            print(f"Marked {arg} complete.")
        elif action == "delete":
            # usage: delete <id>
            if not arg:
                print("Usage: delete <task_id>")
                continue
            delete(file, arg)
            print(f"Deleted {arg}.")
        elif action in ("quit", "exit"):
               break
        else:
            print("Unknown command. Commands: Add, List, Done, Delete, Quit")
    except KeyError as e:
        print("Error:", e)
    except Exception as e:
        print("Unexpected error:", e)

'''
@app.route("/cats")
def index():
    return "tacocat"

@app.route("/")
def home():
    tasks = view_tasks(show_completed=True)  # get your task list
    return render_template("To-Do.html", tasks=tasks)
    return "This is the home page."

@app.route("/create")
def create_screne():
    return render_template("Create.html")

@app.route("/add", methods=["GET", "POST"])
def add_task():
    if request.method == "POST":
        title = request.form["title"]
        note = request.form.get("note", "")  # safer access

        if note == "":
            nid = create(DEFAULT_FILE, title)
        else:
            nid = create(DEFAULT_FILE, title, note)

        print(f"Added task id {nid}.")
        return redirect(url_for("home"))  # reloads home with updated tasks

    # If user visits /add manually (GET request)
    return redirect(url_for("create_task"))




if __name__ == "__main__":
    app.run(debug=True)