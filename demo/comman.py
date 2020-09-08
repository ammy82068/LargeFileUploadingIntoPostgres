import psycopg2, os

folderPath = "P:/temp/"

# file upload start
def file_read(filePath, filename):
    folderPath = filePath + '//' +filename
    with open(folderPath, 'rb') as f:
        fileData = f.read()
    return fileData


def file_insert(filePath, filename):
    data = file_read(filePath, filename)
    #print(data)
    conn = psycopg2.connect("host=localhost dbname=postgres user=postgres password=amit@123")
    cur = conn.cursor()
    cur.execute("INSERT INTO documents(filename,file) " +
                    "VALUES(%s,%s)", (filename, psycopg2.Binary(data)))
    conn.commit()
    # close the communication with the PostgresQL database
    cur.close()
    if conn is not None:
        conn.close()

# file upload end metods
#---------------------------------------------------------------------------------------

# file download functions

def write_file(data, filename):
    with open(filename, 'wb') as f:
        # f.write(data)
        return filename

def download_file(_id):

    conn = psycopg2.connect("host=localhost dbname=postgres user=postgres password=amit@123")
    cur = conn.cursor()
    cur.execute("""SELECT filename, file FROM documents WHERE id = %s """, (_id,))
    d1 = cur.fetchone()
    fileName = d1[0]
    fileData = d1[1]
    #print(type(d1))
    d2 = memoryview.tobytes(fileData)
    a = write_file(d2, fileName)
    cur.close()
    conn.close()
    print(a)
    return a
# file download end funtions ----------------------------------------------------------

