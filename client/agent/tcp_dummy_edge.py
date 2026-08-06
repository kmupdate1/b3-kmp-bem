#!/usr/bin/env python3

import socket
import struct

HOST = "127.0.0.1"
PORT = 23065

# BinaryData
equipment = 1
channel = 2
quantity = 123.45

# Kotlin:
# equipment : UByte  = 1 byte
# channel   : UByte  = 1 byte
# quantity  : Double = 8 bytes
#
# BIG_ENDIAN
payload = struct.pack(
    ">BBd",
    equipment,
    channel,
    quantity,
)

print(f"destination : {HOST}:{PORT}")
print(f"equipment   : {equipment}")
print(f"channel     : {channel}")
print(f"quantity    : {quantity}")
print(f"packet size : {len(payload)} bytes")
print(f"payload     : {payload.hex()}")

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect((HOST, PORT))
    sock.sendall(payload)

print(f"sent        : {len(payload)} bytes")
