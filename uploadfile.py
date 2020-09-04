import psycopg2

def file_read(filename):
    with open(filename, 'rb') as f:
        photo = f.read()
    return photo


def file_insert(_id, filename):
    data = file_read(filename)
    print(filename)
    conn = psycopg2.connect("host=localhost dbname=postgres user=postgres password=arit@123")
    cur = conn.cursor()
    cur.execute("INSERT INTO documents(id,filename,file) " +
                    "VALUES(%s,%s,%s)", (_id, filename, psycopg2.Binary(data)))
    conn.commit()
    # close the communication with the PostgresQL database
    cur.close()
    if conn is not None:
        conn.close()

if __name__ == '__main__':
    file_insert(1, '10mb.pdf')
    file_insert(2, 'proceedings.pdf')