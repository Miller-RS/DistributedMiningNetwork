from conexion import Client
from Constanst import *
from algoritmo_de_minar import HashFinder
import multiprocessing
from multiprocessing import freeze_support


if __name__ == '__main__':
    # Example usage
    client = Client(HOST, PORT, USER)
    client.connect()
    client.send_user()
    data = client.receive_data()

    # Call the freeze_support() function
    freeze_support()

    hash_finder = HashFinder(7, 4, data)
    # Create a queue to store the results
    result_queue = multiprocessing.Queue()

    # Create a list of processes
    processes = []
    for i in range(hash_finder.num_processes):
        start_key = i * 1000000
        end_key = (i + 1) * 1000000
        process = multiprocessing.Process(target=hash_finder.find_hash, args=(start_key, end_key, result_queue))
        processes.append(process)

    # Start the processes
    for process in processes:
        process.start()

    # Wait for the processes to finish
    for process in processes:
        process.join()

            # Get the results from the queue
    results = []
    while not result_queue.empty():
        results.append(result_queue.get())

    for key, hex_digest in results:
        print(f"Found a hash with {hash_finder.num_zeros} zeros at the beginning!")
        print(f"Key: {key}")
        print(f"Hash: {hex_digest}")

    client.close()