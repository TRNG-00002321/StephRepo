from .connectionmanager import get_conn

def ensure_schema():
    sqls = [
        """CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(150) NOT NULL UNIQUE,
            password VARCHAR(255) NOT NULL,
            role VARCHAR(50) NOT NULL DEFAULT 'Employee'
        )""",
        """CREATE TABLE IF NOT EXISTS expenses (
            id INT AUTO_INCREMENT PRIMARY KEY,
            user_id INT NOT NULL,
            amount DECIMAL(12,2) NOT NULL DEFAULT 0.00,
            description TEXT,
            comment TEXT,
            clock DATETIME NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
        )""",
        """CREATE TABLE IF NOT EXISTS approvals (
            id INT AUTO_INCREMENT PRIMARY KEY,
            expense_id INT NOT NULL,
            status VARCHAR(20) NOT NULL DEFAULT 'pending',
            reviewer INT,
            comment TEXT,
            review_date DATETIME,
            FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE CASCADE,
            FOREIGN KEY (reviewer) REFERENCES users(id) ON DELETE SET NULL
        )"""
    ]

    conn = get_conn()
    cursor = conn.cursor()
    try:
        for s in sqls:
            cursor.execute(s)
        conn.commit()
    finally:
        cursor.close()
        conn.close()
