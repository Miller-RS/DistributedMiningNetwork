import hashlib
import random
# from sha1 import SHAone
from sha1 import sha1


class HashFinder:
    def __init__(self, num_zeros, num_processes, word):
        self.num_zeros = num_zeros
        self.num_processes = num_processes
        self.word = word

    # Define a function to generate random keys and calculate the SHA-256 hash
    def find_hash(self, start_key, end_key, result_queue):
        print("bandera t1")    
       
        while(True):
            key = random.randrange(0, 1000000)
            # Concatenate the key and word
            print("bandera t2")
           
            data = (self.word + str(key)).encode()
            # # Remove the newline character from data
            clean_data = data.replace(b'\r', b'').replace(b'\n', b'')

            # Calculate the SHA-1 hash
            # hex_digest = SHAone().sha1(clean_data)
            hex_digest = sha1(clean_data)
            print(key, hex_digest)
            # Check if the hash starts with the required number of zeros
            if hex_digest.startswith("0"*self.num_zeros):
                result_queue.put((key, hex_digest))
                break
