import datetime
import csv
import os

DEFAULT_FILE = "expenses.csv"

class Expenses:
    fieldnames = ['id', 'date', 'amount', 'category', 'note']
    id = 0

    def __init__(self, filename: str = DEFAULT_FILE):
        self.filename = filename
        self._ensure_file()
        Expenses.id = self._load_last_id()

    def _ensure_file(self):
        if not os.path.exists(self.filename):
            with open(self.filename, 'w', newline='', encoding='utf-8') as f:
                writer = csv.DictWriter(f, fieldnames=self.fieldnames)
                writer.writeheader()

    def _load_last_id(self) -> int:
        last = 0
        try:
            with open(self.filename, 'r', newline='', encoding='utf-8') as f:
                reader = csv.DictReader(f)
                for row in reader:
                    try:
                        rid = int(row.get('id', 0))
                        if rid > last:
                            last = rid
                    except ValueError:
                        continue
        except FileNotFoundError:
            return 0
        return last + 1

    def create_expense(self, amount: float, category: str, note: str = "")->int:
        if amount < 0:
            amount = 0
            note = "Amount lower than zero, set to zero"
        row = {
            'id': Expenses.id,
            'date': datetime.date.today(),
            'amount': f"{float(amount):.2f}",
            'category': category,
            'note': note
        }
        with open(self.filename, 'a', newline='', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=self.fieldnames)
            writer.writerow(row)
        added_id = Expenses.id
        Expenses.id += 1
        return added_id


    def read_expenses(self):
        """Return list of expenses as dicts with parsed types (id:int, date:datetime, amount:float)."""
        expenses = []
        with open(self.filename, 'r', newline='', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for row in reader:
                try:
                    parsed = {
                        'id': int(row['id']),
                        'date': datetime.datetime.fromisoformat(row['date']),
                        'amount': float(row['amount']),
                        'category': row['category'],
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

    def total_expenses_by_category(self):
        totals = {}
        for expense in self.read_expenses():
            totals.setdefault(expense['category'], 0)
            totals[expense['category']] += expense['amount']

        return totals



if __name__ == "__main__":
    ex = Expenses()                  # uses "expenses.csv"

    id3 = ex.create_expense(50, "Entertainment")
    print("All:", ex.read_expenses())
    print("Total by category:", ex.total_expenses_by_category())
    print("Total all:", ex.total_expenses())
    print("Get ID1:", ex.get_expense(id3))
    ex.update_expense(id3, amount=52)
    print("After changes:", ex.read_expenses())












