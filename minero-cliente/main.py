from conexion import Client
from Constanst import *
from algoritmo_de_minar import HashFinder
import multiprocessing
from multiprocessing import freeze_support
import threading
from queue import Queue
import queue
from sha1 import sha1


if __name__ == '__main__':
    # Example usage
    client = Client(HOST, PORT, USER)
    client.connect()
    # client.send_user()
    data = client.receive_data()

    # Split the data into: word and num_zeros
    word, num_zeros = data.split()
    print(f"Word: {word}")
    print(f"Number of zeros: {num_zeros}")
    # print(type(num_zeros))

    # Call the freeze_support() function
    #freeze_support()

    hash_finder = HashFinder(int(num_zeros), 6, word)
    # Create a queue to store the results
    result_queue = multiprocessing.Queue()
    print("bandera 0")
    # Create a list of processes
    processes = []
    for i in range(hash_finder.num_processes):
        start_key = i * 1000000
        end_key = (i + 1) * 1000000
        process = multiprocessing.Process(target=hash_finder.find_hash, args=(start_key, end_key, result_queue))
        processes.append(process)
    print("bandera 0.1")
    
    for i in range(4):
        processes[i].start()
    print("bandera 0.2")

    for i in range(4):
        processes[i].join()
    print("bandera 0.3")
        # Get the results from the queue
    results = []
    while not result_queue.empty():
        results.append(result_queue.get())
    print("bandera 0.4")
    for key, hex_digest in results:
        print(
            f"Found a hash with {hash_finder.num_zeros} zeros at the beginning!")
        print(f"Key: {key}")
        print(f"Hash: {hex_digest}")

    print(results)

    print("bandera 1")
    client.send_message(str(results[0][0]) + " " + results[0][1])
    print(str(results[0][0]) + " " + results[0][1])
  
    print("bandera 2")

    data4 = client.receive_data()
    print("bandera 3")
    print("data 4: ",data4)

    key, hash = data4.split()

    data = (word + str(key)).encode()
            # # Remove the newline character from data
    clean_data = data.replace(b'\r', b'').replace(b'\n', b'')
           
    hex_digest = sha1(clean_data)
    print("hash del servidor enviar", hash)
    print("hash que verifica el minero", hex_digest)

    print("bandera 4")
    if hex_digest == hash:
        print("The hash is correct")
        client.send_message("OK")
    print("bandera 5")
    client.close()
