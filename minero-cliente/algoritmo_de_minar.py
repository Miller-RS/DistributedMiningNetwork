import hashlib
import random
from sha1 import sha1


class HashFinder:
    def __init__(self, num_zeros, num_processes, word):
        self.num_zeros = num_zeros
        self.num_processes = num_processes
        self.word = word

    # Define a function to generate random keys and calculate the SHA-256 hash
    def find_hash(self, start_key, end_key, result_queue):
        for key in range(start_key, end_key):
            # Concatenate the key and word
            # data = (str(key) + self.word).encode()
            data = (self.word + str(key)).encode()
            # Remove the newline character from data
            clean_data = data.replace(b'\r', b'').replace(b'\n', b'')
            # Calculate the SHA-1 hash by hashlib
            # hash_object = hashlib.sha1()
            # hash_object.update(clean_data)
            # hex_digest = hash_object.hexdigest()

            # Calculate the SHA-1 hash
            hex_digest = sha1(clean_data)
            # Check if the hash starts with the required number of zeros
            if hex_digest.startswith("0" * self.num_zeros):
                result_queue.put((key, hex_digest))
