# tcp_observation_dummy.py

import socket
import struct
import time

HOST = "127.0.0.1"
PORT = 23065

EQUIPMENT_PUMP1 = 1
CHANNEL_ELECTRIC = 1

INTERVAL_SECONDS = 1.0


def encode_observation(
    equipment: int,
    channel: int,
    quantity: float,
) -> bytes:
    """
    Observation wire format:

        equipment : uint8   1 byte
        channel   : uint8   1 byte
        quantity  : double  8 bytes (big endian)

    Total: 10 bytes
    """
    return struct.pack(
        ">BBd",
        equipment,
        channel,
        quantity,
    )


def main() -> None:
    quantity = 100.0

    while True:
        try:
            with socket.create_connection((HOST, PORT)) as sock:
                print(f"connected: {HOST}:{PORT}")

                while True:
                    packet = encode_observation(
                        equipment=EQUIPMENT_PUMP1,
                        channel=CHANNEL_ELECTRIC,
                        quantity=quantity,
                    )

                    sock.sendall(packet)

                    print(
                        f"sent: "
                        f"equipment={EQUIPMENT_PUMP1}, "
                        f"channel={CHANNEL_ELECTRIC}, "
                        f"quantity={quantity}, "
                        f"bytes={packet.hex()}"
                    )

                    quantity += 1.0
                    time.sleep(INTERVAL_SECONDS)

        except (ConnectionRefusedError, ConnectionResetError, BrokenPipeError) as e:
            print(f"connection error: {e}")
            print("retrying...")
            time.sleep(1)


if __name__ == "__main__":
    main()
