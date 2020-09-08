import psycopg2, os


# file upload start
# def file_read(filePath, filename):
#     folderPath = filePath + '//' +filename
#     with open(folderPath, 'rb') as f:
#         fileData = f.read()
#     return fileData


def file_upload(filename, filedata):
    conn = psycopg2.connect("host=localhost dbname=postgres user=postgres password=arit@123")
    cur = conn.cursor()
    cur.execute("INSERT INTO documents(filename,file) " +
                    "VALUES(%s,%s)", (filename, psycopg2.Binary(filedata)))
    conn.commit()
    # close the communication with the PostgresQL database
    cur.close()
    if conn is not None:
        conn.close()

# file upload end metods
#---------------------------------------------------------------------------------------
