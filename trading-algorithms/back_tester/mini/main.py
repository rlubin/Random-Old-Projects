from backtesting import Backtest, Strategy
from backtesting.lib import crossover

from backtesting.test import SMA, GOOG

import pandas as pd
import requests

from fetch import fetch


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


# print(type(GOOG))
# print(GOOG)


data = fetch('BTC')

bt = Backtest(data, SmaCross,
              cash=10000, commission=.002)
bt.run()
# bt.plot()
