import datetime

from utils.connectionmanager import get_conn

class Expenses:
    def __init__(self, filename: str = "expense.db"):
        self.current_user_id = None


    def set_current_user(self, user_id: int):
        self.current_user_id = int(user_id) if user_id is not None else None


    def create_expense(self, amount: float, description: str = ""):
        if amount < 0.0:
            amount = 0.0
            if description != "":
                description = description + " | Amount lower than zero, set to zero"
            else:
                description = "Amount lower than zero, set to zero"


        conn = get_conn()
        try:
            cur = conn.cur()
            cur.execute("""
            INSERT INTO expenses (user_id, amount, description, clock) VALUES (?, ?, ?, ?)""",
                           (self.current_user_id, amount, description, datetime.datetime.now()))
            self.conn.commit()
            expense_id = cur.lastrowid

            cur.execute("""INSERT INTO approvals (expense_id) VALUES (?)""",(expense_id,))
            self.conn.commit()

            return expense_id #returns last id added
        except Exception as e:
            print(e)
            conn.rollback()
            raise
        finally:
            cur.close()
            conn.close()


    def read_expenses(self):
        """Return list of expenses as dicts with parsed types under specific user"""
        conn = get_conn()
        try:
            cur = conn.cur()
            cur.execute("""
                        SELECT e.id, e.amount, e.description, e.clock,u.username, a.status, a.comment
                        FROM expenses e 
                        LEFT JOIN users u ON e.user_id = u.user_id
                        LEFT JOIN approvals a ON e.id = a.expense_id
                        WHERE u.user_id = %s ORDER BY e.id
                        """, (self.current_user_id,))
            rows = cur.fetchall()
            expenses = []
            for row in rows:
                    expenses.append({
                        "id": row[0],
                        "amount": float(row[1]),
                        "description": row[2],
                        "date": row[3].isoformat(),
                        "username": row[4],
                        "status": row[5],
                        "comment": row[6]
                    })
            return expenses
        finally:
            cur.close()
            conn.close()


    def get_expense(self, expense_id: int):
        conn = get_conn()
        try:
            cur = conn.cur()
            cur.execute("SELECT * FROM expenses WHERE id = ?", (int(expense_id),))
            row = cur.fetchone()

            if not(row):
                return None
            if row[1] != self.current_user_id:
                return None
            else:
                return {
                    "id": row[0],
                    "amount": float(row[2]),
                    "description": row[3],
                    "clock": row[4].isoformat()
                }
        finally:
            cur.close()
            conn.close()


    def update_expense(self, expense_id: int, **kwargs):
        conn = get_conn()
        try:
            cur = conn.cur()
            cur.execute("""SELECT e.user_id, a.status
                        FROM expenses e 
                        LEFT JOIN approvals a ON e.id = a.expense_id
                        WHERE e.id = %s """, (expense_id,))
            row = cur.fetchone()
            if not(row):
                print("Error: Expense not found.")
                return False
            if row[0] != self.current_user_id:
                print("Error: Expense was not made by this user.")
                return False
            if row[1] != 'Pending':
                print(f"Error: Expense '{expense_id}' is not pending. Status: '{row[0]}'")
                return False
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
                updates.append(f"{key} = %s")
                params.append(val)

            if not updates:
                return False

            updates.append(f"{"clock"} = %s")
            params.append(datetime.datetime.now())
            params.append(int(expense_id))
            sql = f"UPDATE expenses SET {', '.join(updates)} WHERE id = %s"
            
            cur.execute(sql, params)
            conn.commit()
            return cur.rowcount > 0
        except Exception as e:
            print(e)
            conn.rollback()
            raise
        finally:
            cur.close()
            conn.close()


    def delete_expense(self, expense_id: int):
        conn = get_conn()
        try:
            cur = self.conn.cursor()
            if row[0] != self.current_user_id:
                print("Error: Expense was not made by this user.")
                return False
            cur.execute("DELETE FROM expenses WHERE id = ?", (int(expense_id),))
            self.conn.commit()
            return cur.rowcount > 0
        finally:
            cur.close()
            conn.close()


    def total_expenses(self):
        cur = self.conn.cur()
        cur.execute("SELECT SUM(amount) FROM expenses")
        row= cur.fetchone()
        return float(row[0]) if row else 0.0

    def total_expenses_by_description(self):
        cur = self.conn.cur()
        cur.execute("SELECT description, SUM(amount) FROM expenses GROUP BY description")
        rows = cur.fetchall()
        return {row[0]: float(row[1]) for row in rows}


