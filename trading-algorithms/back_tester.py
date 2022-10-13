import pandas as pd
from algorithms.TestAlgorithm import TestAlgorithm
from back_tester.AlgorithmTester import AlgorithmTester
from back_tester.PriceData import PriceData
from api.API import API
'''
this function uses AlgorithmTester
to test multiple algorithms with data
'''


def main():
    api = API()
    print(api.get_history('BTC'))
    STOCKPATH = './data/stock/'
    CRYPTOPATH = './data/crypto/'
    # data = PriceData(STOCKPATH + 'SP500.csv', 'stock')
    data = PriceData(CRYPTOPATH + 'BTCUSD.csv', 'crypto')
    algo = TestAlgorithm()
    tester = AlgorithmTester(algo, data)
    tester.back_test(1000)


if __name__ == '__main__':
    main()
