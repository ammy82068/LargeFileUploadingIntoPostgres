from flask import Flask, request, abort, jsonify, send_from_directory, send_file
from comman import  file_insert, file_read, write_file, download_file

api = Flask(__name__)


@api.route('/upload', methods=['POST'])
def uploadFile():
    
    filePath = request.args.get('fpath')
    fileName = request.args.get('fName')
    file_insert(filePath, fileName)
    return "file uploaded"

@api.route('/download', methods=['GET'])
def downloadFile():
    fileId = request.args.get('id')
    response = download_file(fileId)
    return send_file(response ,as_attachment=True)

# serve at localhost:5000
api.run(debug=True)


