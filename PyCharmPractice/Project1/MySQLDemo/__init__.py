import mysql.connector
from sqlalchemy import *

from MiniProjects.Dictionaries import Employees

#Environment variables
DB_USER = "root"
DB_PASSWORD = "tacocat"
DB_HOST = "localhost"
DB_PORT = 3306
DB_NAME = "mydb2"

connection_url = (f"mysql+mysqlconnector://{DB_USER}:{DB_PASSWORD}@"
                  f"{DB_HOST}:{DB_PORT}/{DB_NAME}")

engine = create_engine(connection_url, echo=True)
meta = MetaData()

try:
    with engine.connect() as connection:
        print("Database connection successful!")

        users = Table('users', meta,
                      Column('id', Integer, primary_key=True, autoincrement=True),
                      Column('username', String(50), nullable=False, unique=True),
                      Column('password', String(50), nullable=False),
                      Column('role', String(50), nullable=False, default="Employee"),
                      )
        meta.create_all(engine)

        # insert_stmt = insert(users).values(username='bob', password='tacocatboogaloo', role='Employee')
        #
        # #del_stmt = delete(users).where(users.c.id == 2)
        #
        update_stmt = update(users).where(users.c.username == 'steef').values(role='Employee')
        connection.execute(update_stmt)
        connection.commit()

        stmt = select(users)
        result = connection.execute(stmt)
        for row in result.fetchall():
            print(row)

except Exception as e:
    print(f"An error occurred: {e}")


# cursor.execute('''select * from primecontacts''')
# rows = cursor.fetchall()
# for row in rows:
#     print(row)
#
# print("\n")
#
# try:
#     name = "bob"
#     email = "tacotrucktuesday@email.com"
#
#     command = '''insert into contact(name,email) values(%s,%s)'''
#     vals = (name, email)
#
#     cursor.execute(command,vals)
#     conn.commit()
#
#
#     cursor.execute('''select * from contact''')
#     rows = cursor.fetchall()
#
#     for row in rows:
#         print(row)
#
# except mysql.connector.Error as error:
#     print("Error {}".format(error) + "\nEntry already exists")
#     cursor.execute('''select * from contact''')
#     rows = cursor.fetchall()
#
#     for row in rows:
#         print(row)
#
# conn.close()