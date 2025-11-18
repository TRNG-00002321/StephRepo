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
        expenses = []
        with open(self.filename, 'r', newline='', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for row in reader:
                try:
                    parsed = {
                        'id': int(row['id']),
                        'date': datetime.datetime.fromisoformat(row['date']),
                        'amount': float(row['amount']),
                        'description': row['description'],
                        'note': row['note']
                    }
                except Exception:
                    # skip malformed rows
                    continue
                expenses.append(parsed)
        return expenses

    def get_expense(self, expense_id: int):
        for expense in self.read_expenses():
            if expense['id'] == expense_id:
                return expense
        return {}

    def update_expense(self, expense_id: int, **kwargs):
        updated = False
        rows = []

        with open(self.filename, 'r', newline='', encoding='utf-8') as f:
            reader = csv.DictReader(f)

            for row in reader:
                if int(row['id']) == expense_id:
                    for key, value in kwargs.items():
                        if key == "date":
                            row['date'] = value.isoformat()
                        elif key == "amount":
                            if value < 0:
                                row['amount'] = 0
                            else:
                                row['amount'] = f"{float(value):.2f}"
                        elif key in row:
                            row[key] = value
                    updated = True
                rows.append(row)

        with open(self.filename, 'w', newline='', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=self.fieldnames)
            writer.writeheader()
            writer.writerows(rows)

        return updated

    def delete_expense(self, expense_id: int):
        deleted = False
        rows = []
        with open(self.filename, 'r', newline='', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for row in reader:
                try:
                    if int(row['id']) == expense_id:
                        deleted = True
                        continue
                except ValueError:
                    continue
                rows.append(row)

        with open(self.filename, 'w', newline='', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=self.fieldnames)
            writer.writeheader()
            writer.writerows(rows)

        return deleted


    def total_expenses(self):
        return sum(expense['amount'] for expense in self.read_expenses())

    def total_expenses_by_description(self):
        totals = {}
        for expense in self.read_expenses():
            totals.setdefault(expense['description'], 0)
            totals[expense['description']] += expense['amount']

        return totals



if __name__ == "__main__":
    ex = Expenses()                  # uses "expenses.csv"

    id3 = ex.create_expense(50, "Entertainment")
    print("All:", ex.read_expenses())
    print("Total by description:", ex.total_expenses_by_description())
    print("Total all:", ex.total_expenses())
    print("Get ID1:", ex.get_expense(id3))
    ex.update_expense(id3, amount=52)
    print("After changes:", ex.read_expenses())












