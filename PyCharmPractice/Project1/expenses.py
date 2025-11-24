import datetime
import sqlite3

class Expenses:
    def __init__(self, filename: str = "expense.db"):
        self.filename = filename
        self.conn = sqlite3.connect(self.filename)
        self.conn.execute("PRAGMA foreign_keys=ON") #ties users to expenses made
        self._ensure_table()
        self.current_user_id = None


    def _ensure_table(self):
        self.conn.execute('''
                CREATE TABLE IF NOT EXISTS expenses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,
                    amount DOUBLE NOT NULL DEFAULT 0.0,
                    description TEXT,
                    clock TEXT NOT NULL,
                    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE)
                            ''')
        self.conn.execute('''
                CREATE TABLE IF NOT EXISTS approvals (
                    id Integer PRIMARY KEY AUTOINCREMENT,
                    expense_id INTEGER NOT NULL,
                    status Text NOT NULL Default "Pending",
                    reviewer_id INTEGER,
                    comment TEXT NOT NULL Default "",
                    clock TEXT,
                    FOREIGN KEY(expense_id) REFERENCES expenses(id) ON DELETE CASCADE)
                            ''')
        self.conn.commit()

    def set_current_user(self, user_id: int):
        self.current_user_id = int(user_id) if user_id is not None else None

    def close_db(self):
        self.conn.close()

    def create_expense(self, amount: float, description: str = ""):
        if amount < 0.0:
            amount = 0.0
            if description != "":
                description = description + " | Amount lower than zero, set to zero"
            else:
                description = "Amount lower than zero, set to zero"
        cursor = self.conn.cursor()
        cursor.execute("""
        INSERT INTO expenses (user_id, amount, description, clock) VALUES (?, ?, ?, ?)""",
                       (self.current_user_id, amount, description, datetime.datetime.today()))
        self.conn.commit()
        cursor.execute("""
                       INSERT INTO approvals (expense_id) VALUES (?,)""",
                       (cursor.lastrowid,))
        self.conn.commit()
        return cursor.lastrowid #returns last id added


    def read_expenses(self):
        """Return list of expenses as dicts with parsed types under specific user"""
        cursor = self.conn.cursor()
        cursor.execute("""
                    SELECT e.id, e.amount, e.description, e.clock,u.username, a.status, a.comment
                    FROM expenses e 
                    LEFT JOIN users u ON e.user_id = u.user_id
                    LEFT JOIN approvals a ON e.id = a.expense_id
                    WHERE u.user_id = ? ORDER BY e.id
                    """, (self.current_user_id,))
        rows = cursor.fetchall()
        expenses = []
        for row in rows:
                expenses.append({
                    "id": row[0],
                    "amount": float(row[1]),
                    "description": row[2],
                    "clock": row[3],
                    "username": row[4],
                    "status": row[5],
                    "comment": row[6]
                })
        return expenses

    def get_expense(self, expense_id: int):
        cursor = self.conn.cursor()
        cursor.execute("SELECT * FROM expenses WHERE id = ?", (int(expense_id),))
        row = cursor.fetchone()
        if not(row):
            return None
        else:
            return {
                "id": row[0],
                "amount": float(row[1]),
                "description": row[2],
                "clock": row[3]
            }

    def update_expense(self, expense_id: int, **kwargs):
        allowed = {"amount", "description"}
        updates = []
        params = []
        for key, val in kwargs.items():
            if key not in allowed:
                continue
            if key == "amount":
                val = float(val)
                if val < 0:
                    print("value cannot be negative, set to 0")
                    val = 0.0
            updates.append(f"{key} = ?")
            params.append(val)
        if not updates:
            return False
        updates.append(f"{"clock"} = ?")
        params.append(datetime.datetime.today())
        params.append(int(expense_id))
        sql = f"UPDATE expenses SET {', '.join(updates)} WHERE id = ?"
        cur = self.conn.cursor()
        cur.execute(sql, params)
        self.conn.commit()
        return cur.rowcount > 0

    def delete_expense(self, expense_id: int):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM expenses WHERE id = ?", (int(expense_id),))
        self.conn.commit()
        return cur.rowcount > 0


    def total_expenses(self):
        cursor = self.conn.cursor()
        cursor.execute("SELECT SUM(amount) FROM expenses")
        row= cursor.fetchone()
        return float(row[0]) if row else 0.0

    def total_expenses_by_description(self):
        cursor = self.conn.cursor()
        cursor.execute("SELECT description, SUM(amount) FROM expenses GROUP BY description")
        rows = cursor.fetchall()
        return {row[0]: float(row[1]) for row in rows}


