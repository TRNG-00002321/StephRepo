from Project1.user import Users
from expenses import Expenses


if __name__ == "__main__":
    ex = Expenses()                  # uses "expenses.db"
    us = Users()
    us.new_user(username="Stephenie", password="howdy")
    #ex.create_expense(20.0,"BacoTell")
    cursor = ex.conn.cursor()
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table';") #gets all tables in sqlite
    tables = cursor.fetchall()
    print("Tables in the database:")
    for table in tables:
        print(table[0])


    print("All:", ex.read_expenses())
    print("by Group: " + str(ex.total_expenses_by_description()))
    ex.delete_expense(4)
    print("by Group: " + str(ex.total_expenses_by_description()))


    ex.close_db()













