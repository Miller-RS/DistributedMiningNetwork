from conexion import Client
from Constanst import *
from algoritmo_de_minar import HashFinder
import multiprocessing
from multiprocessing import freeze_support
import threading
from queue import Queue
import queue

# def listen_for_response(self, client):
#         a = 1
#         #response = client.receive_data()
#         #print(response)
        
def listen_for_response(client, result_queue):
    while True:
        response = client.receive_data()
        if response:
            print(f"Received response from server: {response}")
            result_queue.put(response)

if __name__ == '__main__':
    # Example usage
    client = Client(HOST, PORT, USER)
    client.connect()
   # client.send_user()
    data = client.receive_data()

    # Call the freeze_support() function
    freeze_support()

    hash_finder = HashFinder(3, 6, "hello")
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
    #result_queue2 = multiprocessing.Queue()
    

    # Create a new thread to listen for the server's response
    
    #response_thread.start()
    # response_process = multiprocessing.Process(target=listen_for_response, args=(client, result_queue))
    # processes.append(response_process)
    # # response_process.start()
    # response_process.start()
    # Start the processes

    # for process in processes:
    #     process.start()
    for i in range(6):
        processes[i].start()
    print("bandera 0.2")
    # Wait for the processes to finish
    # for process in processes:
    #     process.join()

    for i in range(6):
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
    print("bandera 2")
    client.close()
