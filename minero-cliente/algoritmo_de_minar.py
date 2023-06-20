import hashlib
import random

class HashFinder:
    def __init__(self, num_zeros, num_processes, word):
        self.num_zeros = num_zeros
        self.num_processes = num_processes
        self.word = word

    # Define a function to generate random keys and calculate the SHA-256 hash
    def find_hash(self, start_key, end_key, result_queue):
            for key in range(start_key, end_key):
                # Concatenate the key and word
                data = (str(key) + self.word).encode()

                # Calculate the SHA-256 hash
                hash_object = hashlib.sha256()
                hash_object.update(data)
                hex_digest = hash_object.hexdigest()

                # Check if the hash starts with the required number of zeros
                if hex_digest.startswith("0" * self.num_zeros):
                    result_queue.put((key, hex_digest))
