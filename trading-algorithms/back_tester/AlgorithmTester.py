import pandas as pd
import datetime as dt
import numpy as np
import csv
import os
from back_tester.PriceData import PriceData

'''
AlgorithmTester.py
input: Algorithm, DataFrame
returns: trade_entries.csv (
    type, price_data, entry_price, entry_date, exit_price, exit_date)
creates a list of trades taken by the algorithm with the data
'''


class AlgorithmTester:

    def __init__(self, algorithm, price_data):
        self.algorithm = algorithm
        self.price_data = price_data
        # 'long', 'short', ''
        self.position = ''
        # self.test_len = price_data.length
        self.test_len = 10
        self.trades = []

    # step though price data tick by tick (each element in the list counts as a tick)
    # and run the on_tick function of the algorithm
    def back_test(self, test_len=''):
        if(test_len != ''):
            self.test_len = test_len
        for x in range(self.price_data.length-self.test_len, self.price_data.length+1):
            trade = self.algorithm.on_tick(
                self.price_data.data[0:x+1], self.position)
            if(trade != None):
                self.take_position(trade)
        self.report()

    def take_position(self, trade):
        if(trade['decision'] == 'buy'):
            if(self.position == ''):
                self.position = 'long'
                self.trades.append(
                    {'type': 'long', 'entryDate': trade['date'], 'entryPrice': trade['price'], 'exitDate': None, 'exitPrice': None})
            if(self.position == 'short'):
                self.position = ''
                self.trades[len(
                    self.trades)-1].update(
                    {'exitDate': trade['date'], 'exitPrice': trade['price']})
        if(trade['decision'] == 'sell'):
            if(self.position == 'long'):
                self.position = ''
                self.trades[len(
                    self.trades)-1].update({'exitDate': trade['date'], 'exitPrice': trade['price']})
            if(self.position == ''):
                self.position = 'short'
                self.trades.append(
                    {'type': 'short', 'entryDate': trade['date'], 'entryPrice': trade['price'], 'exitDate': None, 'exitPrice': None})

    def report(self):
        trade_data = pd.DataFrame(self.trades)
        longs = pd.DataFrame(trade_data[trade_data['type'] == 'long'])
        # problematic, gives warning
        entry_price = longs['entryPrice']
        exit_price = longs['exitPrice']
        longs['return'] = exit_price - entry_price
        shorts = pd.DataFrame(trade_data[trade_data['type'] == 'short'])
        # problematic, gives warning
        entry_price = shorts['entryPrice']
        exit_price = shorts['exitPrice']
        shorts['return'] = entry_price - exit_price
        trade_data = pd.concat([shorts, longs])
        trade_data['length'] = trade_data['exitDate']-trade_data['entryDate']
        trade_data = trade_data.sort_index()
        trade_data.index.name = 'Trade'
        # use 2d array then, because dataframe can't offer column datatype flexibility
        analysis = [
            ['Category', 'All trades', 'Long trades', 'Short trades']]
        analysis.append(['Absolute return', round(trade_data["return"].sum(
        ), 2), round(longs["return"].sum(), 2), round(shorts["return"].sum(), 2)])
        analysis.append(['Number of trades', len(trade_data), len(
            longs), len(shorts)])
        analysis.append(['Number of profitable trades', len(trade_data.loc[trade_data['return'] > 0]), len(
            longs.loc[trade_data['return'] > 0]), len(shorts.loc[trade_data['return'] > 0])])
        self._2d_to_csv(analysis)
        self.df_to_csv(trade_data)

    def _2d_to_csv(self, array):
        start_date = self.price_data.data['Date'][self.price_data.length -
                                                  self.test_len].strftime("%Y-%m-%d")
        end_date = self.price_data.end_date.strftime("%Y-%m-%d")
        file_name = 'results/' + self.algorithm.name.replace(
            ' ', '-') + '_' + self.price_data.name.replace(' ', '-') + '_' + start_date + '-to-' + end_date + '.csv'
        if os.path.exists(file_name):
            os.remove(file_name)
        with open(file_name, 'w', newline='') as csvfile:
            spamwriter = csv.writer(csvfile, delimiter=',')
            for i in range(0, len(array)):
                spamwriter.writerow(array[i])

    def df_to_csv(self, df):
        start_date = self.price_data.data['Date'][self.price_data.length -
                                                  self.test_len].strftime("%Y-%m-%d")
        end_date = self.price_data.end_date.strftime("%Y-%m-%d")
        file_name = 'results/' + self.algorithm.name.replace(
            ' ', '-') + '_' + self.price_data.name.replace(' ', '-') + '_' + start_date + '-to-' + end_date + '.csv'
        df.to_csv(file_name, mode='a')
        print("results output to ", file_name)
