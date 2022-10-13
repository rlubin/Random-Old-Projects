'''
this wrapper class will be used to call alphavantage API
dox link
https://www.alphavantage.co/documentation/
only allows 5 api calls per minute with 500 calls per day
'''
import requests
import json
import time


class AlphaVantageAPI:

    def __init__(self):
        self.key = '5ZC0WWZ6635PJYOT'
        # 5 calls per minute means 1 api call every 12 seconds + 1 second to be safe
        self.timer = (60/5)+1
        self.i = 0

    # return price by the day data as json
    def get_daily(self, symbol):
        time.sleep(self.timer)
        response = requests.get(
            f'https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={symbol}&outputsize=full&apikey={self.key}')
        # print(response.json().keys())
        # print(type(response.json()))
        self.i = self.i + 1
        return (response.json()['Time Series (Daily)'])

    # return price by the minute (or any other interval allowed) data as json
    def get_interval(self, symbol, interval='1min'):
        time.sleep(self.timer)
        response = requests.get(
            f'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol={symbol}&interval={interval}&outputsize=full&apikey={self.key}')
        # print(response.json().keys())
        # print(type(response.json()))
        self.i = self.i + 1
        return (response.json()[f'Time Series ({interval})'])
