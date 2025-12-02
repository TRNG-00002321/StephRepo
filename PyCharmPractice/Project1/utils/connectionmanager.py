import mysql.connector
#from __init__ import DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD

DB_HOST = "localhost"
DB_PORT = 3306
DB_NAME = "expense_manager"
DB_USER = "root"
DB_PASSWORD = "manage"


def get_conn():
    return mysql.connector.connect(
    host=DB_HOST,
    port=DB_PORT,
    database=DB_NAME,
    user=DB_USER,
    password=DB_PASSWORD,
    autocommit=False
)
