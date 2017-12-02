import sys
import random
from datetime import datetime

def get_bit(n_dec, n_bin):
    return (n_dec >> n_bin) & 1 

quantity = int(sys.argv[1])

number_dec = 2**quantity -1
print(number_dec)
sum0 = sum1 = 0
arr = [random.randint(0, 9) for r in range(quantity)]
print (arr)

difference = sys.maxsize

start = datetime.now()

for n_dec in range (1, number_dec + 1):
    for n_bin in range(0, len(arr)):
        if(get_bit(n_dec, n_bin) == 0):
            sum0 += arr[n_bin]
        elif(get_bit(n_dec, n_bin) == 1):
            sum1 += arr[n_bin]
    
    dif = abs(sum1 - sum0)

    if dif < difference:
        difference = dif

    sum0 = sum1 = 0

print (difference)
stop = datetime.now()
print(str(stop - start))
