from AlphaVantageAPI import AlphaVantageAPI
from PriceData import PriceData
from tqdm import tqdm
from sp500 import SP500
from watchlist import watchlist
import os


def main():
    api = AlphaVantageAPI()
    # securities = SP500
    securities = watchlist
    price_dfs = []
    # download whatever isn't downloaded
    # on_pc needs to be a list of name.csv strings
    on_pc = os.scandir('./price_data/')
    has = []
    for d in on_pc:
        name = os.path.splitext(d)[0]
        name = name[str(name).rfind('/')+1:]
        has.append(name)
    securities = [s for s in securities if s not in has]
    # download and save all data
    # create price_dfs from api
    for i in tqdm(range(len(securities))):
        df1 = PriceData(api.get_daily(
            securities[i]), securities[i]).df
        price_dfs.append(df1)
        df1.to_csv('./price_data/' + securities[i] + '.csv')


if __name__ == '__main__':
    main()
