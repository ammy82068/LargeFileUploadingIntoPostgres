import psycopg2

def write_file(data, filename):
    with open(filename, 'wb') as f:
        f.write(data)

def read_file(filename):

    conn = psycopg2.connect("host=localhost dbname=postgres user=postgres password=arit@123")
    cur = conn.cursor()
    cur.execute("""SELECT file FROM documents WHERE filename = %s """, (filename,))
    d1 = cur.fetchone()[0]
    print(type(d1))
    d2 = memoryview.tobytes(d1)
    write_file(d2, filename)
    cur.close()
    conn.close()


if __name__ == '__main__':
    read_file('10mb.pdf')
    read_file('proceedings.pdf')