import pandas as pd
import requests


def fetch(sym1, lim=100, sym2='CAD'):
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
    print(df2)
    df2['Time'] = pd.to_datetime(
        df2['Time'], format='%Y-%m-%d %H:%M:%S')
    df2 = df2.set_index('Time')
    del df2.columns['Time']
    print(df2)
    return(df2)
