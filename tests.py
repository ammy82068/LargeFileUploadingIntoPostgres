import psycopg2

def write_file(data, filename):
    with open(filename, 'wb') as f:
        a = f.write(data)

def download_file(_id):

    conn = psycopg2.connect("host=localhost dbname=postgres user=postgres password=arit@123")
    cur = conn.cursor()
    cur.execute("""SELECT filename, file FROM documents WHERE id = %s """, (_id,))
    d1 = cur.fetchone()
    fileName = d1[0]
    fileData = d1[1]
    #print(type(d1))
    d2 = memoryview.tobytes(fileData)
    write_file(d2, fileName)
    cur.close()
    conn.close()
