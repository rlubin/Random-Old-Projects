from backtesting import Backtest, Strategy
from backtesting.lib import crossover

from backtesting.test import SMA, GOOG

import pandas as pd
import requests


class SmaCross(Strategy):
    def init(self):
        Close = self.data.Close
        self.ma1 = self.I(SMA, Close, 10)
        self.ma2 = self.I(SMA, Close, 20)

    def next(self):
        if crossover(self.ma1, self.ma2):
            self.buy()
        elif crossover(self.ma2, self.ma1):
            self.sell()


def fetch_2_df(sym1, lim=100, sym2='CAD'):
    url = f'https://min-api.cryptocompare.com/data/v2/histominute?fsym={sym1}&tsym={sym2}&limit={lim}'
    f = requests.get(url)
    ipdata = f.json()
    # print(ipdata)
    df = pd.DataFrame(ipdata['Data']['Data'])
    # convert df into a readable format for backtesting package
    # Index: Date(YYYY-MM-DD), Open, High, Low, Close, Volume
    # print(type(df))
    # print(df)
    columns = ['Time', 'Open', 'High', 'Low', 'Close', 'Volume']
    df2 = pd.DataFrame(columns=columns)
    # print(df2)
    # df2.set_index = df.time()  # convert to datetime
    for x in range(0, len(columns)):
        if(columns[x] == 'Volume'):
            df2[columns[x]] = df['volumeto'] - df['volumefrom']
        else:
            df2[columns[x]] = df[columns[x].lower()]
    # print(df2)
    df2 = df2.set_index('Time')
    del df2.index.name
    df2['Time'] = pd.to_datetime(
        df2['Time'], format='%Y-%m-%d %')
    print(df2)
    return(df2)


# print(type(GOOG))
# print(GOOG)


data = fetch_2_df('BTC')

bt = Backtest(data, SmaCross,
              cash=10000, commission=.002)
bt.run()
bt.plot()
