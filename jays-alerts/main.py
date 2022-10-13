from FMP_API import FMP
import os
import datetime
import pandas as pd


def get_desktop_path():
    return os.path.join(os.path.expanduser("~"), "desktop")


def get_tickers(location):
    '''
    create a list of tickers from tickers.csv
    ['SPY', 'GOOGL', ...]
    '''
    df = pd.read_csv(location)
    t = [df.columns.values.tolist()] + df.values.tolist()
    tickers = [item for sublist in t for item in sublist]
    return tickers


def sma(df, period):
    '''

    '''
    # print(df["adjClose"].head(3))
    sma = df["adjClose"].head(period).mean()
    return sma


def fisher_transform(df, peroid):
    '''

    '''
    pass


def output_file(df):
    '''

    '''
    date = datetime.datetime.now().strftime("%d-%m-%Y")
    location = os.path.abspath(os.path.join(
        get_desktop_path(), f"triggered_{date}.csv"))
    # location = os.path.abspath(os.path.join(
    #     os.getcwd(), f"triggered_{date}.csv"))
    df.to_csv(location, index=False)


def main():
    '''

    '''
    location = os.path.abspath(os.path.join(get_desktop_path(), "tickers.csv"))
    # location = os.path.abspath(os.path.join(os.getcwd(), "tickers.csv"))
    # location = os.path.abspath(os.path.join(os.getcwd(), "tickers_short.csv"))
    securities = get_tickers(location)
    print(securities)

    # ["Ticker", "Trigger"]
    triggered = []

    fmp = FMP()
    for security in securities:
        # print(security)
        data = fmp.get_daily(security)
        if data == {}:
            print(security, "bad")
            continue
        # print(data)
        data1 = data["historical"][:25]
        data2 = data["historical"][1:26]
        df = pd.DataFrame(data1)
        df2 = pd.DataFrame(data2)
        # print(df)
        # print(df2)
        sma5 = sma(df, 5)
        sma5_ = sma(df2, 5)
        sma9 = sma(df, 9)
        sma9_ = sma(df2, 9)
        sma20 = sma(df, 20)
        sma20_ = sma(df2, 20)
        sma25 = sma(df, 25)
        sma25_ = sma(df2, 25)
        price = df["adjClose"][0]
        price_ = df["adjClose"][1]
        # print(price)
        # print(price_)
        # fisher transform
        # print()
        # if 5 sma crossover 25 sma
        if (sma5_ < sma25_) and (sma5 > sma25):
            triggered.append([security, "5 sma crossover 25 sma"])
        # if 9 sma crossover 20 sma
        if (sma9_ < sma20_) and (sma9 > sma20):
            triggered.append([security, "9 sma crossover 20 sma"])
        # if price crossover 9 sma
        if (price_ < sma9_) and (price > sma9):
            triggered.append([security, "price crossover 9 sma"])
        # if price crossunder 9 sma
        if (price_ > sma9_) and (price < sma9):
            triggered.append([security, "price crossunder 9 sma"])
        # if price crossover 20 sma
        if (price_ < sma20_) and (price > sma20):
            triggered.append([security, "price crossover 20 sma"])

    print(triggered)

    triggered_df = pd.DataFrame(triggered, columns=["Ticker", "Trigger"])
    print(triggered_df)

    output_file(triggered_df)


# if __name__ == '__main__':
#     main()

main()
