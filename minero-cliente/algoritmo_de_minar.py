import hashlib
import random

# Define the number of zeros required at the beginning of the hash
num_zeros = 5
key = 1
# Loop until a hash with the required number of zeros is found
while True:
    # Generate a random key
    key2 = str(key)

    # Concatenate the key and word
    data = (key2 + "Hello world!").encode()

    # Calculate the SHA-256 hash
    hash_object = hashlib.sha256()
    hash_object.update(data)
    hex_digest = hash_object.hexdigest()
    print(hex_digest)

    # Check if the hash starts with the required number of zeros
    if hex_digest.startswith("0" * num_zeros):
        print(f"Found a hash with {num_zeros} zeros at the beginning!")
        print(f"Key: {key}")
        print(f"Hash: {hex_digest}")
        break
    key = key + 1