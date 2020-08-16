#!/usr/bin/python

import sys
import random
from multiprocessing.dummy import Pool as ThreadPool
import subprocess

NUM_THREADS = 4
NUM_REQUESTS = 100

HOST = '127.0.0.1'

if __name__ == '__main__':
    assert(len(sys.argv) == 2)
    port = sys.argv[1]

    client_bin = './tcpclient'
    pool = ThreadPool(NUM_THREADS)

    # generate random sequence numbers
    seq_numbers = map(lambda x: random.randint(0,4096), range(NUM_REQUESTS))

    def launch_request(n):
        cmd = [client_bin, HOST, port, str(n)]
        print(' '.join(cmd))
        subprocess.check_call(cmd)

    results = pool.map(launch_request, seq_numbers)
    pool.close()
    pool.join()
