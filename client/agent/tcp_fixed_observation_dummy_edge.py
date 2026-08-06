# tcp_fixed_observation_dummy_edge.py

import socket
import struct
import time


HOST = "127.0.0.1"
PORT = 23065

# Observation Protocol v1
#
# +-----------+---------+----------+
# | equipment | channel | quantity |
# | uint8     | uint8   | double   |
# | 1 byte    | 1 byte  | 8 bytes  |
# +-----------+---------+----------+
#
# Total: 10 bytes
#
OBSERVATION_FORMAT = ">BBd"
OBSERVATION_SIZE = struct.calcsize(OBSERVATION_FORMAT)

EQUIPMENT_PUMP1 = 1
CHANNEL_ELECTRIC = 1

INTERVAL_SECONDS = 1.0


def encode_observation(
    equipment: int,
    channel: int,
    quantity: float,
) -> bytes:
    payload = struct.pack(
        OBSERVATION_FORMAT,
        equipment,
        channel,
        quantity,
    )

    if len(payload) != OBSERVATION_SIZE:
        raise ValueError(
            f"Invalid observation size: "
            f"expected={OBSERVATION_SIZE}, actual={len(payload)}"
        )

    return payload


def main() -> None:
    quantity = 100.0

    print(f"Observation frame size: {OBSERVATION_SIZE} bytes")

    while True:
        try:
            with socket.create_connection((HOST, PORT)) as sock:
                print(f"connected: {HOST}:{PORT}")

                while True:
                    observation = encode_observation(
                        equipment=EQUIPMENT_PUMP1,
                        channel=CHANNEL_ELECTRIC,
                        quantity=quantity,
                    )

                    sock.sendall(observation)

                    print(
                        f"sent {len(observation)} bytes: "
                        f"equipment={EQUIPMENT_PUMP1}, "
                        f"channel={CHANNEL_ELECTRIC}, "
                        f"quantity={quantity}, "
                        f"hex={observation.hex()}"
                    )

                    quantity += 1.0
                    time.sleep(INTERVAL_SECONDS)

        except (
            ConnectionRefusedError,
            ConnectionResetError,
            BrokenPipeError,
        ) as e:
            print(f"connection error: {e}")
            print("retrying...")
            time.sleep(1)


if __name__ == "__main__":
    main()
