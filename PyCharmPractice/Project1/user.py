import sqlite3
from utils.connectionmanager import get_conn

class Users:
    def __init__(self):
        self.current_user = None


    def new_user(self, username: str, password: str, role: str='Employee')->int:
        if len(username) < 8:
            print(f"Error: Username '{username}' was not provided.")
            return -1
        if len(password) <5:
            print(f"Error: Password '{password}' is too short.")
            return -1

        conn = get_conn()
        cursor = conn.cursor()
        try:
            cursor.execute("INSERT INTO users (username, password,role) VALUES (%s, %s, %s)", (username, password,role))
            conn.commit()
            print(f"user '{username}' registered successfully.")
            return cursor.lastrowid #returns last row id added
        except sqlite3.IntegrityError as e:
            if 'UNIQUE constraint failed' in str(e):
                print(f"Error: Username '{username}' already exists.")
            else:
                # Re-raise other types of IntegrityErrors
                raise e
            return -1


    def login_user(self, username: str, password: str):
        if len(username) == 0:
            print(f"Error: Username '{username}' was not provided.")
            return -1
        conn = get_conn()
        cursor = conn.cursor()

        cursor.execute("SELECT * FROM users WHERE username = %s", (username,))
        user_entry = cursor.fetchone()

        if user_entry is None:
            print(f"Error: Username '{username}' was not found.")
            return -1

        uid,uname,upassword,urole = user_entry
        if upassword != password:
            print(f"Error: Password '{password}' is incorrect.")
            return -1
        self.current_user = {"id": uid, "username": uname, "role": urole}
        return self.current_user

    def delete_user(self, username: str):
        if len(username) == 0:
            print(f"Error: Username '{username}' was not provided.")
            return -1

        conn = get_conn()
        cursor = conn.cursor()
        user_entry = cursor.execute("SELECT * FROM users WHERE username = ?", (username,)).fetchone()
        if user_entry is None:
            print(f"Error: Username '{username}' was not found.")
            return -1
        else:
            cursor.execute("DELETE FROM users WHERE username = ?", (username,)).fetchone()
            conn.commit()
            print(f"user '{username}' deleted successfully.")
            return 0

    def get_current_user(self):
        return self.current_user

    def logout(self):
        if self.current_user:
            print(f"User '{self.current_user["username"]}' logged out.")
        self.current_user = None
