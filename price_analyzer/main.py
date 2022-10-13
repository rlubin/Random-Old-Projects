from AlphaVantageAPI import AlphaVantageAPI
from PriceData import PriceData
from Stats import Stats
import pandas as pd
from matplotlib import pyplot as plt
import time
from tqdm import tqdm
from sp500 import SP500


def main():
    api = AlphaVantageAPI()
    securities = SP500[50:52]
    price_dfs = []
    for i in tqdm(range(len(securities))):
        price_dfs.append(PriceData(api.get_daily(
            securities[i]), securities[i]).df)
    per = 60
    for df in price_dfs:
        stats = Stats(df)
        stats.hist(per)
    # stats.corr(price_dfs, per)


if __name__ == '__main__':
    main()
