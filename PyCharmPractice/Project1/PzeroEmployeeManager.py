import datetime

from Project1.user import Users
from expenses import Expenses


if __name__ == "__main__":

    users = Users()
    expenses = Expenses()

    try:
        while True:
            if not users.get_current_user():
                print("\n--- Menu (not logged in) ---")
                print("1) Register")
                print("2) Login")
                print("3) Quit")
                c = input("Choose: ").strip()
                if c == "1":
                    uname = input("Username: ").strip()
                    pwd = input("Password: ").strip()
                    users.new_user(uname, pwd, "Employee")
                elif c == "2":
                    uname = input("Username: ").strip()
                    pwd = input("Password: ").strip()
                    login = users.login_user(uname, pwd)
                    if login != -1:
                        expenses.set_current_user(login['id'])
                elif c == "3":
                    print("Bye.")
                    break
                else:
                    print("Invalid")
            else:
                cu = users.get_current_user()
                print(f"\nLogged in as {cu['username']} (role: {cu['role']})")
                print("1) Create expense")
                print("2) List expenses")
                print("3) Get expense")
                print("4) Update expense")
                print("5) Delete expense")
                print("6) Totals")
                print("7) Logout")
                print("8) Quit")
                c = input("Choose: ").strip()
                if c == "1":
                    try:
                        amt = float(input("Amount: "))
                    except ValueError:
                        print("Bad amount")
                        continue
                    desc = input("Description: ").strip()
                    expenses.create_expense(amt, desc)
                elif c == "2":
                    rows = expenses.read_expenses()
                    if not rows:
                        print("No expenses.")
                    else:
                        for r in rows:
                            print(
                                f"{r['id']} | {r['amount']:.2f} | {r['description']} | {r['clock']} | {r['username'] or 'N/A'} | {r['status']} | {r['comment'] or 'N/A'}")
                elif c == "3":
                    eid = input("Expense id: ").strip()
                    if eid.isdigit():
                        e = expenses.get_expense(int(eid))
                        if e:
                            print(f"{e['id']} | {e['amount']:.2f} | {e['description']} | {e['clock']} | {e['username'] or 'N/A'} | {e['status']} | {e['comment'] or 'N/A'}" )
                        else: print( "Not found")
                    else:
                        print("Enter numeric id")
                elif c == "4":
                    eid = input("Expense id: ").strip()
                    if not eid.isdigit(): print("Numeric id required"); continue
                    updates = {}
                    amt = input("New amount (blank to skip): ").strip()
                    if amt:
                        try:
                            updates["amount"] = float(amt)
                        except:
                            print("Bad amount"); continue
                    desc = input("New description (blank skip): ").strip()
                    if desc: updates["description"] = desc
                    ok = expenses.update_expense(int(eid), **updates)
                    print("Updated" if ok else "Not updated")
                elif c == "5":
                    eid = input("Expense id to delete: ").strip()
                    if eid.isdigit():
                        ok = expenses.delete_expense(int(eid))
                        print("Deleted" if ok else "Not found")
                    else:
                        print("Numeric id required")
                elif c == "6":
                    print(f"Total: {expenses.total_expenses():.2f}")
                    desc = expenses.total_expenses_by_description()
                    for k, v in desc.items():
                        print(f"{k:<20} -> {v:.2f}")
                elif c == "7":
                    users.logout()
                elif c == "8":
                    print("Bye.")
                    break
                else:
                    print("Invalid")
    finally:
        print("db closed")


    # ex = Expenses()                  # uses "expense.db"
    # us = Users()
    # us.new_user(username="Stephenie", password="howdy")
    # #ex.create_expense(20.0,"BacoTell")
    # cursor = ex.conn.cursor()
    # cursor.execute("SELECT name FROM sqlite_master WHERE type='table';") #gets all tables in sqlite
    # tables = cursor.fetchall()
    # print("Tables in the database:")
    # for table in tables:
    #     print(table[0])
    #
    #
    # print("All:", ex.read_expenses())
    # print("by Group: " + str(ex.total_expenses_by_description()))
    # ex.delete_expense(4)
    # print("by Group: " + str(ex.total_expenses_by_description()))
    #
    #
    # ex.close_db()













