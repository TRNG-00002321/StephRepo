import sqlite3

# 1. Connect to the database
conn = sqlite3.connect('my_database.db')
cursor = conn.cursor()

# 2. Create a table (if it doesn't exist)
cursor.execute('''
    CREATE TABLE IF NOT EXISTS products (
        id INTEGER PRIMARY KEY,
        name TEXT NOT NULL,
        price REAL
    )
''')

# 3. Insert data
cursor.execute("INSERT INTO products (name, price) VALUES (?, ?)", ("Laptop", 1200.00))
cursor.execute("INSERT INTO products (name, price) VALUES (?, ?)", ("Mouse", 25.50))

# 4. Commit changes
conn.commit()

# 5. Fetch data
cursor.execute("SELECT * FROM products")
products = cursor.fetchall()

print("Products in the database:")
for product in products:
    print(f"ID: {product[0]}, Name: {product[1]}, Price: {product[2]}")

# 6. Close the connection
conn.close()