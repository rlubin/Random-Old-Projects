import pandas as pd
from algorithms.Algorithm import Algorithm

'''
test_algorithm.py
purpose
'''
# check all the price_data points, create price_data required, make decision
# parameters:
# return: decision


class TestAlgorithm(Algorithm):

    def __init__(self):
        name = '20 ma cross'
        description = 'buy long/buy back short on price > 20ma\nsell short/sell long on < 20ma'
        super().__init__(name, description)

    def on_tick(self, price_data, current_position):
        # 20 most recent price_data points
        x = len(price_data)-20 if len(price_data) > 20 else 0
        y = x - 1 if len(price_data) > 20 else x
        data_facts = {'date': price_data['Date'][len(price_data)-1], 'low20': price_data['Close'][y:len(price_data)-1].min(
        ), 'high20': price_data['Close'][y:len(price_data)-1].max(), 'sma20': price_data['Close'][y:len(price_data)-1].mean()}
        self.length = len(price_data)
        if(self.length == self.i):
            print(price_data)
            print(data_facts)
        self.i = self.i + 1
        if(current_position == ''):
            # buy long if break 20day high, and no positions
            if(price_data['Close'][len(price_data)-1] > data_facts['high20']):
                return {'decision': 'buy', 'date': data_facts['date'], 'price': price_data['Close'][len(price_data)-1]}
            # sell short if break 20day low and no positions
            if(price_data['Close'][len(price_data)-1] < data_facts['low20']):
                return {'decision': 'sell', 'date': data_facts['date'], 'price': price_data['Close'][len(price_data)-1]}
        # sell if break below 20ma and long
        if(current_position == 'long'):
            if(price_data['Close'][len(price_data)-1] < data_facts['sma20']):
                return {'decision': 'sell', 'date': data_facts['date'], 'price': price_data['Close'][len(price_data)-1]}
        # buy back if break above 20ma and short
        if(current_position == 'short'):
            if(price_data['Close'][len(price_data)-1] > data_facts['sma20']):
                return {'decision': 'buy', 'date': data_facts['date'], 'price': price_data['Close'][len(price_data)-1]}
