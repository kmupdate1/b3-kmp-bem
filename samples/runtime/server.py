from http.server import BaseHTTPRequestHandler, HTTPServer

class Handler(BaseHTTPRequestHandler):
    def do_POST(self):
        length = int(self.headers.get("Content-Length", 0))
        body = self.rfile.read(length).decode("utf-8")

        print("=" * 80)
        print(self.command, self.path)
        print(self.headers)
        print(body)
        print("=" * 80)

        self.send_response(200)
        self.end_headers()
        self.wfile.write(b"OK")

HTTPServer(("localhost", 8080), Handler).serve_forever()
