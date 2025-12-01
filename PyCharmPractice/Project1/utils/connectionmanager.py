import mysql.connector
from __init__ import DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD

conn = mysql.connector.connect(
    pool_name="expense_pool",
    pool_size=5,
    host=DB_HOST,
    port=DB_PORT,
    database=DB_NAME,
    user=DB_USER,
    password=DB_PASSWORD,
    autocommit=False
)

def get_conn():
    return conn.get_connection()
