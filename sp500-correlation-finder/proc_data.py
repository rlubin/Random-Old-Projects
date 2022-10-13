from AlphaVantageAPI import AlphaVantageAPI
from PriceData import PriceData
from corr import corr
import pandas as pd
import time
from tqdm import tqdm
from sp500 import SP500
from options import OPTIONS
import datetime
import os
from Stats import Stats


def main():
    # periods = [20, 40, 60, 125, 253, 1000]
    periods = [20]  # , 40, 60, 125, 250, 500, 750, 1000]
    price_dfs = []
    uncorr = []
    uncorr_sets = []
    # narrow down the list of data analyzed to input list
    liss = OPTIONS
    print(liss)
    # grab data from files
    # create price_dfs from files
    directory = './price_data/'
    for entry in os.scandir(directory):
        a = str(entry).find("'")
        b = str(entry).find('.csv')
        str_entry = str(entry)[a+1:b]
        if str_entry in liss:  # checks options only
            df1 = pd.read_csv(entry, index_col=0)
            price_dfs.append(df1)
    print(price_dfs)
    # correlate all of the stock against each other over different time frames
    for per in periods:
        c = corr(price_dfs, per)
        for col in c.columns:
            for x in list(c.index):
                if(c[col][x] > -0.2 and c[col][x] < 0.2):
                    uncorr.append((col, x, c[col][x]))
        # remove duplicates
        # create a set of stocks whose portfolio correlation is ~0 (5-10)
        # for ment in uncorr:
            # print(ment)
        for df in price_dfs:
            stats = Stats(df)
            stats.hist(per)
        c.to_csv(f'./corrs/' + datetime.datetime.now().strftime('%Y-%m-%d') + '_' + str(per) + '-period' +
                 '.csv')


if __name__ == '__main__':
    main()
