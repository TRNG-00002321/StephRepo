import datetime
import sqlite3

DEFAULT_FILE = "expenses.db"

class Expenses:
    def __init__(self, filename: str = DEFAULT_FILE):
        self.filename = filename
        self.conn = sqlite3.connect(self.filename)
        self.conn.execute("PRAGMA foreign_keys=ON") #ties users to expenses made
        self._ensure_table()


    def _ensure_table(self):
        self.conn.execute('''
                CREATE TABLE IF NOT EXISTS expenses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    amount REAL not NULL DEFAULT 0.0,
                    description TEXT,
                    date TEXT not NULL)
                            ''')
        self.conn.commit()

    def close_db(self):
        self.conn.close()

    def create_expense(self, amount: float, description: str = "")->int:
        if amount < 0.0:
            amount = 0.0
            if description:
                description = description + " | Amount lower than zero, set to zero"
            else:
                description = "Amount lower than zero, set to zero"
        cursor = self.conn.cursor()
        cursor.execute("""
        INSERT INTO expenses (amount, description, date) VALUES (?, ?, ?)""",
                       (amount, description, datetime.datetime.today()))
        self.conn.commit()
        return cursor.lastrowid #returns last id added


    def read_expenses(self):
        """Return list of expenses as dicts with parsed types"""
        cursor = self.conn.cursor()
        cursor.execute("SELECT id,amount,description,date FROM expenses ORDER BY id")
        rows = cursor.fetchall()
        expenses = []
        for row in rows:
                expenses.append({
                    "id": row[0],
                    "amount": float(row[1]),
                    "description": row[2],
                    "date": row[3]
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
                "date": row[3]
            }

    def update_expense(self, expense_id: int, **kwargs):
        allowed = {"amount", "description"}
        updates = []
        params = []
        for key, val in kwargs.items():
            if key not in allowed:
                continue
            # if key == "date" and isinstance(val, datetime.date):
            #     val = val.isoformat()
            if key == "amount":
                val = float(val)
                if val < 0:
                    val = 0.0
            updates.append(f"{key} = ?")
            params.append(val)
        if not updates:
            return False
        updates.append(f"{"date"} = ?")
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


