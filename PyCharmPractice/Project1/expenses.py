import datetime

from utils.connectionmanager import get_conn

class Expenses:
    def __init__(self):
        self.current_user_id = None


    def set_current_user(self, user_id: int):
        self.current_user_id = int(user_id) if user_id is not None else None


    def create_expense(self, amount: float, description: str = "", comment: str = ""):
        if amount < 0.0:
            amount = 0.0
            if comment != "":
                comment = comment + " | Amount lower than zero, set to zero"
            else:
                comment = "Amount lower than zero, set to zero"

        if description == "":
            description = "Other"

        if comment == "":
            comment = "no comment"

        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("""
            INSERT INTO expenses (user_id, amount, description, comment, clock) VALUES (%s, %s, %s, %s, %s)""",
                           (self.current_user_id, amount, description, comment, datetime.datetime.now()))
            conn.commit()
            expense_id = cur.lastrowid

            cur.execute("""INSERT INTO approvals (expense_id) VALUES (%s)""",(expense_id,))
            conn.commit()

            return expense_id #returns last id added
        except Exception as e:
            print(e)
            conn.rollback()
            raise
        finally:
            try:
                cur.close()
            except Exception:
                pass
            try:
                conn.close()
            except Exception:
                pass


    def read_expenses(self):
        """Return list of expenses as dicts with parsed types under specific user"""
        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("""
                        SELECT e.id, e.amount, e.description, e.comment, e.clock,u.username, a.status
                        FROM expenses e 
                        LEFT JOIN users u ON e.user_id = u.id
                        LEFT JOIN approvals a ON e.id = a.expense_id
                        WHERE u.id = %s AND a.status = 'pending' ORDER BY e.id
                        """, (self.current_user_id,))
            rows = cur.fetchall()
            expenses = []
            for row in rows:
                    expenses.append({
                        "id": row[0],
                        "amount": float(row[1]),
                        "description": row[2],
                        "comment": row[3],
                        "clock": row[4].date().isoformat(),
                        "username": row[5],
                        "status": row[6]
                    })
            return expenses
        finally:
            try:
                cur.close()
            except Exception:
                pass
            try:
                conn.close()
            except Exception:
                pass

    def read_expense_history(self):
        """Return list of past expenses as dicts with parsed types under specific user"""
        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("""
                        SELECT e.id, e.amount, e.description, a.review_date, e.comment, a.status, a.reviewer, a.comment
                        FROM expenses e 
                        LEFT JOIN users u ON e.user_id = u.id
                        LEFT JOIN approvals a ON e.id = a.expense_id
                        WHERE u.id = %s AND a.status != 'pending' ORDER BY e.id
                        """, (self.current_user_id,))
            rows = cur.fetchall()
            expenses = []
            for row in rows:
                    expenses.append({
                        "id": row[0],
                        "amount": float(row[1]),
                        "description": row[2],
                        "comment": row[4],
                        "clock": row[3].date().isoformat(),
                        "reviewer": row[5],
                        "status": row[6],
                        "rev_comment": row[7]
                    })
            return expenses
        finally:
            try:
                cur.close()
            except Exception:
                pass
            try:
                conn.close()
            except Exception:
                pass


    def get_expense(self, expense_id: int):
        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("""SELECT e.id, e.user_id, e.amount, e.description, e.clock,e.comment, a.status, a.comment
                        FROM expenses e 
                        LEFT JOIN users u ON e.user_id = u.id
                        LEFT JOIN approvals a ON e.id = a.expense_id WHERE e.id = %s""", (int(expense_id),))
            row = cur.fetchone()

            if not(row):
                return None
            elif row[1] != self.current_user_id:
                return None
            else:
                return {
                        "id": row[0],
                        "amount": float(row[2]),
                        "description": row[3],
                        "username": row[5],
                        "clock": row[4].date().isoformat(),
                        "status": row[6],
                        "comment": row[7]
                    }
        finally:
            cur.close()
            conn.close()


    def update_expense(self, expense_id: int, **kwargs):
        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("""SELECT e.user_id, a.status, e.amount
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
            if row[1] != 'pending':
                print(f"Error: Expense '{expense_id}' is not pending. Status: '{row[1]}'")
                return False
            allowed = {"amount", "description","comment"}
            updates = []
            params = []
            for key, val in kwargs.items():
                if key not in allowed:
                    continue
                if key == "amount":
                    val = float(val)
                    if val < 0:
                        print("value cannot be negative, value unchanged.")
                        val = float(row[2])
                updates.append(f"{key} = %s")
                params.append(val)

            if not updates:
                return False

            updates.append(f"{"clock"} = %s")
            params.append(datetime.datetime.now())
            params.append(int(expense_id))
            cur.execute(f"UPDATE expenses SET {', '.join(updates)} WHERE id = %s", params)
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
        cur = conn.cursor()
        try:
            cur.execute("""SELECT e.user_id, a.status
                           FROM expenses e
                                    LEFT JOIN approvals a ON e.id = a.expense_id
                           WHERE e.id = %s """, (expense_id,))
            row = cur.fetchone()
            if row[0] != self.current_user_id:
                print("Error: Expense was not made by this user.")
                return False
            cur.execute("DELETE FROM expenses WHERE id = %s", (int(expense_id),))
            conn.commit()
            return cur.rowcount > 0
        finally:
            cur.close()
            conn.close()


    def total_expenses(self):
        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("SELECT SUM(amount) FROM expenses where user_id = %s", (self.current_user_id,))
            row= cur.fetchone()
            return float(row[0]) if row else 0.0
        finally:
            cur.close()
            conn.close()

    def total_expenses_by_description(self):
        conn = get_conn()
        cur = conn.cursor()
        try:
            cur.execute("SELECT description, SUM(amount) FROM expenses where user_id = %s GROUP BY description", (self.current_user_id,))
            rows = cur.fetchall()
            return {row[0]: float(row[1]) for row in rows}
        finally:
            cur.close()
            conn.close()


