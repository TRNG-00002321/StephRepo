"""
To-Do List Manager (JSON)
Functions:
- add_task(file, title, note=None) *Create*
- view_list(file)                  *Read*
- mark_complete(file, task_id)     *Update*
- delete_task(file, task_id)       *Delete*
- dictionary uses key pairs with ids for each task
- safe file creation & error handling
"""

import json
import os

DEFAULT_FILE = "to-do.json"

def file_exists(file):
    if not os.path.exists(file):
        with open(file, "w") as f:
            json.dump({}, f)

def read(file=DEFAULT_FILE):
    file_exists(file)
    with open(file, "r") as f:
        try:
            data = json.load(f)
            if not isinstance(data, dict):
                # replace invalid content with empty dict
                return {}
            return data
        except json.JSONDecodeError:
            return {}


def update(data, file=DEFAULT_FILE):
    with open(file, "w") as f:
        json.dump(data, f, indent=4, sort_keys=True)


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
    tasks = read(file)
    nid = _next_id(tasks)
    tasks[nid] = {
        "title": title,
        "note": note or "", #if there is or isn't notes for task
        "completed": False,
    }
    update(tasks, file)
    return nid


def view_tasks(file=DEFAULT_FILE, show_completed=True):
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


def mark_complete(file, task_id):
    tasks = read(file)
    if task_id not in tasks:
        raise KeyError(f"Task id {task_id} not found")
    tasks[task_id]["completed"] = True
    update(tasks, file)
    return tasks[task_id]


def delete(file, task_id):
    tasks = read(file)
    if task_id not in tasks:
        raise KeyError(f"Task id {task_id} not found")
    del tasks[task_id]
    update(tasks, file)
    return True


# --- Simple CLI to play with the manager ---
def _print_tasks(lines):
    if not lines:
        print("No tasks found.")
        return
    for t in lines:
        status = "x" if t["completed"] else " "
        print(f"[{status}] {t['id']}: {t['title']}")
        if t["note"]:
            print(f"     note: {t['note']}")


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

