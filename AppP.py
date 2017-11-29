import random

def get_bit(n_dec, n_bin):
    return (n_dec >> n_bin) & 1        


quantity = 4
number_dec = 15                     #int("1111",2)
sum0 = sum1 = 0
arr = [random.randint(0, 9) for r in range(quantity)]
# arr = [4,5,7,1]
res = []
print (arr)

for n_dec in range (1, number_dec + 1):
    # print (n_dec)
    for n_bin in range(0, len(arr)):
        if(get_bit(n_dec, n_bin) == 0):
            sum0 += arr[n_bin]
        elif(get_bit(n_dec, n_bin) == 1):
            sum1 += arr[n_bin]
    
    res.append(abs(sum1- sum0))
    sum0 = sum1 = 0

print(min(res))

