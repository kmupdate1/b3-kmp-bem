#!/usr/bin/env python3

import socket
import struct

HOST = "127.0.0.1"
PORT = 23065

# BinaryData
equipment = 1
measurements = [
    (1, 123.45),
    (2, 0.5)
]

print(f"destination : {HOST}:{PORT}")
print(f"equipment   : {equipment}")

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect((HOST, PORT))

    for channel, quantity in measurements:
        payload = struct.pack(
            ">BBd",
            equipment,
            channel,
            quantity,
        )

        print()
        print(f"channel     : {channel}")
        print(f"quantity    : {quantity}")
        print(f"packet size : {len(payload)} bytes")
        print(f"payload     : {payload.hex()}")

        sock.sendall(payload)
        print(f"sent        : {len(payload)} bytes")

print()
print(f"sent total  : {len(measurements)} measurements")
